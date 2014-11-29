import java.util.Random;


public class Step6 {

	private static final int arrSize = 20;
	private static final int valRange = 100;
	private static final int targetRange = 200;
	private static final int loopTimes = 10000;
	
	public static void main(String[] args) {
		
		long start = System.currentTimeMillis();
		
		//check SubsetSum
		int count = 0;
		for (int i = 0; i < loopTimes; i++){
			// generate an array with size 20, value [1-100]
			int[] arr = genRandomArray();
			// generate a target value [1-200]
			Random rand = new Random();
			int target = rand.nextInt(targetRange) + 1;
			
			boolean[] result = solveSubsetSum(arr, target);
			if (testSubsetSumResult(result, arr, target)){
				count++;
			}
		}
		
		long end = System.currentTimeMillis();
		System.out.println("percentage: " + ((double)count)/loopTimes*100 + "%");
		System.out.println("Execution Time: " + (end - start)/10000.0 + "ms");
		
	}

	public static boolean[] solveSubsetSum(int[] arr, int target){
		int index[] = new int[arr.length];
		// index array
		for (int i = 0; i < index.length; i++){	
			index[i] = i;
		}
		//first time
		boolean[] result = SubsetSumDP.solve(arr, target);
		if (testSubsetSumResult(result, arr, target)){
			return result;
		}else{
			//second time
			//just shuffle the index array, not the original array!
			myShuffle(index);
			int[] arr1 = copyArray(arr, index);
			result = SubsetSumDP.solve(arr1, target);
			if (testSubsetSumResult(result, arr1, target)){
				return reconstructResult(result, index);
			}else{
				//third time
				myShuffle(index);
				int[] arr2 = copyArray(arr, index);
				result = SubsetSumDP.solve(arr2, target);
				return reconstructResult(result, index);
			}
		}
		
	}
	
	
	//recontruct the result
	private static boolean[] reconstructResult(boolean[] result, int[] index){
		
		if (result == null)	return null;
		
		boolean[] newRet = new boolean[result.length];
		for (int i = 0; i < result.length; i++){
			if (result[i]){
				newRet[index[i]] = true;
			}else{
				newRet[index[i]] = false;
			}
		}
		return newRet;
	}
	
	//copy an array according to index.
	private static int[] copyArray(int[] arr, int[] index){
		int[] newArr = new int[arr.length];
		for (int i = 0; i < arr.length; i++){
			newArr[i] = arr[index[i]];
		}
		return newArr;
	}
	
	//shuffle an array
	private static void myShuffle(int[] arr){
		
		Random rand = new Random();
		for (int i = arr.length; i > 0; i--){
			int idx = rand.nextInt(i);
			int tmp = arr[i - 1];
			arr[i - 1] = arr[idx];
			arr[idx] = tmp;
		}
		
	}
	
	private static boolean testSubsetSumResult(boolean[] result, int[] arr, int target){
		
		if (result == null)	return true;

		int sum = 0;
		for (int i = 0; i < result.length; i++){
			if (result[i]){
				sum += arr[i];
			}
		}
		if (sum == target){
			return true;
		}else{
			return false;
		}

	}
	
	private static int[] genRandomArray(){
		
		int[] arr = new int[arrSize];
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++){			
			int randomNum = rand.nextInt(valRange) + 1;
			arr[i] = randomNum;
		}
		return arr;
	}
}
