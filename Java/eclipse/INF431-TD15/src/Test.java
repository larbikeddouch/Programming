import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


import java.io.Console;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.HashMap;
import java.util.regex.Pattern;

import search.Manager;

public class Test {
    	public static final String datafile = "/home/xavier/Programming/Java/eclipse/INF431-TD15/data/titles.txt";
    //  public static final String datafile = "titles.txt"; // For laptops, download local copy
	public static LinkedBlockingQueue<String> result;
	public static HashMap<String,Integer> count;
	
	public static void searchForm(String file) throws FileNotFoundException{
		BufferedReader data = new BufferedReader(new FileReader(datafile));
		Console c = System.console();
		if (c == null) {System.out.println("Run this program from the terminal window, not from Eclipse."); return;}
		String q = c.readLine("Search for [Asterix]: ");
		if (q.equals("")) q = "Asterix";
 		String num = c.readLine("Number of results [10]: ");
 		if (num.equals("")) num = "10";
 		
 		int n = Integer.parseInt(num);
 		
		long time = System.currentTimeMillis();
		result = Manager.search(data, q, n);
		
		
	}
    /*
	public static void frequencyForm(String file) throws FileNotFoundException{
		BufferedReader data = new BufferedReader(new FileReader(datafile));
		Console c = System.console();
		if (c == null) {System.out.println("Run this program from the terminal window, not from Eclipse."); return;}
		String q = c.readLine("Count number of occurrences for [Asterix,Obelix]: ");
		if (q.equals("")) q = "Asterix,Obelix";
		String[] sa = q.split(",");
 		
		long time = System.currentTimeMillis();
		count = Manager.count(data, sa);
		
		
	}
    */
	
	public static void main(String[] args) throws FileNotFoundException{
		
		BufferedReader data = new BufferedReader(new FileReader(datafile));
		
		result = Manager.search(data,"Asterix",100);
		
		/*for (String res: result){
			System.out.println(res);
		}*/
		
                /*
		String[] queries = {"Asterix","Obelix"};
		count = Manager.count(data,queries);
		for (String res: count.keySet()){
			System.out.println(res + " occurs "+ count.get(res) + " times.");
		}
                */
		
		/* For command-line testing:
		searchForm(datafile);
		
		for (String res: result){
			System.out.println(res);
		}
		*/
		/* For command-line testing:
		frequencyForm(datafile);
		for (String res: count.keySet()){
			System.out.println(res + " occurs "+ count.get(res) + " times.");
		}
		*/
		
	}
}
