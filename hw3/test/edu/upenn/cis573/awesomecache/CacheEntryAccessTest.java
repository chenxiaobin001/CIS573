package edu.upenn.cis573.awesomecache;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class CacheEntryAccessTest {
	
	private CacheEntry cacheEntry;
	private CacheEntry cacheEntryNullKey;
	private Date date;

	@Before
	public void setUp() throws Exception {
		
		cacheEntry = new CacheEntry("test");
		cacheEntryNullKey = new CacheEntry(null);
		date = new Date();
		
	}

	@Test
	public void testNegtiveCountValue() {	
		//test case: count < 0, 
		
		cacheEntry.count = -1;
		int actualCount = cacheEntry.count;
		assertTrue(actualCount >= 0);
	}
	
	@Test
	public void testNullKey(){
		assertTrue(cacheEntryNullKey.getKey() != null);
	}
	
	@Test
	public void testAccess1Times(){
		// test case (access times = 1, count = 1)
		
		long actualTime[] = new long[10];
		for (int i = 0; i < 10; i++)	actualTime[i] = -1;
		int expectedCount = 1;
		long accessTime = date.getTime();
		actualTime[0] = accessTime; 
		cacheEntry.access(accessTime);
		int actualCount = cacheEntry.count;
		assertEquals(expectedCount, actualCount);
		assertArrayEquals(actualTime, cacheEntry.getAccesses());
	}
	
	@Test
	public void testAccessedLessThan11Times() {
		// test case (access times = 9, count = 9)
		
		long actualTime[] = new long[10];
		int len = 9;
		for (int i = 0; i < 10; i++)	actualTime[i] = -1;
		int expectedCount = len;
		for (int i = 0; i < len; i++){
			long accessTime = date.getTime();
			actualTime[len - 1 - i] = accessTime; 
			cacheEntry.access(accessTime);
		}
		int actualCount = cacheEntry.count;
		assertEquals(expectedCount, actualCount);
		assertArrayEquals(actualTime, cacheEntry.getAccesses());
	}
	
	@Test
	public void testAccessedMoreThan11Times(){
		// test case (access times = 12, count = 12)
		long actualTime[] = new long[10];
		int len = 12;
		for (int i = 0; i < 10; i++)	actualTime[i] = -1;
		int expectedCount = len;
		for (int i = 0; i < len; i++){
			long accessTime = date.getTime();
			if (len - 1 - i <= 9)	actualTime[len - 1 - i] = accessTime; 
			cacheEntry.access(accessTime);
		}
		int actualCount = cacheEntry.count;
		assertEquals(expectedCount, actualCount);
		assertArrayEquals(actualTime, cacheEntry.getAccesses());
	}
	
	
}
