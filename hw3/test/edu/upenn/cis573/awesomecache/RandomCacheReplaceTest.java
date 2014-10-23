package edu.upenn.cis573.awesomecache;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RandomCacheReplaceTest {

	private RandomCache randomCache;
	private int initSize = 8;
	
	@Before
	public void setUp() throws Exception {
		randomCache = new RandomCache(initSize);
	}

	@Test
	public void testRandomEntry() {
		
		int times = 1000000;
		int len = randomCache.entries.length;
		int count[] = new int[randomCache.entries.length];
		for (int i = 0; i < len; i++)	count[i] = 0;
		for (int i = 0; i < times; i++){		//run 1000000 times
			int index = randomCache.replace();
			assertTrue(index >=0 && index < len);
			count[index]++;
		}
		for (int i = 0; i < len; i++){
			System.out.println((double)count[i]/times);
		}
		for (int i = 0; i < len; i++){
			
			assertTrue(Math.abs((double)count[i]/times - 1.0/len) / (1.0/len) <= 0.1);
		}
	}

}
