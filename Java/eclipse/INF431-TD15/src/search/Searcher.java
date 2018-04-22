package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class Searcher extends Thread {
    private final BufferedReader data;
    private final String query;
    private final LinkedBlockingQueue<String> result;
    private int processedItems = 0;
    private int matchesFound = 0;
    
    public Searcher(BufferedReader data, 
                    String query,
                    LinkedBlockingQueue<String> result){
        this.data = data;
        this.query = query;
        this.result = result;
    }

	public int getProcessedItems() {
		return processedItems;
	}

	public int getMatchesFound() {
		return matchesFound;
	}

    public void printStatus() {
        System.out.println("Thread "+getName()+" processed "+processedItems+" items and found "+matchesFound+" matching results.");
	
    }
    
    @Override
	public void run(){
    	try {
    		String currentTitle = data.readLine();
    		while (currentTitle != null && !Thread.interrupted()) {
    			
    			if (currentTitle.indexOf(query) >= 0) {
    				result.add(currentTitle);
    				matchesFound++;
    			}
    			
    			processedItems++;
    			currentTitle = data.readLine();
    		}
    		result.add("--EOF--");
    		System.out.println("Null");
    	}
    	catch (IOException e) {
    		result.add("--EOF--");
    		System.out.println("Exception");
    	}
    }
    
}
