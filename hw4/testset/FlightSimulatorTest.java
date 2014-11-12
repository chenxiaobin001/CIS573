import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class FlightSimulatorTest {

	
	// these are the defaults for my tests
	protected Airplane[] planes;
	protected int steps;
	protected double safeDistance;

	@Rule public ExpectedException thrown= ExpectedException.none();
	
	@Before
	public void reset() throws Exception {
		planes = new Airplane[4];
		planes[0] = new Airplane(0, 10, 90, 5);
		planes[1] = new Airplane(-10, 0, 180, 5);
		planes[2] = new Airplane(0, -10, 270, 5);
		planes[3] = new Airplane(10, 0, 0, 5);
		steps = 10;
		safeDistance = 2;
	}

	@Before
	public void setUp() throws Exception{
		reset(); 
	}
	
	@Test
	public void testInvalidPlanes() {
		thrown.expect( IllegalArgumentException.class );
		planes = null;
		boolean actual = FlightSimulator.simulateFlights(planes, steps, safeDistance);
	//	assertEquals("Test failed for invalid plane.", expected, actual);
	}
	
	@Test
	public void testInvalidPlanesAndStep() {
		thrown.expect( IllegalArgumentException.class );
		planes = null;
		steps = 0;
		boolean actual = FlightSimulator.simulateFlights(planes, steps, safeDistance);
	}
	
	@Test
	public void testInvalidSteps() {
		thrown.expect( IllegalArgumentException.class );
		steps = 0;
		boolean actual = FlightSimulator.simulateFlights(planes, steps, safeDistance);
	}
	
	@Test
	public void testInvalidStepsAndSafeDistance() {
		thrown.expect( IllegalArgumentException.class );
		steps = 0;
		safeDistance = -1;
		boolean actual = FlightSimulator.simulateFlights(planes, steps, safeDistance);
	}
	
	@Test
	public void testInvalidSafeDistance() {
		thrown.expect( IllegalArgumentException.class );
		safeDistance = -2;
		boolean actual = FlightSimulator.simulateFlights(planes, steps, safeDistance);
	}
	
	@Test
	public void testInvalidPlanes1() {
		thrown.expect( IllegalArgumentException.class );
		planes[3] = null;
		boolean actual = FlightSimulator.simulateFlights(planes, steps, safeDistance);
	}
	
	@Test
	public void testInvalidVelocity() {
		thrown.expect( IllegalArgumentException.class );
		planes[3].velocity = -1;
		boolean actual = FlightSimulator.simulateFlights(planes, steps, safeDistance);
	}
	
	@Test
	public void testViolateSafeDistance() {
		planes[3] = new Airplane(0, 10, 90, 2);
		boolean actual = FlightSimulator.simulateFlights(planes, steps, safeDistance);
		boolean expected = false;
		assertEquals("Test failed for Violating SafeDistance.", expected, actual);
	}
	
	@Test
	public void testSafeDistance() {
		steps = 1;
		boolean actual = FlightSimulator.simulateFlights(planes, steps, safeDistance);
		boolean expected = true;
		assertEquals("Test failed.", expected, actual);
	}
	
	@Test
	public void testViolateSafeDistanceInTwoSteps() {
		steps = 2;
		planes[0] = new Airplane(2, 2, 180, 1);
		planes[1] = new Airplane(-2, 2, 0, 1);
		boolean actual = FlightSimulator.simulateFlights(planes, steps, safeDistance);
		boolean expected = false;
		assertEquals("Test failed for invalid plane.", expected, actual);
	}
	
	@Test
	public void testSafeDistanceInTwoSteps() {
		steps = 2;
		planes[0] = new Airplane(-1, -1, 60, 1);
		planes[1] = new Airplane(1, 1, -60, 1);
		planes[2] = new Airplane(0, 100, 90, 10);
		planes[3] = new Airplane(100, 0, 0, 10);
		safeDistance = 1;
		boolean actual = FlightSimulator.simulateFlights(planes, steps, safeDistance);
		boolean expected = true;
		assertEquals("Test failed for invalid plane.", expected, actual);
	}
	
	@Test
	public void testViolateSafeDistanceInTwoStepsV1() {
		steps = 2;
		planes[0] = new Airplane(1, 2, 0, 1);
		planes[1] = new Airplane(-1, 2, 180, 2);
		boolean actual = FlightSimulator.simulateFlights(planes, steps, safeDistance);
		boolean expected = true;
		assertEquals("Test failed for invalid plane.", expected, actual);
	}
	
	@Test
	public void testViolateSafeDistanceInTwoStepsV2() {
		steps = 2;
		planes[0] = new Airplane(1, -1, 270, 1);
		planes[1] = new Airplane(1, 1, 90, 2);
		boolean actual = FlightSimulator.simulateFlights(planes, steps, safeDistance);
		boolean expected = true;
		assertEquals("Test failed for invalid plane.", expected, actual);
	}
	
	@Test
	public void testViolateSafeDistanceInTwoStepsV3() {
		steps = 2;
		safeDistance = 1;
		planes[0] = new Airplane(-1, 0, 0, 1);
		planes[1] = new Airplane(0, 1, 90, 1);
		boolean actual = FlightSimulator.simulateFlights(planes, steps, safeDistance);
		boolean expected = true;
		assertEquals("Test failed for invalid plane.", expected, actual);
	}
	
	@Test
	public void testViolateSafeDistanceInTwoStepsV4() {
		steps = 2;
		safeDistance = 1;
		planes[0] = new Airplane(0, 1, 0, 0);
		planes[1] = new Airplane(0, 0, 1.57, 1);
		boolean actual = FlightSimulator.simulateFlights(planes, steps, safeDistance);
		boolean expected = true;
		assertEquals("Test failed for invalid plane.", expected, actual);
	}
	
	
	@Test
	public void testSafeDistanceInThreeSteps() {
		steps = 3;
		planes[0] = new Airplane(2, 0, 0, 0);
		planes[1] = new Airplane(-2, 0, 0, 1);
		planes[2] = new Airplane(0, 100, 90, 10);
		planes[3] = new Airplane(100, 0, 0, 10);
		safeDistance = 1;
		boolean actual = FlightSimulator.simulateFlights(planes, steps, safeDistance);
		boolean expected = true;
		assertEquals("Test failed for invalid plane.", expected, actual);
	}
	
	@Test(timeout=1000)
	public void testTimeout1() {
		steps = 3;
		planes = new Airplane[0];
		safeDistance = 1;
		boolean actual = FlightSimulator.simulateFlights(planes, steps, safeDistance);
		boolean expected = true;
		assertEquals("Test failed for invalid plane.", expected, actual);
	}
	
}
