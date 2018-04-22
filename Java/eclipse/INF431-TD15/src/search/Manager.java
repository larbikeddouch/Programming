package search;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Manager {
    
    public static LinkedBlockingQueue<String> search1(BufferedReader data, String query, int num)
        throws InterruptedException{
        LinkedBlockingQueue<String> result = new LinkedBlockingQueue<String>();
        Searcher s = new Searcher(data,query,result);
        s.setName("1");
        s.start();
        Thread.sleep(100);
        s.printStatus();
        return result;
    }
    
    public static LinkedBlockingQueue<String> search2(BufferedReader data, String query, int num)
            throws InterruptedException{
            LinkedBlockingQueue<String> result = new LinkedBlockingQueue<String>();
            Searcher s = new Searcher(data,query,result);
            s.setName("1");
            s.start();
            while (s.isAlive()) {
            	Thread.sleep(100);
                s.printStatus();
            }
            return result;
        }
    
    public static LinkedBlockingQueue<String> search2b(BufferedReader data, String query, int num)
            throws InterruptedException{
            LinkedBlockingQueue<String> result = new LinkedBlockingQueue<String>();
            Searcher s = new Searcher(data,query,result);
            s.setName("1");
            s.start();
            s.join();
            s.printStatus();
            return result;
        }
    
    public static LinkedBlockingQueue<String> search3(BufferedReader data, String query, int num)
            throws InterruptedException{
            LinkedBlockingQueue<String> result = new LinkedBlockingQueue<String>();
            Searcher s = new Searcher(data,query,result);
            Printer p = new Printer(result);
            s.setName("1");
            s.start();
            p.setName("2");
            p.start();
            s.join();
            p.join();
            s.printStatus();
            p.printStatus();
            return result;
        }
    
    public static LinkedBlockingQueue<String> search4(BufferedReader data, String query, int num)
            throws InterruptedException{
            LinkedBlockingQueue<String> result = new LinkedBlockingQueue<String>();
            Searcher s = new Searcher(data,query,result);
            Printer p = new Printer(result,10);
            s.setName("1");
            s.start();
            p.setName("2");
            p.start();
            p.join();
            s.interrupt();
            s.join();
            s.printStatus();
            p.printStatus();
            return result;
        }
    
    public static LinkedBlockingQueue<String> search5(BufferedReader data, String query, int num)
            throws InterruptedException{
            LinkedBlockingQueue<String> result = new LinkedBlockingQueue<String>();
            Searcher s1 = new Searcher(data,query,result);
            Searcher s2 = new Searcher(data,query,result);
            Printer p = new Printer(result,10);
            s1.setName("search 1");
            s1.start();
            s2.setName("search 2");
            s2.start();
            p.setName("printer");
            p.start();
            p.join();
            s1.interrupt();
            s2.interrupt();
            s1.join();
            s2.join();
            s1.printStatus();
            s2.printStatus();
            p.printStatus();
            return result;
        }
    
    public static HashMap<String,Integer> count(BufferedReader data, String[] queries) throws InterruptedException {
    	LinkedBlockingQueue<String> result = new LinkedBlockingQueue<String>();
    	MapFrequency m1 = new MapFrequency(data,queries,result);
    	MapFrequency m2 = new MapFrequency(data,queries,result);
    	ReduceFrequency r = new ReduceFrequency(result);
    	
    	m1.setName("map 1");
    	m2.setName("map 2");
    	r.setName("reduce");
    	
    	m1.start();
    	m2.start();
    	r.start();
    	
    	m1.join();
    	m2.join();
    	r.join();
    	
    	m1.printStatus();
    	m2.printStatus();
    	r.printStatus();
    	
    	return r.count;
    }
    
    public static LinkedBlockingQueue<String> search(BufferedReader data, String query, int num){
        try{
            return search5(data,query,num);
        }
        catch (InterruptedException e){
            System.out.println("Search interrupted.");
            throw new RuntimeException("Unexpected search interruption");
        }
    }
}
