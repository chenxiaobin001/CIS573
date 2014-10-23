package edu.upenn.cis573.awesomecache;

import static org.junit.Assert.*;

import java.util.Date;

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
	public void testFirstCacheEntryLeastFrequent(){
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
	public void testMiddleCacheEntryLeastFrequent(){
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
	public void testLastCacheEntryLeastRecent(){
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

}
