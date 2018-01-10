package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

public class Parser implements Runnable {
	private BlockingQueue<Shingle> queue;
	private String file;
	private int shignleSize, k;
	private Deque<String> buffer = new LinkedList<>();
	private int docId;
		

	/*variables 
 	file which is being parsed
 	k is the number of shingles that generate hash*/
	public Parser(String file, BlockingQueue<Shingle> q,int shingleSize) {
		this.queue = q;
		this.file=file;
		this.shignleSize=shignleSize;
		this.k=k;
	}
	
	public void run() {
		try {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));		
		String line = null;
		while((line = br.readLine())!= null) {
			String uLine = line.toUpperCase();
			String[] words = uLine.split(" "); // Can also take a regexpression
			addWordsToBuffer(words);
			Shingle s = getNextShingle();
			queue.put(s); // Blocking method. Add is not a blocking method
		
		}
		flushBuffer();
		br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}// Run


	private void addWordsToBuffer(String [] words) {
		for(String s : words) {
			buffer.add(s);
		}
  
        }

  	private Shingle getNextShingle() {
		StringBuffer sb = new StringBuffer();
		int counter = 0;
		int shingleSize = 0;
		while(counter < shingleSize) {
			if(buffer.peek() != null) {
				sb.append(buffer.poll());
				counter++;
			}
		}  
		if (sb.length() > 0) {
			return(new Shingle(docId, sb.toString().hashCode()));
		}
		else {
			return(null);
		}
  	} // Next shingle
	
  	public class Poison extends Shingle {

		public Poison(int docId, int hashcode) {
			super(docId, hashcode);
			// TODO Auto-generated constructor stub
		}
  		// Source -> Generate constructors FROM superclass	
  	}

	private void flushBuffer() throws InterruptedException {
		while(buffer.size() > 0) {
			Object Shingle;
			Shingle s = getNextShingle();
			if(s != null) {
				queue.put(s);
			}
			else {
				queue.put(new Poison(0,0));
			}
		}
	}

        
  }//Parser