package edu.upenn.cis573.awesomecache;

public class RandomCache extends Cache {

	public RandomCache(int _maxEntries) {
		super(_maxEntries);
	}

	/*
	 * The strategy here is simple: just pick one entry at random
	 * and evict it.
	 */
	public int replace() {
		
		return (int)(Math.random() * entries.length);


	}

}
