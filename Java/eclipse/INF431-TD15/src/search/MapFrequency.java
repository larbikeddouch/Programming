package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class MapFrequency extends Thread {
	BufferedReader data;
	String[] queries;
	LinkedBlockingQueue<String> result;
	int processedItems;
	int matchesFound;
	
	public MapFrequency(BufferedReader data, String[] queries, LinkedBlockingQueue<String> result) {
		this.data = data;
		this.queries = queries;
		this.result = result;
	}
	
    public void printStatus() {
        System.out.println("Thread "+getName()+" processed "+processedItems+" items and found "+matchesFound+" matching results.");
	
    }
    
    @Override
	public void run(){
    	try {
    		String currentTitle = data.readLine();
    		while (currentTitle != null && !Thread.interrupted()) {
    			
    			for (String query : this.queries) {
    				if (currentTitle.indexOf(query) >= 0) {
        				result.add(query);
        				matchesFound++;
        			}
    			}
    			
    			processedItems++;
    			currentTitle = data.readLine();
    		}
    		result.add("--EOF--");
    	}
    	catch (IOException e) {
    		result.add("--EOF--");
    	}
    }
    
}
