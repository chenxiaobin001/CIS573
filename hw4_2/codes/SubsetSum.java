public class SubsetSum {

    public static void main(String[] args) {
	int A[] = new int[5];
	subsetSum(A, 0);
    }

    /**
     * This method solves the subset sum problem by looking at all
     * combinations of the elements in A and seeing if any add
     * up to the target value.
     * 
     * The return value is a boolean array in which the value of 
     * each element indicates whether or not the corresponding element 
     * in the int array would be included in the solution.
     * 
     * The method returns null if there is no solution.
     */
    public static boolean[] subsetSum(int A[], int target) {
	boolean soln[] = null; 

	// determine how many combinations there are
	int combinations = 1;
	for (int i = 0; i < A.length; i++) {
	    combinations *= 2;
	}

	// try each combination, treating the value as a vector of booleans
	for (int c = 1; c < combinations; c++) {
	    
	    int comb = c;
	    int sum = 0;
	    
	    // the bits in the value indicate whether or not to include
	    // that element as part of the sum
	    for (int i = A.length-1; i >= 0; i--) {
		if (comb % 2 == 1) {
		    sum += A[i];
		}
		comb = comb/2;
	    }

	    // if we found a solution, populate the boolean array and break
	    if (sum == target) {
		comb = c;
		soln = new boolean[A.length];
		for (int i = A.length-1; i >= 0; i--) {
		    if (comb % 2 == 1) 
			soln[i] = true;
		    else soln[i] = false;
		    comb = comb / 2;
		}
		break;
	    }
	}

	// TODO: write your assertions here!
	// all the indicated return values add up to target value
	
	if (soln != null) { //ignore null case
		int mSum = 0;
		for (int i = 0; i < A.length; i++){
			if (soln[i]){
					mSum += A[i];
			}
		}
		assert(mSum == target);	
	}
	return soln;


    }
    

}
