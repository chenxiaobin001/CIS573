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
		planes = new Airplane[5];
		planes[0] = new Airplane(0, 0, 0, 0);
		planes[1] = new Airplane(10, 10, 90, 1);
		planes[2] = new Airplane(2, 2, 180, -1);
		planes[3] = new Airplane(-10, -10, 2700, 20);
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


}
