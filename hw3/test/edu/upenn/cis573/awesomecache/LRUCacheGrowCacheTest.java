package edu.upenn.cis573.awesomecache;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LRUCacheGrowCacheTest {

	private Cache cache;
	private int initSize = 8;
	
	//inputs: initialSize, entries
	@Rule public ExpectedException thrown= ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		//test LRUCache
		cache = new LRUCache(initSize);
	}
	
	@Test
	public void testNegativeInitialSizeEmptyCacheEntry(){
		//test case (initialSize = -8) to see if exception is thrown
		thrown.expect( IllegalArgumentException.class );
		cache = new LRUCache(-8);
		cache.growCache();
	}
	
	@Test
	public void testZeroInitialSizeEmptyEntries() {
		//test case (InitialSize = 0) 
		cache = new LRUCache(0);
		int initSize = cache.initialSize;
		int originalSize = cache.entries.length;
		cache.growCache();
		int newSize = cache.entries.length;
		assertTrue(newSize == originalSize + initSize);
	}
	
	@Test
	public void testPositiveInitialSizeOneCacheEntries(){
		//test case (InitialSize = 8, CacheEntry num = 1)
		int initSize = cache.initialSize;
		cache.entries[0] = new CacheEntry("test1");
		int originalSize = cache.entries.length;
		cache.growCache();
		int newSize = cache.entries.length;
		assertTrue(newSize == originalSize + initSize);
	}
	
	@Test
	public void testPositiveMaxSizeMaxnumSubtractOneCacheEntries(){
		//test case (MaxCacheEntrySize = 8, CacheEntry num = 7)
		int initSize = cache.initialSize;
		for (int i = 0; i < cache.entries.length - 1; i++){
			cache.entries[i] = new CacheEntry("test" + Integer.toString(i));
		}
		int originalSize = cache.entries.length;
		cache.growCache();
		int newSize = cache.entries.length;
		assertTrue(newSize == originalSize + initSize);
	}
	
	@Test
	public void testPositiveMaxSizeMaxnumCacheEntries(){
		//test case (MaxCacheEntrySize = 8, CacheEntry num = 8)
		int initSize = cache.initialSize;
		for (int i = 0; i < cache.entries.length; i++){
			cache.entries[i] = new CacheEntry("test" + Integer.toString(i));
		}
		int originalSize = cache.entries.length;
		cache.growCache();
		int newSize = cache.entries.length;
		assertTrue(newSize == originalSize + initSize);
	}
}
