package edu.upenn.cis573.awesomecache;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LFUCacheReplaceTest {

	private LFUCache lfu;
	private int initSize = 8;
	
	@Before
	public void setUp() throws Exception {
		lfu = new LFUCache(initSize);
	}

	@Test
	public void testEmptyEntry(){
		
		int len = lfu.entries.length;
		int actual = lfu.replace();
		assertTrue(actual >= 0 && actual < len);
	}
	
	@Test
	public void testOneEntry(){
		
		long time = System.currentTimeMillis();
		lfu.entries[0] = new CacheEntry("test");
		lfu.entries[0].accesses[0] = time;
		int actual = lfu.replace();
		assertTrue(actual != 0);
	}
	
	@Test
	public void testSomeEntries(){
		
		long time = System.currentTimeMillis();
		lfu.entries[0] = new CacheEntry("test1");
		lfu.entries[0].accesses[0] = time + 1;
		lfu.entries[1] = new CacheEntry("test2");
		lfu.entries[1].accesses[0] = time + 2;
		lfu.entries[2] = new CacheEntry("test3");
		lfu.entries[2].accesses[0] = time + 3;
		int actual = lfu.replace();
		assertTrue(actual != 1 && actual != 2 && actual != 0);
	}
	
	@Test 
	public void testFullSizeEntriesFirstCacheEntryLeastFrequent(){
		// test case: ectries num = fullSize, first one is least frequent visited
		int expected = 0;
		long time = System.currentTimeMillis();
		lfu.entries[0] = new CacheEntry("test" + Integer.toString(0));
		lfu.entries[0].access(time - 2000);				//2s ago
		
		for (int i = 1; i < lfu.entries.length; i++){
			lfu.entries[i] = new CacheEntry("test" + Integer.toString(i));
			lfu.entries[i].access(time - 200 + i);
		}
		int actual = lfu.replace();
		assertEquals(expected, actual);
	}

	@Test 
	public void testFullSizeEntriesMiddleCacheEntryLeastFrequent(){
		// test case: ectries num = fullSize, one entry except the first one or the last one 
		// is least frequent visited
		int len = lfu.entries.length;
		int random = (int )(Math.random() * (len - 2) + 1);
		int expected = random;
		long time = System.currentTimeMillis();
		lfu.entries[random] = new CacheEntry("test" + Integer.toString(0));
		lfu.entries[random].access(time - 2000);				//2s ago
		
		for (int i = 0; i < lfu.entries.length; i++){
			if (i == random)	continue;
			lfu.entries[i] = new CacheEntry("test" + Integer.toString(i));
			lfu.entries[i].access(time - 200 + i);
		}
		int actual = lfu.replace();
		assertEquals(expected, actual);
	}
	
	@Test 
	public void testFullSizeEntriesLastCacheEntryLeastFrequent(){
		// test case: ectries num = fullSize, last one is least frequent visited
		int len = lfu.entries.length;
		int expected = lfu.entries.length - 1;
		long time = System.currentTimeMillis();
		for (int i = 0; i < len - 1; i++){
			lfu.entries[i] = new CacheEntry("test" + Integer.toString(i));
			lfu.entries[i].access(time - 200 + i);
		}
		lfu.entries[len - 1] = new CacheEntry("test");
		lfu.entries[len - 1].access(time - 2000);		//2s ago
		int actual = lfu.replace();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFullSizeEntriesWithTies(){
		// test case: ectries num = fullSize, entry 1 and entry 3 are tied
		int len = lfu.entries.length;
		long time = System.currentTimeMillis();
		for (int i = 0; i < len; i++){
			lfu.entries[i] = new CacheEntry("test" + Integer.toString(i));
			lfu.entries[i].access(time - 200 + i);
		}
		lfu.entries[1] = new CacheEntry("test" + Integer.toString(1));
		lfu.entries[1].access(time - 2000);
		lfu.entries[3] = new CacheEntry("test" + Integer.toString(3));
		lfu.entries[3].access(time - 2000);
		int actual = lfu.replace();
		assertTrue(actual == 1 || actual == 3);

	}

}
