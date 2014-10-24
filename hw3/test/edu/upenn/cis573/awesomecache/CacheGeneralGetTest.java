package edu.upenn.cis573.awesomecache;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CacheGeneralGetTest {

	private Cache cache;
	private int initSize = 8;
	
	//input: key, entries, hit, misses
	@Before
	public void setUp() throws Exception {
		//use lruCache, but test general cache get() that does not invoke replace().
		cache = new LRUCache(initSize);
		
	}

	@Test
	public void testNullKey() {
		// test case: key = null
		String key = null;
		boolean expected = false;
		boolean actual = cache.get(key).isHit();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testExitingKeyCacheEntryNonFullSize() {
		// test case: key = "test", size = 1
		String key = "test";
		cache.entries[0] = new CacheEntry(key);
		
		int oldHits = cache.hits;
		boolean expected = true;
		String expectedKey = key;
		CacheResult cr = cache.get(key);
		int newHits = cache.hits;
		boolean actual = cr.isHit();
		String actualKey = cr.getKey();
		assertEquals(expected, actual);
		assertTrue(newHits == (oldHits + 1));
		assertEquals(expectedKey, actualKey);
		
	}
	
	@Test
	public void testExitingKeyCacheEntryFullSize() {
		// test case: key = "test", size = entries.length
		String key = "test1";
		int len = cache.entries.length;
		for (int i = 0; i < len; i++){
			cache.entries[i] = new CacheEntry("test" + Integer.toString(i));
		}
		
		int oldHits = cache.hits;
		boolean expected = true;
		String expectedKey = key;
		CacheResult cr = cache.get(key);
		int newHits = cache.hits;
		boolean actual = cr.isHit();
		String actualKey = cr.getKey();
		assertEquals(expected, actual);
		assertTrue(newHits == (oldHits + 1));
		assertEquals(expectedKey, actualKey);
		
	}
	
	@Test
	public void testNonExitingKeyCacheEntryNonFullSize() {
		// test case: key = "test", size = entries.length
		String key = "test1";
		int len = cache.entries.length;
		for (int i = 0; i < len; i++){
			cache.entries[i] = new CacheEntry("test" + Integer.toString(i));
		}
		
		int oldHits = cache.hits;
		boolean expected = true;
		String expectedKey = key;
		CacheResult cr = cache.get(key);
		int newHits = cache.hits;
		boolean actual = cr.isHit();
		String actualKey = cr.getKey();
		assertEquals(expected, actual);
		assertTrue(newHits == (oldHits + 1));
		assertEquals(expectedKey, actualKey);
		
	}

}
