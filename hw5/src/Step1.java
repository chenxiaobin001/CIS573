import java.util.Random;


public class Step1 {


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
//			int[] arr1 = {1,2,3};
			
			// generate a random value [1-200]
			Random rand = new Random();
			int target = rand.nextInt(targetRange) + 1;
//			int target1 = 7;
			
			if (testSubsetSum(arr, target)){
				count++;
/*				if (SubsetSum.solve(arr, target)!=null){
					System.out.println(target);
					for (int j = 0; j < arr.length; j++){
						System.out.println(arr[j]);
					}
					System.out.println("/////////////////////////////");
				}*/
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("percentage: " + ((double)count)/loopTimes*100 + "%");
		System.out.println("Execution Time: " + (end - start)/10000.0 + "ms");
		
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
	
	private static boolean testSubsetSum(int[] arr, int target){
		
		boolean[] result = SubsetSum.solve(arr, target);
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
	

}
