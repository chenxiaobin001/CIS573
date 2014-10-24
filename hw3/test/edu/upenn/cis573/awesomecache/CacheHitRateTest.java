package edu.upenn.cis573.awesomecache;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CacheHitRateTest {

	private Cache cache; 
	private int initSize = 8;
	@Before
	public void setUp() throws Exception {
		//use LRUCache to test
		cache = new LRUCache(initSize);
		
	}
	
	@Rule public ExpectedException thrown= ExpectedException.none();
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
		thrown.expect( IllegalStateException.class );
		cache.hits = -1;
		cache.misses = 1;
		double hitRate = cache.hitRate();
		
	}
	
	@Test
	public void testMAXHitsMAXMisses() {
		// test case (hits = -1, misses = 1)
		thrown.expect( IllegalStateException.class );
		cache.hits = Integer.MAX_VALUE;
		cache.misses = Integer.MAX_VALUE;
		double hitRate = cache.hitRate();
		assertTrue(hitRate >= 0.0 && hitRate <= 1.0);
		assertTrue(Double.compare(50.0, hitRate) == 0);
	}
	
	@Test
	public void testPositiveHitsNegativeMisses() {
		// test case (hits = 1, misses = -1)
		thrown.expect( IllegalStateException.class );
		cache.hits = 1;
		cache.misses = -1;
		double hitRate = cache.hitRate();
		
	}
	
	@Test
	public void testSomeHitsSomeMisses() {
		// test case (hits = 3, misses = 5)
		cache.hits = 3;
		cache.misses = 5;
		double hitRate = cache.hitRate();
		assertTrue(Double.compare(hitRate, 3.0/8 * 100) == 0);
		assertTrue(hitRate >= 0.0 && hitRate <= 100.0);
	
	}
	
	@Test 
	public void testAllHitsZeroMisses() {
		// test case (hits = 1, misses = 0)
		cache.hits = 1;
		cache.misses = 0;
		double hitRate = cache.hitRate();
		assertTrue(Double.compare(hitRate, 100.0) == 0);
		assertTrue(hitRate >= 0.0 && hitRate <= 100.0);

	}
	
	
	
}
