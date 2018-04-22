import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

// Block Chain should maintain only limited block nodes to satisfy the functions
// You should not have all the blocks added to the block chain in memory 
// as it would cause a memory overflow.

public class BlockChain {
    public static final int CUT_OFF_AGE = 10;
    
    public class BlockLeaf {

    	private Block block;
    	private BlockLeaf parent;
    	private int blockHeight;
    	private int blockAge;
    	private UTXOPool leafUtxoPool;
    	
    	public BlockLeaf(Block b, BlockLeaf _parent, int _height, int _age, UTXOPool utxoPool) {
    		this.block = b;
    		this.parent = _parent;
    		this.blockHeight = _height;
    		this.blockAge = _age;
    		this.leafUtxoPool = utxoPool;
    	}
    	
    	public Block getBlock() { return block;	}
    	public BlockLeaf getParent() { return parent;}
    	public int getHeight() { return blockHeight;}
    	public void incrementHeight() { blockHeight++;}
    	public int getAge() { return blockAge;}
    	public void incrementAge() { blockAge++;}
    	public boolean isValid(int maxHeight) { return maxHeight < blockHeight + BlockChain.CUT_OFF_AGE;}
    	public UTXOPool getUTXOPool() { return new UTXOPool(this.leafUtxoPool);}
    }
    
    private TransactionPool txPool;
    private Set<BlockLeaf> blocks;
    
    /**
     * create an empty block chain with just a genesis block. Assume {@code genesisBlock} is a valid
     * block
     */
    public BlockChain(Block genesisBlock) {
        this.txPool = new TransactionPool();
        UTXOPool utxoP = new UTXOPool();
        utxoP.addUTXO(new UTXO(genesisBlock.getCoinbase().getHash(),0),
        		genesisBlock.getCoinbase().getOutput(0));
        for (Transaction tx : genesisBlock.getTransactions()) {
        	int txOutputSize = tx.getOutputs().size();
        	for (int i = 0 ; i < txOutputSize; i++) {
        		UTXO u = new UTXO(tx.getHash(),i);
        		utxoP.addUTXO(u,tx.getOutput(i));
        	}
        }
        this.blocks = new HashSet<>();
        this.blocks.add(new BlockLeaf(genesisBlock,null,0,0,utxoP));
    }

    /** Get the maximum height block */
    /*public Block getMaxHeightBlock() {
        int max = -1;
        Block result = null;
        for (Block b : blockHeights.keySet()) {
        	if (blockHeights.get(b) == max) {
        		if (blockAge.get(b) > blockAge.get(result)) {
        			result = b;
        		}
        	}
        	
        	if (blockHeights.get(b) > max) {
        		result = b;
        		max = blockHeights.get(b);
        	}
        }
        return result;
    }*/
    
    public BlockLeaf getMaxHeightBlockLeaf() {
    	BlockLeaf leafResult = null;
        for (BlockLeaf bl : this.blocks) {
        	if (leafResult == null)
        		leafResult = bl;
        	else {
        		if (bl.getHeight() > leafResult.getHeight() ||
        				(bl.getHeight() == leafResult.getHeight() &&
        				bl.getAge() > leafResult.getAge()))
        			leafResult = bl;
        	}
        }
        return leafResult;
    }
    public Block getMaxHeightBlock() {
        return this.getMaxHeightBlockLeaf().getBlock();
    }
    

    /** Get the UTXOPool for mining a new block on top of max height block */
    public UTXOPool getMaxHeightUTXOPool() {
    	return this.getMaxHeightBlockLeaf().getUTXOPool();
    }

    /** Get the transaction pool to mine a new block */
    public TransactionPool getTransactionPool() {
        return new TransactionPool(this.txPool);
    }

    /**
     * Add {@code block} to the block chain if it is valid. For validity, all transactions should be
     * valid and block should be at {@code height > (maxHeight - CUT_OFF_AGE)}.
     * 
     * <p>
     * For example, you can try creating a new block over the genesis block (block height 2) if the
     * block chain height is {@code <=
     * CUT_OFF_AGE + 1}. As soon as {@code height > CUT_OFF_AGE + 1}, you cannot create a new block
     * at height 2.
     * 
     * @return true if block is successfully added
     */
    /*public boolean addBlock(Block block) {
    	
    	// Handle Genesis Block
    	if (block.getPrevBlockHash() == null)
    		return false;
    	
    	// check that all transactions are in the blockchain transaction pool
    	//if (!this.txPool.getTransactions().containsAll(block.getTransactions()))
    	//	return false;
    	
    	// check that all transactions are valid (individually and as a group)
    	// already handled by TxHandler class, let's not rewrite the code here
    	TxHandler txHandler = new TxHandler(this.utxoP);
    	Transaction[] blockTransactions = new Transaction[block.getTransactions().size()];
    	for (int i = 0; i < block.getTransactions().size(); i ++) {
    		Transaction tx = block.getTransaction(i);
    		blockTransactions[i] = tx;
    	}
    	Transaction[] txHandlerTransactions = txHandler.handleTxs(blockTransactions);
    	if (txHandlerTransactions.length != blockTransactions.length)
    		return false;
    	
    	// the block is now valid
    	// find previous block and add it to the blockchain
    	Block prevBlock = null;
    	int prevBlockHeight = -1;
    	ByteArrayWrapper prevHash = new ByteArrayWrapper(block.getPrevBlockHash());
    	for (Block b : this.blockHeights.keySet()) {
    		ByteArrayWrapper bHash = new ByteArrayWrapper(b.getHash());
    		if (bHash.equals(prevHash)) {
    			prevBlock = b;
    			prevBlockHeight = this.blockHeights.get(prevBlock);
    			continue;
    		}
    	}
    	
    	if (prevBlock == null) {
    		// means that the block either does not exist or is further away than max height
    		return false;
    	}
    	
    	Block maxHeightBlock = this.getMaxHeightBlock();
    	int maxHeight = this.blockHeights.get(maxHeightBlock);
    	if (prevBlockHeight == maxHeight) {
    		// needs to update height and age
    		Set<Block> stillValidBlocks = new HashSet<>();
    		Set<Block> nowInvalidBlocks = new HashSet<>();
    		for (Block b : this.blockHeights.keySet()) {
    			if (this.blockHeights.get(b) <= maxHeight - CUT_OFF_AGE + 1)
    				nowInvalidBlocks.add(b);
    			else
    				stillValidBlocks.add(b);
    		}
    		for (Block b : nowInvalidBlocks) {
    			this.blockHeights.remove(b);
    			this.blockAge.remove(b);
    		}
    		for (Block b : stillValidBlocks) {
    			this.blockHeights.put(b,this.blockHeights.get(b) + 1);
    			this.blockAge.put(b,this.blockAge.get(b) + 1);
    		}
    		this.blockHeights.put(block, maxHeight + 1);
    		this.blockAge.put(block, 0);
    	}
    	else {
    		Set<Block> currentBlockSet = this.blockAge.keySet();
    		for (Block b : currentBlockSet) {
    			this.blockAge.put(b, this.blockAge.get(b) + 1);
    		}
    	}
    	// add the new block
		this.blockHeights.put(block, prevBlockHeight + 1);
		this.blockAge.put(block, 0);
    	// update transaction pool
		for (Transaction tx : block.getTransactions()) {
			this.txPool.removeTransaction(tx.getHash());
		}
		// update utxopool
		this.utxoP = txHandler.getUTXOPool();
		this.utxoP.addUTXO(new UTXO(block.getCoinbase().getHash(),0),
        		block.getCoinbase().getOutput(0));
    	
    	return true;
    }*/
    
    public boolean addBlock(Block block) {
    	
    	// Handle Genesis Block
    	if (block.getPrevBlockHash() == null)
    		return false;
    	
    	// get previous block
    	BlockLeaf prevB = null;
    	ByteArrayWrapper prevBlockHash = new ByteArrayWrapper(block.getPrevBlockHash());
    	for (BlockLeaf bl : this.blocks) {
    		ByteArrayWrapper blHash = new ByteArrayWrapper(bl.getBlock().getHash());
    		if (blHash.equals(prevBlockHash)) {
    			prevB = bl;
    			continue;
    		}
    	}
    	if (prevB == null)
    		return false;
    	
    	// check that all transactions are in the blockchain transaction pool
    	//if (!this.txPool.getTransactions().containsAll(block.getTransactions()))
    	//	return false;
    	
    	// check that all transactions are valid (individually and as a group)
    	// already handled by TxHandler class, let's not rewrite the code here
    	TxHandler txHandler = new TxHandler(prevB.getUTXOPool());
    	Transaction[] blockTransactions = new Transaction[block.getTransactions().size()];
    	for (int i = 0; i < block.getTransactions().size(); i ++) {
    		Transaction tx = block.getTransaction(i);
    		// check that transaction is still in transaction pool
    		//if (this.txPool.getTransaction(tx.getHash()) == null)
    		//	return false;
    		blockTransactions[i] = tx;
    	}
    	Transaction[] txHandlerTransactions = txHandler.handleTxs(blockTransactions);
    	if (txHandlerTransactions.length != blockTransactions.length)
    		return false;
    	
    	// the block is now valid
    	// find previous block and add it to the blockchain
    	// find corresponding blockleaf
    	BlockLeaf maxHeightBl = this.getMaxHeightBlockLeaf();
    	int maxHeight = maxHeightBl.getHeight();
    	
    	// updates height and age for all blocks (if necessary)
    	if (prevB.getHeight() == maxHeight) {
    		Set<BlockLeaf> invalidBls = new HashSet<>(); 
    		for (BlockLeaf bl : this.blocks) {
    			bl.incrementAge();
    			bl.incrementHeight();
    			if (!bl.isValid(maxHeight + 1))
    				invalidBls.add(bl);
    		}
    		this.blocks.removeAll(invalidBls);
    	}
    	else {
    		for (BlockLeaf bl : this.blocks) {
    			bl.incrementAge();
    		}
    	}
    	
		// update utxopool and add block
		UTXOPool updatedUtxoP = txHandler.getUTXOPool();
		updatedUtxoP.addUTXO(new UTXO(block.getCoinbase().getHash(),0),
        		block.getCoinbase().getOutput(0));
		this.blocks.add(new BlockLeaf(block,prevB,prevB.getHeight() + 1,0,updatedUtxoP));
		
    	// update transaction pool
		for (Transaction tx : block.getTransactions()) {
			this.txPool.removeTransaction(tx.getHash());
		}
    	
    	return true;
    }

    /** Add a transaction to the transaction pool */
    public void addTransaction(Transaction tx) {
        
    	this.txPool.addTransaction(tx);
    	
    }
}