package edu.upenn.cis573.awesomecache;

public class LFUCache extends Cache {

	public LFUCache(int _initialSize) {
		super(_initialSize);
	}


	/*
	 * Uses a Least-Frequently Used replacement algorithm to determine
	 * which CacheEntry to evict. Works by looping through all CacheEntry
	 * objects in the entries array and finding out how many times they were
	 * accessed in the past second. If none were accessed in the past second,
	 * it sees how many times each was accessed in the past two seconds, and so
	 * on. It then chooses the one that was accessed the fewest times in that
	 * "window" and returns the index of that entry.
	 * If two or more CacheEntries have been accessed equally infrequently,
	 * the one that it chooses to evict is not defined. 
	 */
	public int replace() {
		// first see if there are any empty spots
		for (int i = 0; i < entries.length; i++) {
			if (entries[i] == null) {
				return i;
			}
		}
		
		// if not, then we have to evict the entry that was
		// least frequently used
		
		// to do this, we look at the number of accesses in the last second
		// if there are none, we look at the number in the last two seconds, and so on
		
		int window = 1; // number of seconds back in time in which to look
		
		long now = System.currentTimeMillis();
		
		// keep track of how many times each entry was accessed in the time window
		int numAccesses[] = new int[entries.length];
		
		while (true) {
			// reset the array
			for (int i = 0; i < numAccesses.length; i++) numAccesses[i] = 0;

			// update the array with the number of accesses within the window
			for (int i = 0; i < entries.length; i++) {
				if (entries[i] != null) {
					long[] times = entries[i].getAccesses();
					for (int j = 0; j < times.length; j++) {
						if (now - times[j] < window) numAccesses[i]++;
					}
				}
			}
			// now find the index of the entry with the smallest number of accesses
			// in that time window
			int min = numAccesses[0];
			int index = 0;
			for (int i = 1; i < numAccesses.length; i++) {
				if (numAccesses[i] < min) {
					min = numAccesses[i];
					index = i;
				}
			}
			// see if we have a winner... if all values in numAccesses are zero,
			// then nothing was accessed in that window
			boolean found = false; // whether or not we found something
			for (int i = 0; i < numAccesses.length; i++) {
				if (numAccesses[i] > 0) found = true;
			}
			// if found is true, then we must have accessed something during that time
			if (found) return index;
			// otherwise, we never accessed anything, so increase the window
			else window++;
		}
		
		
	}

}
