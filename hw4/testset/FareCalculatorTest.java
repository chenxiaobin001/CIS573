import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class FareCalculatorTest {
	
	public static final long ONE_DAY = 60 * 60 * 24 * 1000;
	
	protected FareCalculator fc;
	
	// these are the defaults for my tests
	protected double price1;
	protected double price2;
	protected boolean isFreqFlier;
	protected long departureTime;
	protected int duration; 
	protected double delta;

	// reset the default values
	protected void reset() {
		price1 = 10;
		price2 = 10;
		isFreqFlier = true;
		// since there are no special rules in this case
		departureTime = System.currentTimeMillis() + ONE_DAY * 8;
		duration = 5;
		delta = 0;
	}

	@Before
	public void setUp() throws Exception {
		fc = new FareCalculator();
		reset(); 
	}

	@Test
	public void testInvalidPrice1() {
		int price1 = -10;
		
		double actual = fc.calculateFare(price1, price2, isFreqFlier, departureTime, duration);
		double expected = -1;
		
		assertEquals("Test failed for invalid price1.", expected, actual, delta);
		
	}

	@Test
	public void testInvalidPrice2() {
		price2 = -10;

		double actual = fc.calculateFare(price1, price2, isFreqFlier, departureTime, duration);
		double expected = -1;
		
		assertEquals("Test failed for invalid price2.", expected, actual, delta);
	}
	
	@Test
	public void testInvalidDepartureTime() {
		departureTime = -10;

		double actual = fc.calculateFare(price1, price2, isFreqFlier, departureTime, duration);
		double expected = -1;
		
		assertEquals("Test failed for invalid departureTime.", expected, actual, delta);
	}
	
	@Test
	public void testInvalidDuration() {
		duration = -10;

		double actual = fc.calculateFare(price1, price2, isFreqFlier, departureTime, duration);
		double expected = -1;
		
		assertEquals("Test failed for invalid duration.", expected, actual, delta);
	}

	@Test
	public void testTwoSegmentsDiffLessThan500() {
		price1 = 300;
		price2 = 200;
		// if 9 days from now, there should be no further adjustments
		departureTime = System.currentTimeMillis() + ONE_DAY * 9;
		
		// more expensive reduced by 10%
		double expected = 0.9*price1 + price2;

		double actual = fc.calculateFare(price1, price2, isFreqFlier, departureTime, duration);
		
		assertEquals("Test failed for two segments diff less than 500.", expected, actual, delta);
	}
	
	
	@Test
	public void testTwoSegmentsDiffMoreThan500() {
		price1 = 900;
		price2 = 200;
		// if 9 days from now, there should be no further adjustments
		departureTime = System.currentTimeMillis() + ONE_DAY * 9;
		
		// more expensive reduced by 15%
		double expected = 0.85*price1 + price2;

		double actual = fc.calculateFare(price1, price2, isFreqFlier, departureTime, duration);
		
		assertEquals("Test failed for two segments more less than 500.", expected, actual, delta);
	}

	@Test
	public void testOneSegmentDurationLessThan8() {
		price1 = 200;
		price2 = 0;
		duration = 3;
		
		// no discount
		double expected = price1;

		double actual = fc.calculateFare(price1, price2, isFreqFlier, departureTime, duration);
		
		assertEquals("Test failed for one segment only (price1), duration less than 8.", expected, actual, delta);
	}
	
	@Test
	public void testOneSegmentDurationMoreThan8() {
		price1 = 200;
		price2 = 0;
		duration = 10;
		
		// 10% discount
		double expected = 0.9*price1;

		double actual = fc.calculateFare(price1, price2, isFreqFlier, departureTime, duration);
		
		assertEquals("Test failed for one segment only (price1), duration more than 8.", expected, actual, delta);
	}

	@Test
	public void testOneSegmentPrice2OnlyDurationLessThan8() {
		price1 = 0;
		price2 = 200;
		duration = 3;
		
		// no discount
		double expected = price2;

		double actual = fc.calculateFare(price1, price2, isFreqFlier, departureTime, duration);
		
		assertEquals("Test failed for one segment only (price2), duration less than 8.", expected, actual, delta);
	}

	
	@Test
	public void testTwoSegmentsDepartureMoreThan14DaysFromNow() {
		price1 = 300;
		price2 = 200;
		departureTime = System.currentTimeMillis() + ONE_DAY * 18;
		
		// 10% discount on price1, then 20% discount 
		double expected = 0.8*(0.9*price1+price2);

		double actual = fc.calculateFare(price1, price2, isFreqFlier, departureTime, duration);
		
		assertEquals("Test failed for two segments, departure more than 14 days from now.", expected, actual, delta);
	}
	
	
	@Test
	public void testTwoSegmentsDepartureLessThan7DaysFromNowIsNotFreqFlier() {
		price1 = 300;
		price2 = 200;
		departureTime = System.currentTimeMillis() + (long)(ONE_DAY * 2.5);
		isFreqFlier = false;
		
		// 10% discount on price1, then $500 surcharge
		double expected = (0.9*price1+price2) + 500;

		double actual = fc.calculateFare(price1, price2, isFreqFlier, departureTime, duration);
		
		assertEquals("Test failed for two segments, departure less than 7 days from now, is not frequent flier.", expected, actual, delta);
	}

	@Test
	public void testTwoSegmentsDepartureLessThan3DaysFromNowIsFreqFlier() {
		price1 = 300;
		price2 = 200;
		departureTime = System.currentTimeMillis() + ONE_DAY * 2;
		isFreqFlier = true;
		
		// 10% discount on price1, then $100 surcharge
		double expected = (0.9*price1+price2) + 100;

		double actual = fc.calculateFare(price1, price2, isFreqFlier, departureTime, duration);
		
		assertEquals("Test failed for two segments, departure less than 3 days from now, is frequent flier.", expected, actual, delta);
	}



	@Test
	public void testTwoSegmentsDeparture3To7DaysFromNowIsNotFreqFlier() {
		price1 = 300;
		price2 = 200;
		departureTime = System.currentTimeMillis() + ONE_DAY * 5;
		isFreqFlier = false;
		
		// 10% discount on price1, $200 surcharge
		double expected = (0.9*price1+price2) + 200;

		double actual = fc.calculateFare(price1, price2, isFreqFlier, departureTime, duration);
		
		assertEquals("Test failed for two segments, departure 3-7 days from now, is not frequent flier.", expected, actual, delta);
	}
	
	@Test
	public void testTwoSegmentsDeparture3To7DaysFromNowIsFreqFlier() {
		price1 = 300;
		price2 = 200;
		departureTime = System.currentTimeMillis() + ONE_DAY * 5;
		isFreqFlier = true;
		
		// 10% discount on price1, then no surcharge 
		double expected = (0.9*price1+price2);

		double actual = fc.calculateFare(price1, price2, isFreqFlier, departureTime, duration);
		
		assertEquals("Test failed for two segments, departure 3-7 days from now, is frequent flier.", expected, actual, delta);
	}

	@Test
	public void testEnormousPrices() {
		price1 = Double.MAX_VALUE;
		price2 = Double.MAX_VALUE;
		
		double expected = -1;

		double actual = fc.calculateFare(price1, price2, isFreqFlier, departureTime, duration);
		
		assertEquals("Test failed for enormous prices (meant to cause overflow).", expected, actual, delta);
	}
	
	/***********************************************************************************************************/
	/*********************************below are added tests*****************************************************/
	/***********************************************************************************************************/
}
