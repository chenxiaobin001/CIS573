public class Sort {

    public static void main(String[] args) {
	int A[] = new int[5];
	sort(A);
    }

    /**
     * This method returns an array containing the positive values in A
     * in non-descending sorted order.
     */
    public static int[] sort(int[] A) {

	// find out how many elements in A are positive
	int count = 0;
	for (int i = 0; i < A.length; i++)
	    if (A[i] > 0) count++;

	int R[] = new int[count];

	int r = 0;

	// populate the new array
	for (int i = 0; i < A.length; i++) 
	    if (A[i] > 0) {
		R[r] = A[i];
		r++;
	    }

	// bubble sort!
	for (int i = 0; i < R.length; i++) {
	    for (int j = i; j < R.length-1; j++) {
		if (R[j] < R[j+1]) { 
		    int temp = R[j];
		    R[j] = R[j+1];
		    R[j+1] = temp;
		}
	    }
	}

	// TODO: write your assertions here!

	return R;

    }

}
