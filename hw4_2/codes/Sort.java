import java.util.HashMap;
import java.util.Iterator;

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
	
	// all positive elements are in the return array.
	HashMap<Integer, Integer> originalArr = getPositiveElementCount(A);
	HashMap<Integer, Integer> newArr = getPositiveElementCount(R);
	assert(originalArr.size() == newArr.size());	//two array have the same number of positive numbers
	for (int i : newArr.keySet()){
		assert(originalArr.get(i) != null);		//this positive number is in original array
		assert(originalArr.get(i) == newArr.get(i));	//the count of positive number is the same	
	}
	
	// all elements in return array are positive
	for (int i = 0; i < R.length; i++){
		assert (R[i] > 0);
	}
	
	// all elements in new array are in increasing order.
	for (int i = 1; i <R.length; i++){
		assert (R[i] >= R[i - 1]);
	}

	return R;

    }
	
	private static HashMap<Integer, Integer> getPositiveElementCount(int[] arr){
		HashMap<Integer, Integer> result = new HashMap<Integer, Integer>();
		for (int i = 0; i < arr.length; i++){
			if (arr[i] > 0){
				if (result.get(arr[i]) == null){
					result.put(arr[i], 1);
				}else{
					result.put(arr[i], result.get(arr[i]) + 1);
				}
			}
		}
		return result;
	}

}
