package edu.upenn.cis573.awesomecache;

public class LRUCache extends Cache {

	public LRUCache(int _maxEntries) {
		super(_maxEntries);
	}


	/*
	 * Uses a Least-Recently Used replacement algorithm to determine
	 * which CacheEntry to evict. Works by looping through all CacheEntry
	 * objects in the entries array and finding the one whose most recent
	 * access was farthest in the past and returns the index of that entry.
	 * If two or more CacheEntries are tied for least-recently used (which is pretty 
	 * much impossible, but whatever), then it should break ties by choosing
	 * the one whose second-most-recent access was farthest in the past, and
	 * so on. 
	 */
	public int replace() {
		// first see if there are any empty spots in the entries array
		for (int i = 0; i < entries.length; i++) {
			if (entries[i] == null) {
				// found an empty spot, so we'll put the CacheEntry there
				return i;
			}
		}
		
		// if not, then we have to evict the entry that was
		// least recently used
		
		// loop through and find the largest access time
		long time = 0;
		int index = 0;
		for (int i = 0; i < entries.length; i++) {
			if (entries[i].getAccesses()[0] > time) {
				time = entries[i].getAccesses()[0];
				index = i;
			}
		}

		return index;
	}

}
