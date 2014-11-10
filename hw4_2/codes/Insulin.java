public class Insulin {

    public static void main(String[] args) {
        calculate(0,0,0);
    }

    public static int calculate(int current, int previous, int amount) {

	/* IMPLEMENT THIS METHOD! */

    }


    public static int administer(int current, int previous, int amount) {
	
	// given the current reading, previous reading, and current amount,
	// figure out how much to give (ie the new amount)
	int newAmount = calculate(current, previous, amount);
	if (newAmount == -1) return -1;

	// given the amount to give and the current reading.
	// figure out what the new reading will be
	int newLevel = process(newAmount, current);
	if (newLevel == -1) return -1;

	/* VERIFY THIS! */
	assert current - newLevel <= 10; 

	return 0;
    }


    /* DO NOT CHANGE THIS METHOD! */

    public static int process(int amount, int bg) {

	int newLevel;

	// check for illegal values
	if (amount < 0 || bg < 50 || bg > 250) return -1;

	// if the current level is less than 100, then you're not going to
	// be very much affected by insulin
	if (bg < 100) {
	    newLevel = bg - amount;
	}
	// if you're in the middle, then it affects you a bit
	else if (bg < 150) {
	    newLevel = bg - (2 * amount);
	}
	// otherwise, it affects you a lot
	else newLevel = bg - (4 * amount);

	assert newLevel >= 50 && newLevel <= 250;

	return newLevel;
 
    }

	


}
