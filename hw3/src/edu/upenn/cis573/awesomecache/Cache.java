package edu.upenn.cis573.awesomecache;

/*
 * This is the base class for the different cache implementations.
 */

public abstract class Cache {
	
	public abstract int replace();
	
	protected int maxEntries; // the maximum number of entries this cache will hold
	protected CacheEntry[] entries; // the actual entries
	protected int initialSize; // the initial value of maxEntries
	protected int hits; // the number of times an entry was found in the cache
	protected int misses; // the number of times an entry was not found in the cache
	protected String[] history; // the list of all keys used for retrieving entries
	
	public Cache(int _initialSize) {
		// we need to keep track of the initial size for when we grow the cache
		maxEntries = _initialSize;
		initialSize = _initialSize;
		// initialize the arrays
		entries = new CacheEntry[initialSize];
		history = new String[100];
	}
	
	/*
	 * Removes the CacheEntry with the given key. 
	 * Returns true if an entry with that key was found, and false if not.
	 */
	public boolean remove(String key) {
		if (key == null) return false;
		
		for (int i = 0; i < entries.length; i++) {
			if (entries[i].getKey().equals(key)) {
				entries[i] = null;
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * Returns the hit rate (as a percent) for this cache
	 */
	public double hitRate() {
		return (double)hits/(hits+misses);
	}
	
	/*
	 * Allows you to programmatically increase the max number of entries.
	 * It does so by creating a new array which is "initialSize" greater
	 * than the current array and copying everything over. Note that because
	 * order doesn't matter in the cache, when old entries are copied over
	 * they are moved to the front of the new array (i.e., they don't 
	 * necessarily keep their old location).
	 */
	public boolean growCache() {
		maxEntries += initialSize;
		CacheEntry[] newEntries = new CacheEntry[maxEntries];
		
		// copy over the existing entries
		int count = 0;
		for (int i = 0; i < entries.length; i++) {
			if (entries[i] != null) 
				newEntries[count++] = entries[i];
		}
		
		return true;
		
	}

	/*
	 * Returns the key that was used most often in the "get" method.
	 * If two or more keys are equally popular, it is not defined which
	 * one should be returned. If "get" has never been called on this
	 * object, this method should return null.  
	 */
	public String mostPopularKey() {
		String mostPopular = null;
		int max = 0;
		// iterate over all elements in the history array
		for (int i = 0; i < history.length; i++) {
			if (history[i] != null) {
				String key = history[i];
				int count = 0;
				// look at all the words again and count the number of occurrences
				for (int j = 0; j < history.length; j++) {
					if (history[j] != null && history[j].equals(key)) {
						count++;
					}
				}
				if (count > max) {
					max = count;
					mostPopular = key;
				}
			}
		}
		return mostPopular;
	}

	/*
	 * Sees if the key is present in the CacheEntry array.
	 * If it is, then it is considered a cache hit, and the CacheResult that
	 * is returned has its "hit" field set to true (the value of the "replacedKey"
	 * field is not defined in this case).
	 * If there is no CacheEntry with this key, then it is a cache miss.
	 * In that case, a new CacheEntry with this key should be created and 
	 * added to the entries array. If the entries array is full, then a 
	 * CacheEntry object must be evicted according to the implementation
	 * in the concrete class' "replace" method. For a cache miss, the CacheResult
	 * that is returned has its "hit" field set to false, and its "replacedKey"
	 * field should be equal to the key of the CacheEntry that was evicted.
	 * If the argument to this method is null, the method should return null.
	 */
	public CacheResult get(String key) {
		
		// add this to the list of search keys
		boolean insertedKey = false;
		for (int i = 0; i < history.length; i++) {
			if (history[i] == null) {
				history[i] = key;
				insertedKey = true;
			}
		}
		// see if this one got placed
		if (!insertedKey) {
			// grow the array by adding 100 more elements
			int length = history.length;
			history = new String[length + 100];
			// insert this element at the end of the original
			history[length] = key;
		}
		
		// first see if it's present in the cache
		for (int i = 0; i < entries.length; i++) {
			if (entries[i] != null && entries[i].getKey().equalsIgnoreCase(key)) {
				// it's a hit!
				hits++;
				entries[i].access(System.currentTimeMillis());
				return new CacheResult(true);
			}
		}
		
		// if we get here, it's a miss!

		// need to figure out which one to replace
		int replaceIndex = replace();
		
		// save this for the return value
		String replaceKey = entries[replaceIndex].getKey();
		
		// create a new entry
		CacheEntry entry = new CacheEntry(key);
		
		// put it in the array
		entries[replaceIndex] = entry;
		
		// update
		misses++;
		
		return new CacheResult(false, replaceKey);

	}




}
