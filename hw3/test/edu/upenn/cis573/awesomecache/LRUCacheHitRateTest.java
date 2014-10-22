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
	public void testHitRate() {
		fail("Not yet implemented");
	}

}
