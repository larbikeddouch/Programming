package search;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class ReduceFrequency extends Thread {
    LinkedBlockingQueue<String> in;
    HashMap<String,Integer> count;
    int items;
    
    public ReduceFrequency(LinkedBlockingQueue<String> in) {
    	this.in= in;
    	this.count = new HashMap<String,Integer>();
    	this.items = 0;
    }
    
    public void printStatus() {
    	System.out.println("Thread "+getName()+" processed "+items+" items.");
    	for (String title : count.keySet()) {
    		System.out.println(title + " occurs " + count.get(title) + " times.");
    	}
    }
    
    @Override
    public void run() {
    	try {
    		String result = in.take();
    		while (result != "--EOF--") {
    			if (!count.containsKey(result)) {
    				count.put(result, 1);
    			}
    			else {
    				count.put(result, count.get(result) + 1);
    			}
    			items++;
    			result = in.take();
    		}
    	}
    	catch (InterruptedException e) {
    		
    	}
    }
    
}
