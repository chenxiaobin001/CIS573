package edu.upenn.cis573.awesomecache;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LRUCacheHitRateTest {

	private Cache cache; 
	private int initSize = 8;
	@Before
	public void setUp() throws Exception {
		//test LRUCache
		cache = new LRUCache(initSize);
		
	}
	
	//input hits, misses
	@Test
	public void testZeroHitZeroMiss() {
		// test case (hits = 0, misses = 0)
		double expected = 0;
		double actual = cache.hitRate();
	//	System.out.println(actual);
		assertTrue(actual >= 0.0 && actual <= 1.0);
		assertTrue(Double.compare(expected, actual) == 0);
	}
	
	@Test
	public void testNegativeHitsPositiveMisses() {
		// test case (hits = -1, misses = 1)
		cache.hits = -1;
		cache.misses = 1;
		double hitRate = cache.hitRate();
		assertTrue(hitRate >= 0.0 && hitRate <= 1.0);
		
	}
	
	@Test
	public void testPositiveHitsNegativeMisses() {
		// test case (hits = 1, misses = -1)
		cache.hits = 1;
		cache.misses = -1;
		double hitRate = cache.hitRate();
		assertTrue(hitRate >= 0.0 && hitRate <= 1.0);
		
	}
	
	@Test
	public void testSomeHitsSomeMisses() {
		// test case (hits = 3, misses = 5)
		cache.hits = 3;
		cache.misses = 5;
		double hitRate = cache.hitRate();
		assertTrue(Double.compare(hitRate, 3.0/8) == 0);
		assertTrue(hitRate >= 0.0 && hitRate <= 100.0);
	
	}
	
	@Test 
	public void testAllHitsZeroMisses() {
		// test case (hits = 1, misses = 0)
		cache.hits = 1;
		cache.misses = 0;
		double hitRate = cache.hitRate();
		assertTrue(Double.compare(hitRate, 1.0) == 0);
		assertTrue(hitRate >= 0.0 && hitRate <= 100.0);

	}
	
	
}
