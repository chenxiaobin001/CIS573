package edu.upenn.cis573.awesomecache;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LRUCacheRemoveTest {

	private Cache cache;
	private int initSize = 8;
	private int maxSize;
	@Before
	public void setUp() throws Exception {
		//test LRUCache
		maxSize = initSize;
		cache = new LRUCache(initSize);
	}
	
	//inputs: key, entries

	@Test
	public void testWithNullKeyAndEmptyEntries() {
		// key = null, entries.length = 0
		boolean expected = false;
		boolean actual = cache.remove(null);
		assertEquals(expected, actual);
	}

	@Test
	public void testWithNullKeyAndNonEmptyEntries(){
		// key = "test1", entries.length > 0 && < maxLength (8)
		boolean expected = true;
		String key = "test1";
		cache.entries[0] = new CacheEntry("test1");
		cache.entries[1] = new CacheEntry("test2");
		boolean actual = cache.remove(key);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testWithNullKeyAndEntriesFullSize(){
		// key = "nonExist", entries.length == maxLength (8)
		int maxLength = maxSize;
		String key = "nonExist";
		boolean expected = false;
		for (int i = 0; i < maxLength; i++){
			cache.entries[i] = new CacheEntry("test" + Integer.toString(i));
		}
		boolean actual = cache.remove(key);
		assertEquals(expected, actual);
	}
}
