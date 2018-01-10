package ie.gmit.sw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.text.Position;


public class Consumer implements Runnable {
	private static final Collection a = null;
	private BlockingQueue<Shingle> queue;
	private int k;
	private int[] minhashes; // The random stuff
	private Map<Integer, List<Integer>> map = new HashMap<>();
	private ExecutorService pool;

	public Consumer(BlockingQueue<Shingle>q, int k, int poolSize) {
		this.queue = q;
		this.k = k;
	//	pool = Executors.fixedSizeThreadPool(poolSize);
		init();
	}

	private void init() {
		Random random = new Random();
		minhashes = new int[k]; // k = 200 - 300
		for(int i = 0; i < minhashes.length; i++) {
			minhashes[i] = random.nextInt(0);
		}
	}// init
	
	public void run() {
		int docCount = 1; // FIX
		int max = Integer.MAX_VALUE;
		while(docCount > 0) {

			try {
				
			
			Shingle s = queue.take(); // Blocking method
			if(s instanceof Position) {
				docCount--;
			}
			
					for(int i = 0; i < minhashes.length; i++) {
						int value = s.hashCode()^minhashes[i]; // ^ - xor(Random generated key)
						List<Integer> list = map.get(s.getDocId());
						if(list == null) {					// Happens once for each document
							list = new ArrayList<Integer>(k); // k - size   //
							for (int j =0; j < k; j++) {		//
								list.add(j , Integer.MAX_VALUE);	//
							}						//
							map.put((Integer) s.getDocId(), list);			//
						}//if
						else {
							if(list.get(i) > value) {
							
								list.set(i, value);
							}	
						
					}//  Else
						for( i = 0;i<minhashes.length;i++) {
							
							 value = s.getHashCode() ^ minhashes[i];
											
							//populate with minimum hash calculated this far
							if(list.get(i) > value) {
								list.set(i, value);
							}
						}	
			}// For
					
		}// While
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// this is a blocking method
		}
	}
}
	

