package edu.upenn.cis573.awesomecache;

/*
 * Represents a single entry in the cache.
 * 
 */
public class CacheEntry {

	// indicates that we should keep track of the last 10 access times
	public static final int NUM_ACCESSES = 10;
	
	protected String key; // the key for this entry
	protected long[] accesses; // the times of the last 10 accesses
	protected int count; // the number of times this entry has been accessed
	
	public CacheEntry(String _key) {
		key = _key;
		accesses = new long[NUM_ACCESSES];
		for (int i = 0; i < accesses.length; i++) accesses[i] = -1;
		count = 0;
		/*
		 * Note: ideally, the constructor would go to persistent storage and load
		 * the data for this key, but for simplicity we're not going to do that!
		 */
	}
	
	/*
	 * Updates the number of accesses and the array of access times
	 * The times are to be kept in order, with the first entry the most recent,
	 * the second entry the second most recent, etc.
	 */
	public void access(long time) {
		// update the count
		count++;
		
		// shift each entry to the "right"
		for (int i = accesses.length-1; i > 0; i--)
			accesses[i] = accesses[i-1];
		// set the first entry to this time
		accesses[0] = time;
	}
	
	public String getKey() { return key; }

	public long[] getAccesses() { return accesses; }

}
