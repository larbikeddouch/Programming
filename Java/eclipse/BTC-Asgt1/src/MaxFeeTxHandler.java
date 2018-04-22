import java.util.ArrayList;

public class MaxFeeTxHandler {
    
	protected UTXOPool utxoPool;
	private double maxFee;
	
    public MaxFeeTxHandler(UTXOPool utxoPool) {
    	this.utxoPool = new UTXOPool(utxoPool);
    	this.maxFee = 0;
    }
    
    public double getMaxFee() {
    	return this.maxFee;
    }
    
    public boolean isValidTx(Transaction tx) {
    	ArrayList<UTXO> claimedUtxos = new ArrayList<UTXO>();
    	double inputTotalValue = 0;
    	double outputTotalValue = 0;
    	
    	// (1), (2) and (3)
    	// should use hash of the current transaction. cannot use
		// the one of the previous transaction when creating coins
    	for (int i = 0; i < tx.numInputs(); i++ ) {
    		Transaction.Input in = tx.getInputs().get(i);
    		UTXO u = new UTXO(in.prevTxHash, in.outputIndex);
    		if (!this.utxoPool.contains(u))
    			return false;
    		Transaction.Output prevOutput = this.utxoPool.getTxOutput(u);
    		if (!Crypto.verifySignature(prevOutput.address, tx.getRawDataToSign(i), in.signature))
    			return false;
    		if (claimedUtxos.contains(u))
    			return false;
    		claimedUtxos.add(u);
    		inputTotalValue += prevOutput.value;
    	}
    	
    	// (4)
    	for (Transaction.Output output : tx.getOutputs()) {
    		if (output.value < 0)
    			return false;
    		outputTotalValue += output.value;
    	}
    	
    	// (5)
    	if (outputTotalValue > inputTotalValue)
    		return false;
    	
    	return true;
    }
    
    public Transaction[] handleTxs(Transaction[] possibleTxs) {
    	ArrayList<Transaction> pTxs = new ArrayList<Transaction>();
    	for (int i = 0; i < possibleTxs.length; i++) {
    		if (this.isValidTx(possibleTxs[i])) pTxs.add(possibleTxs[i]);
    	}
    	ArrayList<Transaction> result = this.handleTxsAsArrayList(pTxs);
    	Transaction[] resultAsArray = new Transaction[result.size()];
    	for (int i = 0; i < result.size(); i++) {
    		resultAsArray[i] = result.get(i);
    	}
    	
    	return resultAsArray;
    }
    
    protected ArrayList<Transaction> handleTxsAsArrayList(ArrayList<Transaction> possibleTxs) {
    	if (possibleTxs.isEmpty() || possibleTxs == null)
    		return null;
    	
    	ArrayList<Transaction> pTxs = new ArrayList<Transaction>(possibleTxs);
    	
		Transaction firstCorrectTxs = null;
    	for (Transaction tx : pTxs) {
    		if (this.isValidTx(tx)) {
    			firstCorrectTxs = tx;
    			break;
    		}
    	}
    	if (firstCorrectTxs == null)
    		return new ArrayList<Transaction>();
    	pTxs.remove(firstCorrectTxs);
    	ArrayList<Transaction> result = this.maxTransactions(firstCorrectTxs,pTxs);
    	return result;
    }
    
    private ArrayList<Transaction> maxTransactions(Transaction firstCorrectTxs, ArrayList<Transaction> pTxs) {
		
    	if (pTxs.isEmpty() || pTxs == null) {
			this.maxFee = this.getTransactionFee(firstCorrectTxs);
			ArrayList<Transaction> result = new ArrayList<Transaction>();
			result.add(firstCorrectTxs);
			return result;
		}
		else {
			ArrayList<Transaction> firstCorrectTxsAsList = new ArrayList<Transaction>();
			firstCorrectTxsAsList.add(firstCorrectTxs);
			UTXOPool poolWith = this.updateUTXOPool(this.utxoPool,firstCorrectTxsAsList);
			
			MaxFeeTxHandler maxFeeWithout = new MaxFeeTxHandler(this.utxoPool);
			MaxFeeTxHandler maxFeeWith = new MaxFeeTxHandler(poolWith);
			ArrayList<Transaction> maxResultWithout = maxFeeWithout.handleTxsAsArrayList(pTxs);
			ArrayList<Transaction> maxResultWith = maxFeeWithout.handleTxsAsArrayList(pTxs);
			
			if (maxFeeWithout.getMaxFee() > maxFeeWith.getMaxFee() + this.getTransactionFee(firstCorrectTxs)) {
				this.maxFee = maxFeeWithout.getMaxFee();
				return maxResultWithout;
			}
			else {
				this.maxFee = maxFeeWith.getMaxFee() + this.getTransactionFee(firstCorrectTxs);
				maxResultWith.add(0, firstCorrectTxs);
				return maxResultWith;
			}
		}
		
	}
    
    protected UTXOPool updateUTXOPool(UTXOPool utxop, ArrayList<Transaction> txs) {
    	UTXOPool result = new UTXOPool(utxop);
    	for (Transaction tx : txs) {
    		for (Transaction.Input in : tx.getInputs()) {
    			UTXO u = new UTXO(in.prevTxHash,in.outputIndex);
    			result.removeUTXO(u);
    		}
    		ArrayList<Transaction.Output> outs = tx.getOutputs();
    		for (int i = 0; i < tx.numOutputs(); i++) {
    			UTXO u = new UTXO(tx.getHash(),i);
    			result.addUTXO(u, outs.get(i));
    		}
    	}
    	return result;
    }

    protected double getTransactionFee(Transaction tx) {
    	double fee = 0;
    	for (Transaction.Input in : tx.getInputs()) {
    		UTXO u = new UTXO(in.prevTxHash,in.outputIndex);
    		fee += this.utxoPool.getTxOutput(u).value;
    	}
    	for (Transaction.Output out : tx.getOutputs()) {
    		fee -= out.value;
    	}
    	return fee;
    }
    
}
