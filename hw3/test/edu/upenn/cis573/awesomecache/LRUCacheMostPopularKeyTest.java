package edu.upenn.cis573.awesomecache;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LRUCacheMostPopularKeyTest {

	private Cache cache;
	private int initSize = 8;
	
	@Before
	public void setUp() throws Exception {
		cache = new LRUCache(initSize);
	}

	@Test
	public void testEmptyHistoryRecord() {
		// test case: history has zero record
		String expected = null;
		String actual = cache.mostPopularKey();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testOneHistoryRecord(){
		// test case: one history access record
		String key = "test1";
		cache.history[1] = key;
		String expected = key;
		String actual = cache.mostPopularKey();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testTwoHistoryRecordDifferent(){
		// test case: history records = 2, different record
		String key1 = "test1";
		String key2 = "test2";
		cache.history[1] = key1;
		cache.history[2] = key2;
		String actual = cache.mostPopularKey();
		assertTrue(actual == key1 || actual == key2);
	}
	
	@Test
	public void testHistoryRecordFirstLastSame(){
		// test case: history records = maxLen - 1, first last record are the same
		String key = "test";
		int len = cache.history.length;
		for (int i = 1; i < len - 2; i++){
			cache.history[i] = ("test" + Integer.toString(i));
		}
		cache.history[0] = key;
		cache.history[len - 1] = key;
		String expected = key;
		String actual = cache.mostPopularKey();
		assertEquals(expected, actual);
		
		
	}
	
	@Test
	public void testMaxlenHistoryRecordAllSame(){
		// test case: history records = maxLen, all the records are the same
		String key = "test";
		int len = cache.history.length;
		for (int i = 0; i < len - 1; i++){
			cache.history[i] = key;
		}
		String expected = key;
		String actual = cache.mostPopularKey();
		assertEquals(expected, actual);
	}

}
