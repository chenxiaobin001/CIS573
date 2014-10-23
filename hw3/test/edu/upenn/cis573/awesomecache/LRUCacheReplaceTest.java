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
		int expected = 0;
		int actual = lru.replace();
		assertEquals(expected, actual);
	}
	
	@Test 
	public void testFullCacheEntryFirstLeastRecent(){
		// test case: ectries num = fullSize, first one is least recent visited
		int expected = 0;
		for (int i = 0; i < lru.entries.length; i++){
			lru.entries[i] = new CacheEntry("test" + Integer.toString(i));
			lru.entries[i].access(date.getTime() + i);
		}
		int actual = lru.replace();
		assertEquals(expected, actual);
	}
	
	@Test 
	public void testFullCacheEntryLastLeastRecent(){
		// test case: ectries num = fullSize, last one is least recent visited
		int expected = lru.entries.length - 1;
		for (int i = 0; i < lru.entries.length; i++){
			lru.entries[i] = new CacheEntry("test" + Integer.toString(i));
		}
		for (int i = 0; i < lru.entries.length; i++){
			lru.entries[lru.entries.length - 1 - i].access(date.getTime() + i);
		}
		int actual = lru.replace();
		assertEquals(expected, actual);
	}

}
