public class Min {

    public static void main(String[] args) {
	min(0, 0, 0, 0);
    }


    /**
     * This method returns the minimum of the four parameters.
     */
    public static int min(int a, int b, int c, int d) {
	int r = 0;
	if (a < b) {
	    if (a < c) {
		if (c < d) r = a;
		else r = d;
	    }
	    else {
		if (c < d) r = c;
		else r = d;
	    }
	}
	else if (b < c) {
	    if (c < d) r = c; 
	    else if (b < d) r = b;
	    else r = d;
	}
	else if (c < d) r = c;
	else r = d;

	// the return value is less than or equal to all params
	assert (r <= a && r <= b && r <= c && r <= d);
	// the return value is equal to one of the params
	assert (r == a || r == b || r == c || r == d);

	return r;
    }


}
