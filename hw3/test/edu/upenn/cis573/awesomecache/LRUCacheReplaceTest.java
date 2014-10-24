package edu.upenn.cis573.awesomecache;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class LRUCacheReplaceTest {
	
	private LRUCache lru;
	private int initSize = 8;
	private Date date;
	
	@Before
	public void setUp() throws Exception {
		lru = new LRUCache(initSize);
		date = new Date();
	}

	@Test
	public void testEmptyCacheEntry() {
		// test case: entries num = 0
		int actual = lru.replace();
		int size = lru.entries.length;
		assertTrue(actual >=0 && actual < size);
	}
	
	@Test
	public void testOneCacheEntries() {
		// test case: entries num = 1
		lru.entries[1] = new CacheEntry("test1");
		int actual = lru.replace();
		assertTrue(actual != 1);
	}
	
	@Test
	public void testSomeCacheEntries() {
		// test case: entries num = 3
		lru.entries[1] = new CacheEntry("test1");
		lru.entries[3] = new CacheEntry("test3");
		lru.entries[5] = new CacheEntry("test5");
		int actual = lru.replace();
		assertTrue(actual != 1 && actual != 3 && actual != 5);
	}
	
	@Test 
	public void testFullCacheEntryFirstLeastRecent(){
		// test case: ectries num = fullSize, first one is least recent visited
		int expected = 0;
		long time = System.currentTimeMillis();
		for (int i = 0; i < lru.entries.length; i++){
			lru.entries[i] = new CacheEntry("test" + Integer.toString(i));
			lru.entries[i].accesses[0] = time + i;
		}
		int actual = lru.replace();
		assertEquals(expected, actual);
	}
	
	@Test 
	public void testFullCacheEntryTies(){
		// test case: ectries num = fullSize, entry 1 and 2 tied for least-recently used
		int expected = 0;
		for (int i = 0; i < lru.entries.length; i++){
			lru.entries[i] = new CacheEntry("test" + Integer.toString(i));
		}
		long time = System.currentTimeMillis();
		lru.entries[0].accesses[0] = time;
		lru.entries[0].accesses[1] = time;
		lru.entries[1].accesses[0] = time;
		lru.entries[1].accesses[1] = time + 1;
		for (int i = 2; i < lru.entries.length; i++){
			lru.entries[i].accesses[0] = time + i;
		}
		int actual = lru.replace();
		assertEquals(expected, actual);
	}

}
