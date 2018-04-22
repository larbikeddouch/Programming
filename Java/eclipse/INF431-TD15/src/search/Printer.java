package search;

import java.util.concurrent.BlockingQueue;

public class Printer extends Thread {
    BlockingQueue<String> in;
    int items, num;
    public Printer(BlockingQueue<String> in){
    	this.items = 0;
    	this.in = in;
    	this.num = -1;
    }
    public Printer(BlockingQueue<String> in, int num){
    	this.items = 0;
    	this.num = num;
    	this.in = in;
    }
    
    public void printStatus(){
    	System.out.println("Thread "+getName()+" processed "+items+" items.");
    }
    public void run() {
    	try {
    		String result = in.take();
    		while (result != "--EOF--" && (this.items < this.num || this.num <= 0)) {
    			items++;
    			System.out.println(result);
    			result = in.take();
    		}
    	}
    	catch (InterruptedException e) {
    		
    	}
    }
}
