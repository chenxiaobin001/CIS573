package edu.upenn.cis573.awesomecache;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CacheGetTest {

	private Cache cache;
	private int initSize = 8;
	
	//input: key, entries, hit, misses
	@Before
	public void setUp() throws Exception {
		//use lruCache, but test general cache get() that does not invoke replace().
		cache = new LRUCache(initSize);
		
	}

	@Test
	public void testNullKeyNegativeHitsMissesEmptyCacheEntryEmptyHistory() {
		// null key, hits = -8, misses = -8, empty history entry, empty cache entries
		String key = null;
		CacheResult expected = null;
		cache.hits = -8;
		cache.misses = -8;
		int expectedHits = cache.hits;
		int expectedMisses = cache.misses;
		CacheResult actual = cache.get(key);
		int actualHits = cache.hits;
		int actualMisses = cache.misses;
		
		assertEquals(expectedHits, actualHits);
		assertEquals(expectedMisses, actualMisses);
		assertTrue(expected == actual);
	}
	
	

	@Test
	public void testExistingKeyPositiveHitsMissesOneCacheEntryOneHistory() {
		// Existing key, hits = 0, misses = 0, one history entry, one cache entry.
		String key = "test";
		CacheResult expected = new CacheResult(true, key);
		cache.entries[0] = new CacheEntry(key);
		cache.history[0] = "test0";
		cache.hits = 0;
		cache.misses = 0;
		int expectedHits = cache.hits + 1;
		int expectedMisses = cache.misses;
		long preAccessTime = System.currentTimeMillis();
		CacheResult actual = cache.get(key);
		long postAccessTime = System.currentTimeMillis();
		int actualHits = cache.hits;
		int actualMisses = cache.misses;
		long actualAccessTime = cache.entries[0].accesses[0];
		String actualHistory = cache.history[1];
		
		assertEquals(key, actualHistory);
		assertTrue(actualAccessTime <= postAccessTime && actualAccessTime >= preAccessTime);
		assertEquals(expectedHits, actualHits);
		assertEquals(expectedMisses, actualMisses);
		assertTrue(expected.isHit() == actual.isHit());
		assertTrue(expected.getKey() == actual.getKey());
	}
	
	@Test
	public void testExitingKeyPositiveHitsMissesSomeCacheEntriesSomeHistory() {
		// Existing key, hits = 1, misses = 1, some history entries(3), some cache entries(3)
		String key = "test";
		CacheResult expected = new CacheResult(true, key);
		cache.entries[0] = new CacheEntry("test0");
		cache.history[0] = "test0";
		cache.entries[1] = new CacheEntry("test1");
		cache.history[1] = "test1";
		cache.entries[2] = new CacheEntry(key);
		cache.history[2] = "test2";
		cache.hits = Integer.MAX_VALUE;
		cache.misses = Integer.MAX_VALUE;
		long expectedHits = (long)cache.hits + 1;
		int expectedMisses = cache.misses;
		long preAccessTime = System.currentTimeMillis();
		CacheResult actual = cache.get(key);
		long postAccessTime = System.currentTimeMillis();
		long actualHits = (long)cache.hits;
		int actualMisses = cache.misses;
		long actualAccessTime = cache.entries[2].accesses[0];
		String actualHistory = cache.history[3];
		
		assertEquals(key, actualHistory);
		assertTrue(actualAccessTime <= postAccessTime && actualAccessTime >= preAccessTime);
		assertEquals(expectedHits, actualHits);
		assertEquals(expectedMisses, actualMisses);
		assertTrue(expected.isHit() == actual.isHit());
		assertTrue(expected.getKey() == actual.getKey());
		
	}
	
	
	@Test
	public void testNonExitingKeySomeCacheEntriesLRU() {
		// Non-existing key, hits = 1, misses = 1, some history entries(3), fullSize cache entries
		cache = new LRUCache(initSize);
		String key = "NonExist";
		CacheResult expected = new CacheResult(false, null);
		int len = cache.entries.length;
		long time = System.currentTimeMillis();
		for (int i = 0; i < len - 1; i++){
			cache.entries[i] = new CacheEntry("test" + Integer.toString(i));
			cache.entries[i].accesses[0] = time - 1000 + i;
		}
		cache.history[0] = "test0";
		cache.history[1] = "test1";
		cache.history[2] = "test2";
		cache.hits = 1;
		cache.misses = 1;
		int expectedHits = cache.hits;
		int expectedMisses = cache.misses + 1;
		long preAccessTime = System.currentTimeMillis();
		CacheResult actual = cache.get(key);
		long postAccessTime = System.currentTimeMillis();
		int actualHits = cache.hits;
		int actualMisses = cache.misses;
		long actualAccessTime = 0;
		for (CacheEntry ce: cache.entries){
			if (ce != null && ce.key == key){
				System.out.println("a");
				actualAccessTime = ce.accesses[0];
				System.out.println(actualAccessTime);
			}
		}
		String actualHistory = cache.history[3];
		
		assertEquals(key, actualHistory);
		assertEquals(expectedHits, actualHits);
		assertEquals(expectedMisses, actualMisses);
		assertTrue(expected.isHit() == actual.isHit());
		assertTrue(expected.getKey() == actual.getKey());
		assertTrue(actualAccessTime <= postAccessTime && actualAccessTime >= preAccessTime);
		
	}
	
	
	@Test
	public void testNonExitingKeySomeCacheEntriesLFU() {
		// Non-existing key, hits = 1, misses = 1, some history entries(3), fullSize cache entries
		cache = new LFUCache(initSize);
		String key = "NonExist";
		CacheResult expected = new CacheResult(false, null);
		int len = cache.entries.length;
		long time = System.currentTimeMillis();
		for (int i = 0; i < len - 1; i++){
			cache.entries[i] = new CacheEntry("test" + Integer.toString(i));
			cache.entries[i].accesses[0] = time - 1000 + i;
		}
		cache.history[0] = "test0";
		cache.history[1] = "test1";
		cache.history[2] = "test2";
		cache.hits = 1;
		cache.misses = 1;
		int expectedHits = cache.hits;
		int expectedMisses = cache.misses + 1;
		long preAccessTime = System.currentTimeMillis();
		CacheResult actual = cache.get(key);
		long postAccessTime = System.currentTimeMillis();
		int actualHits = cache.hits;
		int actualMisses = cache.misses;
		long actualAccessTime = 0;
		for (CacheEntry ce: cache.entries){
			if (ce != null && ce.key == key){
				System.out.println("a");
				actualAccessTime = ce.accesses[0];
				System.out.println(actualAccessTime);
			}
		}
		String actualHistory = cache.history[3];
		
		assertEquals(key, actualHistory);
		assertEquals(expectedHits, actualHits);
		assertEquals(expectedMisses, actualMisses);
		assertTrue(expected.isHit() == actual.isHit());
		assertTrue(expected.getKey() == actual.getKey());
		assertTrue(actualAccessTime <= postAccessTime && actualAccessTime >= preAccessTime);
		
	}
	
	@Test
	public void testNonExitingKeySomeCacheEntriesRandom() {
		// Non-existing key, hits = 1, misses = 1, some history entries(3), fullSize cache entries
		cache = new RandomCache(initSize);
		String key = "NonExist";
		CacheResult expected = new CacheResult(false, null);
		int len = cache.entries.length;
		long time = System.currentTimeMillis();
		for (int i = 0; i < len - 1; i++){
			cache.entries[i] = new CacheEntry("test" + Integer.toString(i));
			cache.entries[i].accesses[0] = time - 1000 + i;
		}
		cache.history[0] = "test0";
		cache.history[1] = "test1";
		cache.history[2] = "test2";
		cache.hits = 1;
		cache.misses = 1;
		int expectedHits = cache.hits;
		int expectedMisses = cache.misses + 1;
		long preAccessTime = System.currentTimeMillis();
		CacheResult actual = cache.get(key);
		long postAccessTime = System.currentTimeMillis();
		int actualHits = cache.hits;
		int actualMisses = cache.misses;
		long actualAccessTime = 0;
		for (CacheEntry ce: cache.entries){
			if (ce != null && ce.key == key){
				System.out.println("a");
				actualAccessTime = ce.accesses[0];
				System.out.println(actualAccessTime);
			}
		}
		String actualHistory = cache.history[3];
		
		assertEquals(key, actualHistory);
		assertEquals(expectedHits, actualHits);
		assertEquals(expectedMisses, actualMisses);
		assertTrue(expected.isHit() == actual.isHit());
		assertTrue(expected.getKey() == actual.getKey());
		assertTrue(actualAccessTime <= postAccessTime && actualAccessTime >= preAccessTime);
		
	}
	
	
	@Test
	public void testNonExitingKeyFullCacheEntriesLRU() {
		// Non-existing key, hits = 1, misses = 1, some history entries(3), fullSize cache entries
		cache = new LRUCache(initSize);
		String key = "NonExist";
		CacheResult expected = new CacheResult(false, null);
		int len = cache.entries.length;
		long time = System.currentTimeMillis();
		for (int i = 0; i < len; i++){
			cache.entries[i] = new CacheEntry("test" + Integer.toString(i));
			cache.entries[i].accesses[0] = time - 1000 + i;
		}
		cache.history[0] = "test0";
		cache.history[1] = "test1";
		cache.history[2] = "test2";
		cache.hits = 1;
		cache.misses = 1;
		int expectedHits = cache.hits;
		int expectedMisses = cache.misses + 1;
		long preAccessTime = System.currentTimeMillis();
		CacheResult actual = cache.get(key);
		long postAccessTime = System.currentTimeMillis();
		int actualHits = cache.hits;
		int actualMisses = cache.misses;
		long actualAccessTime = 0;
		for (CacheEntry ce: cache.entries){
			System.out.println(ce.key);
			if (ce.key == key){
				System.out.println("a");
				actualAccessTime = ce.accesses[0];
				System.out.println(actualAccessTime);
			}
		}
		String actualHistory = cache.history[3];
		
		assertEquals(key, actualHistory);
		assertEquals(expectedHits, actualHits);
		assertEquals(expectedMisses, actualMisses);
		assertTrue(expected.isHit() == actual.isHit());
		assertTrue(expected.getKey() == actual.getKey());
		assertTrue(actualAccessTime <= postAccessTime && actualAccessTime >= preAccessTime);
		
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
