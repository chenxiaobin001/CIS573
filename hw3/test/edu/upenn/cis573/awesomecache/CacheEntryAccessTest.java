package edu.upenn.cis573.awesomecache;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CacheEntryAccessTest {
	
	private CacheEntry cacheEntry;
	private Date date;

	@Before
	public void setUp() throws Exception {
		
		cacheEntry = new CacheEntry("test");
		date = new Date();
		
	}

	//inputs: key, access, count
	
	@Test
	public void testNegtiveCountValue() {	
		//test case: count < 0, 
		
		cacheEntry.count = -1;
		int actualCount = cacheEntry.count;
		assertTrue(actualCount >= 0);
	}
	
	@Rule public ExpectedException thrown= ExpectedException.none();
	@Test
	public void testNullKey(){
		// test case: null key as argument
		thrown.expect( IllegalArgumentException.class );
		cacheEntry = new CacheEntry(null);
		assertTrue(cacheEntry.getKey() != null);
	}
	
	
	@Test
	public void testAccess0Times(){
		// test case (access times = 0, count = 0)
		
		long actualTime[] = new long[10];
		for (int i = 0; i < 10; i++)	actualTime[i] = -1;
		int expectedCount = 0;
		int actualCount = cacheEntry.count;
		assertEquals(expectedCount, actualCount);
		assertArrayEquals(actualTime, cacheEntry.getAccesses());
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
	public void testAccessedLessThan10Times() {
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
	public void testAccessed10Times() {
		// test case (access times = 10, count = 10)
		
		long actualTime[] = new long[10];
		int len = 10;
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
	public void testAccessedMoreThan10Times(){
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
