package edu.upenn.cis573.awesomecache;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CacheEntryAccessTest {
	
	private CacheEntry cacheEntry;

	@Rule public ExpectedException thrown= ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		
		cacheEntry = new CacheEntry("test");
		
	}

	//inputs: key, access, count
	
	@Test
	public void testNegativeCountValueNegativeTime() {	
		//test case: count < 0, 
		
		thrown.expect( IllegalStateException.class );
		thrown.expect( IllegalArgumentException.class );
		cacheEntry.count = -1;
		cacheEntry.access(-1);
		
	}
	
	@Test
	public void testMAXCountValue() {	
		//test case: count = INT_MAX, 
		
		cacheEntry.count = Integer.MAX_VALUE;
		long expected = (long)Integer.MAX_VALUE + 1;
		cacheEntry.access(System.currentTimeMillis());
		long actual = cacheEntry.count;
		assertEquals(expected, actual);
	}

	@Test
	public void testAccessZeroTimes(){
		// test case (access times = 0, count = 0)
		
		cacheEntry.count = 0;
		int expectedCount = 1;
		long expectTIme[] = new long[10];
		for (int i = 0; i < 10; i++)	expectTIme[i] = -1;
		long accessTime = System.currentTimeMillis();
		expectTIme[0] = accessTime;
		
		cacheEntry.access(accessTime);
		int actualCount = cacheEntry.count;
		assertEquals(expectedCount, actualCount);
		assertArrayEquals(expectTIme, cacheEntry.getAccesses());
	}
	
	@Test
	public void testAccessOneTimes(){
		// test case (access times = 1, count = 1)
		
		cacheEntry.count = 1;
		int expectedCount = 2;
		long expectTime[] = new long[10];
		for (int i = 0; i < 10; i++)	expectTime[i] = -1;
		long accessTime = System.currentTimeMillis();
		expectTime[1] = accessTime;
		cacheEntry.accesses[0] = accessTime;
		
		accessTime = System.currentTimeMillis();
		expectTime[0] = accessTime; 
		cacheEntry.access(accessTime);
		int actualCount = cacheEntry.count;
		assertEquals(expectedCount, actualCount);
		assertArrayEquals(expectTime, cacheEntry.getAccesses());
	}
	
	
	@Test
	public void testAccessedLessThanTenTimes() {
		// test case (access times = 9, count = 9)
		
		cacheEntry.count = 9;
		int expectedCount = 10;
		long expectTime[] = new long[10];
		int len = 10;
		for (int i = 0; i < 10; i++)	expectTime[i] = -1;
		
		for (int i = 0; i < len - 1; i++){
			long accessTime = System.currentTimeMillis();
			expectTime[len - 1 - i] = accessTime; 
			cacheEntry.accesses[len - 2 - i] = accessTime;
		}
		long accessTime = System.currentTimeMillis();
		expectTime[0] = accessTime;
		cacheEntry.access(accessTime);
		
		int actualCount = cacheEntry.count;
		assertEquals(expectedCount, actualCount);
		assertArrayEquals(expectTime, cacheEntry.getAccesses());
	}
	
	
	@Test
	public void testAccessedTenTimes() {
		// test case (access times = 10, count = 10)
		
		cacheEntry.count = 10;
		int expectedCount = 11;
		long expectTime[] = new long[10];
		int len = 10;
		for (int i = 0; i < 10; i++)	expectTime[i] = -1;
		
		for (int i = 0; i < len; i++){
			long accessTime = System.currentTimeMillis();
			cacheEntry.accesses[len - 1 - i] = accessTime;
			if (len - i < len)
				expectTime[len - i] = accessTime; 
			
		}
		long accessTime = System.currentTimeMillis();
		expectTime[0] = accessTime;
		cacheEntry.access(accessTime);
		
		int actualCount = cacheEntry.count;
		assertEquals(expectedCount, actualCount);
		assertArrayEquals(expectTime, cacheEntry.getAccesses());
	}
	
	@Test
	public void testAccessedMoreThanTenTimes(){
		// test case (access times = 12, count = 12)
		
		cacheEntry.count = 12;
		int expectedCount = 13;
		int len = 10;
		long expectTime[] = new long[len];
		
		for (int i = 0; i < 10; i++)	expectTime[i] = -1;
		
		for (int i = 0; i < 12; i++){
			long accessTime = System.currentTimeMillis();
			if (12 - i < len)
				expectTime[12 - i] = accessTime;
			if (12 - 1 - i < len)	cacheEntry.accesses[12 - 1 - i] = accessTime; 
			
		}
		long accessTime = System.currentTimeMillis();
		expectTime[0] = accessTime;
		cacheEntry.access(accessTime);
		int actualCount = cacheEntry.count;
		assertEquals(expectedCount, actualCount);
		assertArrayEquals(expectTime, cacheEntry.getAccesses());
	}
	
	
}
