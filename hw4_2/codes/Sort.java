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
	    for (int j = 0; j < R.length-1-i; j++) {
		if (R[j] > R[j+1]) { 
		    int temp = R[j];
		    R[j] = R[j+1];
		    R[j+1] = temp;
		}
	    }
	}

	// TODO: write your assertions here!
	
	
	
	// all elements in return array are positive
	for (int i = 0; i < R.length; i++){
		assert (R[i] > 0);
	}
	
	// all elements in new array are in increasing order.
	for (int i = 1; i <R.length; i++){
		assert (R[i] >= R[i - 1]);
	}
	
	// all positive elements are in the return array R[].
	int[] original = copyArray(A);
	int index = 0;
	for (int i = 0; i < R.length; i++){
		for (index = 0; index < original.length; index++){
			if (original[index] == R[i]){
				original[index] = -1;		//find corresponding num in A[] and remove it
				break;
			}
		}
		assert(index != original.length);		//every positive num in R[] has corresponding positive num in A[]
	}
	for (int i = 0; i < original.length; i++){
		assert (original[i] <= 0);			//not positive num left in A[]
	}
	
	return R;

    }
	
	
	private static int[] copyArray(int[] arr){	//copy an array
    	if (arr == null)	return null;
    	int size = arr.length;
    	int[] result = new int[size];
    	for (int i = 0; i < size; i++){
    		result[i] = arr[i];
    	}
    	return result;
    }
}



