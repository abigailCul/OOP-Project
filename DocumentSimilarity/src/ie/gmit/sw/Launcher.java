package ie.gmit.sw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

public class Launcher {
	private int k =0;
	private int[] minhashes; // The random stuff
	private Map<Integer, List<Integer>> map = new HashMap<>();

	public Launcher(String file1, String file2, int ss) throws InterruptedException {
		BlockingQueue<Shingle> q = new LinkedBlockingQueue<>();	
		
		k = ss;
		Random random =new Random();
		
		minhashes = new int [k];
		//populating minHashes table with random integers
		for (int i=0;i<minhashes.length;i++) {
			minhashes[i] = random.nextInt();
}
		
		
			Map <Integer,List<Integer>> m1 = new HashMap<>();
			Map <Integer,List<Integer>> m2 = new HashMap<>();
				
			// threadPoolSize	
			
		Thread t1 = new Thread(new Parser(file1, q, k),"T1");
		Thread t2 = new Thread(new Parser(file2, q, k),"T2");
		
		// t3 for consumer
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
				//float result = 0.0f;
				float result = Jaccard(m1.get(0),m2.get(0));
				
				System.out.println("------------------------------------------------------");
				System.out.println("How similar : "+result+"%");				
		
	}

//////////Get Jaccard Results
float Jaccard(List<Integer> a,List<Integer> b) {

	float result = (float) 0.0;

	List<Integer> intersection = new ArrayList<Integer>(a);
	intersection.retainAll(b);
	result = ((float)intersection.size()/k)*100;

	System.out.println("Min Hashes "+ intersection.size());

	return result;
  }
}
