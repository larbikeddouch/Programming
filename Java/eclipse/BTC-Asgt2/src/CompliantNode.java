import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* CompliantNode refers to a node that follows the rules (not malicious)*/
public class CompliantNode implements Node {
	
	private double p_graph;
	private double p_malicious;
	private double p_txDistribution;
	private int numRounds;
	private int currentRound;
	// Boolean is true when the node is compliant
	private HashMap<Integer, Boolean> followeeIds;
	private HashMap<Transaction,Integer> counters;
	private HashMap<Integer,ArrayList<Transaction>> lastInfo;
	private HashMap<Integer,ArrayList<Transaction>> currentInfo;
	private Set<Transaction> lastSent;
	
    public CompliantNode(double p_graph, double p_malicious, double p_txDistribution, int numRounds) {
        this.p_graph = p_graph;
        this.p_malicious = p_malicious;
        this.p_txDistribution = p_txDistribution;
        this.numRounds = numRounds;
        this.currentRound = 0;
        this.followeeIds = new HashMap<>();
        this.counters = new HashMap<>();
        this.lastInfo = new HashMap<>();
        this.currentInfo = new HashMap<>();
        this.lastSent = new HashSet<>();
    }

    public void setFollowees(boolean[] followees) {
        for (int i = 0; i < followees.length; i++) {
        	if (followees[i]) this.followeeIds.put(i,true);
        }
    }

    public void setPendingTransaction(Set<Transaction> pendingTransactions) {
    	for (Transaction tx : pendingTransactions) {
    		if (!counters.containsKey(tx))
    			counters.put(tx, 100);
    	}
    }

    public Set<Transaction> sendToFollowers() {
        this.currentRound++;
        if (this.currentRound >= this.numRounds) {
        	
        	// Latest Code
        	return getLatestTransactionSet(3);
        	
        	// New Code where we compute the intersection of all information sent
        	/*Set<Transaction> result = new HashSet<>(this.lastSent);
        	for (Integer i : this.lastInfo.keySet()) {
        		if (this.followeeIds.containsKey(i)) {
        			Set<Transaction> broadcasted = new HashSet<>(this.lastInfo.get(i));
        			result.retainAll(broadcasted);
        		}
        	}
        	return result;*/
        }
        this.lastSent = getLatestTransactionSet(0);
        return this.lastSent;
    }
    
    private Set<Transaction> getLatestTransactionSet(int threshold) {
        Set<Transaction> result = new HashSet<Transaction>();
        for (Transaction tx : counters.keySet()) {
        	if (counters.get(tx) > threshold)
        		result.add(tx);
        }
        return result;
    }

    public void receiveFromFollowees(Set<Candidate> candidates) {
    	
    	// update counters first
        for (Candidate c : candidates) {
        	if (!followeeIds.containsKey(c.sender) && !followeeIds.get(c.sender))
        		continue;
        	
        	if (!this.lastInfo.containsKey(c.sender))
        		this.lastInfo.put(c.sender, new ArrayList<Transaction>());
        	
        	if (!this.currentInfo.containsKey(c.sender))
        		this.currentInfo.put(c.sender, new ArrayList<Transaction>());
        	this.currentInfo.get(c.sender).add(c.tx);
        	
        	if (!counters.containsKey(c.tx))
        		counters.put(c.tx, 0);
        	
        	if (this.lastInfo.containsKey(c.sender)
        			&& this.lastInfo.get(c.sender).contains(c.tx)) {
        		counters.put(c.tx, counters.get(c.tx) + 1);
        	}
        }
        
        // update last info and detect malicious nodes
        for (Integer sender : this.lastInfo.keySet()) {
        	if (!this.currentInfo.containsKey(sender) ||
        			!this.currentInfo.get(sender).containsAll(this.lastInfo.get(sender))) {
        		this.followeeIds.put(sender, false);
        	}
        }
        
        this.lastInfo = new HashMap<>(this.currentInfo);
        for (Integer sender : this.currentInfo.keySet())
        	this.currentInfo.get(sender).clear();
        
    }
}
