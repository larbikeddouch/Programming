import java.util.ArrayList;

public class TxHandler {

    /**
     * Creates a public ledger whose current UTXOPool (collection of unspent transaction outputs) is
     * {@code utxoPool}. This should make a copy of utxoPool by using the UTXOPool(UTXOPool uPool)
     * constructor.
     */
	protected UTXOPool utxoPool;
	
    public TxHandler(UTXOPool utxoPool) {
    	this.utxoPool = new UTXOPool(utxoPool);
    }

    /**
     * @return true if:
     * (1) all outputs claimed by {@code tx} are in the current UTXO pool, 
     * (2) the signatures on each input of {@code tx} are valid, 
     * (3) no UTXO is claimed multiple times by {@code tx},
     * (4) all of {@code tx}s output values are non-negative, and
     * (5) the sum of {@code tx}s input values is greater than or equal to the sum of its output
     *     values; and false otherwise.
     */
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

    /**
     * Handles each epoch by receiving an unordered array of proposed transactions, checking each
     * transaction for correctness, returning a mutually valid array of accepted transactions, and
     * updating the current UTXO pool as appropriate.
     * 
     * Check if there is any potential issue with a circular graph when Transaction can reference
     * another one in the same epoch.
     */
    public Transaction[] handleTxs(Transaction[] possibleTxs) {
    	ArrayList<Transaction> pTxs = new ArrayList<Transaction>();
    	for (int i = 0; i < possibleTxs.length; i++) {
    		pTxs.add(possibleTxs[i]);
    	}
    	ArrayList<Transaction> result = this.handleTxsAsArrayList(pTxs);
    	Transaction[] resultAsArray = new Transaction[result.size()];
    	for (int i = 0; i < result.size(); i++) {
    		resultAsArray[i] = result.get(i);
    	}
    	
    	return resultAsArray;
    }
    
    protected ArrayList<Transaction> handleTxsAsArrayList(ArrayList<Transaction> possibleTxs) {
    	ArrayList<Transaction> result = new ArrayList<Transaction>();
    	while (!possibleTxs.isEmpty()) {
    		ArrayList<Transaction> correctTxs = new ArrayList<Transaction>();
        	for (Transaction tx : possibleTxs) {
        		if (this.isValidTx(tx))
        			correctTxs.add(tx);
        	}
        	possibleTxs.removeAll(correctTxs);
        	
        	if (correctTxs.size() == 0)
        		break;
        	
        	ArrayList<Transaction> intermediaryResult = this.maxTransactions(correctTxs);
        	
        	this.utxoPool = this.updateUTXOPool(this.utxoPool,intermediaryResult);
        	result.addAll(intermediaryResult);
    	}
    	return result;
    }
    	
    
    /**
     * Method that adds creates a set of Transactions where no other transactions can be added
     * Starts by adding the first correct transaction and then iterate on the other ones
     * If a new transaction is mutually valid with the current ones, it will add it
     * Otherwise, it will be discarded as it is impossible to add it at the same time as the other ones 
     * @param intermediaryResult
     * @param correctTxs
     */
    protected ArrayList<Transaction> maxTransactions(ArrayList<Transaction> correctTxs) {
    	ArrayList<Transaction> intermediaryResult = new ArrayList<Transaction>();
    	
    	intermediaryResult.add(correctTxs.get(0));
    	
    	boolean shouldAdd;
    	
    	for (int i = 1; i < correctTxs.size(); i++) {
    		Transaction currentTxs = correctTxs.get(i);
    		shouldAdd = true;
    		for (Transaction tx : intermediaryResult)
    			shouldAdd = shouldAdd && this.mutuallyValid(currentTxs, tx);
    		if (shouldAdd)
    			intermediaryResult.add(currentTxs);
    	}
    	
    	return intermediaryResult;
	}

	/**
     * @param tx1 a transaction
     * @param tx2 a transaction
     * @return a boolean that describes whether both transactions are valid at the same time
     */
    protected boolean mutuallyValid(Transaction tx1, Transaction tx2) {
    	
    	for (Transaction.Input in1 : tx1.getInputs()) {
    		UTXO u1 = new UTXO(in1.prevTxHash,in1.outputIndex);
    		for (Transaction.Input in2 : tx2.getInputs()) {
    			UTXO u2 = new UTXO(in2.prevTxHash,in2.outputIndex);
    			if (u1.equals(u2))
    				return false;
    		}
    	}
    	
    	return true;
    }
    
    /**
     * Update utxo pool with the resulting transactions of handleTxs method
     * Remove used transaction outputs and adds the new transaction outputs
     * @param txs
     */
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
    
    protected final double getPackageFee(ArrayList<Transaction> txs) {
    	double value = 0;
    	for (Transaction tx : txs) {
    		value += this.getTransactionFee(tx);
    	}
    	return value;
    }

	protected double getTransactionFee(Transaction tx) {
		return 0;
	}

}
