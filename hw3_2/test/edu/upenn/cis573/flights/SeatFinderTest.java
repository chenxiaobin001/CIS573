package edu.upenn.cis573.flights;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * The class <code>SeatFinderTest</code> contains tests for the class <code>{@link SeatFinder}</code>.
 *
 * @generatedBy CodePro at 14-10-24 下午9:04
 * @author user
 * @version $Revision: 1.0 $
 */
public class SeatFinderTest {
	/**
	 * Run the int numSeats(byte[],boolean,boolean,boolean,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 14-10-24 下午9:04
	 */
	@Test
	public void testNumSeats_1()
		throws Exception {
		SeatFinder fixture = new SeatFinder();
		byte[] state = new byte[] {};
		boolean windowOk = true;
		boolean aisleOk = true;
		boolean middleOk = true;
		int maxRow = 1;

		int result = fixture.numSeats(state, windowOk, aisleOk, middleOk, maxRow);

		// add additional test code here
		assertEquals(-1, result);
	}

	/**
	 * Run the int numSeats(byte[],boolean,boolean,boolean,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 14-10-24 下午9:04
	 */
	@Test
	public void testNumSeats_2()
		throws Exception {
		SeatFinder fixture = new SeatFinder();
		byte[] state = new byte[] {};
		boolean windowOk = true;
		boolean aisleOk = true;
		boolean middleOk = true;
		int maxRow = 1;

		int result = fixture.numSeats(state, windowOk, aisleOk, middleOk, maxRow);

		// add additional test code here
		assertEquals(-1, result);
	}

	/**
	 * Run the int numSeats(byte[],boolean,boolean,boolean,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 14-10-24 下午9:04
	 */
	@Test
	public void testNumSeats_3()
		throws Exception {
		SeatFinder fixture = new SeatFinder();
		byte[] state = new byte[] {};
		boolean windowOk = true;
		boolean aisleOk = true;
		boolean middleOk = true;
		int maxRow = 0;

		int result = fixture.numSeats(state, windowOk, aisleOk, middleOk, maxRow);

		// add additional test code here
		assertEquals(-1, result);
	}

	/**
	 * Run the int numSeats(byte[],boolean,boolean,boolean,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 14-10-24 下午9:04
	 */
	@Test
	public void testNumSeats_4()
		throws Exception {
		SeatFinder fixture = new SeatFinder();
		byte[] state = new byte[] {(byte) 0, (byte) 1};
		boolean windowOk = true;
		boolean aisleOk = true;
		boolean middleOk = true;
		int maxRow = 1;

		int result = fixture.numSeats(state, windowOk, aisleOk, middleOk, maxRow);

		// add additional test code here
		assertEquals(6, result);
	}

	/**
	 * Run the int numSeats(byte[],boolean,boolean,boolean,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 14-10-24 下午9:04
	 */
	@Test
	public void testNumSeats_5()
		throws Exception {
		SeatFinder fixture = new SeatFinder();
		byte[] state = new byte[] {(byte) 0, (byte) 1};
		boolean windowOk = false;
		boolean aisleOk = false;
		boolean middleOk = false;
		int maxRow = 1;

		int result = fixture.numSeats(state, windowOk, aisleOk, middleOk, maxRow);

		// add additional test code here
		assertEquals(0, result);
	}

	/**
	 * Run the int numSeats(byte[],boolean,boolean,boolean,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 14-10-24 下午9:04
	 */
	@Test
	public void testNumSeats_6()
		throws Exception {
		SeatFinder fixture = new SeatFinder();
		byte[] state = new byte[] {(byte) 0, (byte) 1};
		boolean windowOk = true;
		boolean aisleOk = true;
		boolean middleOk = true;
		int maxRow = 1;

		int result = fixture.numSeats(state, windowOk, aisleOk, middleOk, maxRow);

		// add additional test code here
		assertEquals(6, result);
	}

	/**
	 * Run the int numSeats(byte[],boolean,boolean,boolean,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 14-10-24 下午9:04
	 */
	@Test
	public void testNumSeats_7()
		throws Exception {
		SeatFinder fixture = new SeatFinder();
		byte[] state = new byte[] {(byte) 0, (byte) 1};
		boolean windowOk = true;
		boolean aisleOk = true;
		boolean middleOk = true;
		int maxRow = 1;

		int result = fixture.numSeats(state, windowOk, aisleOk, middleOk, maxRow);

		// add additional test code here
		assertEquals(6, result);
	}

	/**
	 * Run the int numSeats(byte[],boolean,boolean,boolean,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 14-10-24 下午9:04
	 */
	@Test
	public void testNumSeats_8()
		throws Exception {
		SeatFinder fixture = new SeatFinder();
		byte[] state = new byte[] {(byte) 0, (byte) 1};
		boolean windowOk = true;
		boolean aisleOk = true;
		boolean middleOk = true;
		int maxRow = 1;

		int result = fixture.numSeats(state, windowOk, aisleOk, middleOk, maxRow);

		// add additional test code here
		assertEquals(6, result);
	}

	/**
	 * Run the int numSeats(byte[],boolean,boolean,boolean,int) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 14-10-24 下午9:04
	 */
	@Test
	public void testNumSeats_9()
		throws Exception {
		SeatFinder fixture = new SeatFinder();
		byte[] state = new byte[] {(byte) 0, (byte) 1};
		boolean windowOk = true;
		boolean aisleOk = true;
		boolean middleOk = true;
		int maxRow = 1;

		int result = fixture.numSeats(state, windowOk, aisleOk, middleOk, maxRow);

		// add additional test code here
		assertEquals(6, result);
	}

	
	@Test
	public void testNumSeats_NullState()
		throws Exception {
		SeatFinder fixture = new SeatFinder();
		byte[] state = null;
		boolean windowOk = true;
		boolean aisleOk = true;
		boolean middleOk = true;
		int maxRow = 1;

		int result = fixture.numSeats(state, windowOk, aisleOk, middleOk, maxRow);

		// add additional test code here
		assertEquals(-1, result);
	}
	
	@Test
	public void testNumSeats_Window7()
		throws Exception {
		SeatFinder fixture = new SeatFinder();
		byte[] state = new byte[] {(byte) 0, (byte) 64};;
		boolean windowOk = true;
		boolean aisleOk = true;
		boolean middleOk = true;
		int maxRow = 1;

		int result = fixture.numSeats(state, windowOk, aisleOk, middleOk, maxRow);

		// add additional test code here
		assertEquals(6, result);
	}
	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 14-10-24 下午9:04
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 14-10-24 下午9:04
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 14-10-24 下午9:04
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(SeatFinderTest.class);
	}
}