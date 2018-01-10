package ie.gmit.sw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.text.Position;


public class Consumer implements Runnable {
	private BlockingQueue<Shingle> queue;
	private int k;
	private int[] minhashes; // The random stuff
	private Map<Integer, List<Integer>> map = new HashMap<>();
	private ExecutorService pool;

	public Consumer(BlockingQueue<Shingle>q, int k, int poolSize) {
		this.queue = q;
		this.k = k;
		pool = Executors.fixedSizeThreadPool(poolSize);
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
		int docCount = 2; // FIX THIS
		while(docCount > 0) {
			Shingle s = queue.take(); // Blocking method
			if(s instanceof Position) {
				docCount--;
			}
			else {
				pool.execute(new Runnable());
					for(int i = 0; i < minhashes.length; i++) {
						int value = s.hashCode()^minhashes[i]; // ^ - xor(Random generated key)
						List<Integer> list = map.get(s.getDocId());
						if(list == null) {					// Happens once for each document
							list = new ArrayList<Integer>(k); // k - size   //
							for (int j =0; j < list.length; j++) {		//
								list.set(j > Integer.MAX_VALUE);	//
							}						//
							map.pool(s.getDocId(), list0);			//
						}//if
						else {
							if(list.get(i) > value) {
							
								list.set(i, value);
							}	
						}//else
					}// For
			}// Else
		}// While
	}// Run
	

////////////////////////////////////////////////////////////////////////////////////////

List<Integer> intersection = new ArrayList(a);
intersection.retainAll(b);

float jaccard = ((float)intersection.size()) / ((k*2) + ((float)intersection.size()));
}

