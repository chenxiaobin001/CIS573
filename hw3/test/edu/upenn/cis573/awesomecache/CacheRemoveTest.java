package edu.upenn.cis573.awesomecache;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CacheRemoveTest {

	private Cache cache;
	private int initSize = 8;
	
	@Before
	public void setUp() throws Exception {
		//use LRUCache to test
		cache = new LRUCache(initSize);
	}
	
	//inputs: key, entries

	@Test
	public void testWithNullKeyAndEmptyEntries() {
		// key = null, entry num = 0
		boolean expected = false;
		boolean actual = cache.remove(null);
		assertEquals(expected, actual);
	}

	@Test
	public void testWithExistingKeyAndOneEntry(){
		// key = "test1", entries.length = 1
		boolean expected = true;
		String key = "test1";
		cache.entries[0] = new CacheEntry("test1");
		boolean actual = cache.remove(key);
		
		assertEquals(expected, actual);
		for (CacheEntry ce : cache.entries){
			if (ce != null){
				assertTrue(ce.getKey() != key);
			}
		}
	}
	
	@Test
	public void testWithExistingKeyAnd7Entries(){
		// key = "nonExist", entries.length == maxLength - 1 (7)
		int maxLength = cache.maxEntries;
		String key = "test1";
		boolean expected = true;
		for (int i = 0; i < maxLength - 1; i++){
			cache.entries[i] = new CacheEntry("test" + Integer.toString(i));
		}
		boolean actual = cache.remove(key);
		
		assertEquals(expected, actual);
		for (CacheEntry ce : cache.entries){
			if (ce != null){
				assertTrue(ce.getKey() != key);
			}
		}
	}
	
	@Test
	public void testWithNonExistingKeyAndEntriesFullSize(){
		// key = "nonExist", entries.length == maxLength (8)
		int maxLength = cache.maxEntries;
		String key = "nonExist";
		boolean expected = false;
		for (int i = 0; i < maxLength; i++){
			cache.entries[i] = new CacheEntry("test" + Integer.toString(i));
		}
		boolean actual = cache.remove(key);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testWithNonExistingKeyAndEntriesNonFullSize(){
		// key = "nonExist", entries.length == 3
		String key = "nonExist";
		boolean expected = false;
		for (int i = 0; i < 3; i++){
			cache.entries[i] = new CacheEntry("test" + Integer.toString(i));
		}
		boolean actual = cache.remove(key);
		assertEquals(expected, actual);
	}
}
