import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Step8 {

	private static final int arrSize = 20;
	private static final int valRange = 100;
	private static final int targetRange = 200;
	private static final int loopTimes = 10000;
	private boolean[] result;
	
	public static void main(String[] args) {
		

	}
	
	
	public static boolean[] solveSubsetSum(int[] arr, int target){
		return null;
	}
	
	
	
	public class MyThreadPoolExecutor extends ThreadPoolExecutor {
		 
	    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
	            long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
	        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	    }
	 
	    @Override
	    protected void afterExecute(Runnable r, Throwable t) {
	        super.afterExecute(r, t);
	        if (t != null) {
	            System.out.println("exception");
	        }
	        System.out.println("Perform afterExecute() logic");
	    }
	 
	}
	
	
	// thread to execute SubsetSumDP
	public class SubsetSumDPThread implements Runnable
	{
	    private int[] arr;
	    private int target;
	    
	    public SubsetSumDPThread(int[] arr, int target){
	    	this.arr = arr;
	    	this.target = target;
	    }
	 
		@Override
		public void run(){
			try{
	            result = SubsetSumDP.solve(arr, target);
	        } catch (Exception e) {
	            e.printStackTrace();
	            result = null;
	        } 
		}
	}
	
	// thread to execute SubsetSum
	public class SubsetSumThread implements Runnable
	{
	    private int[] arr;
	    private int target;
	    
	    public SubsetSumThread(int[] arr, int target){
	    	this.arr = arr;
	    	this.target = target;
	    }
	    
	 
		@Override
		public void run() {
			try{
	            result = SubsetSum.solve(arr, target);
	        } catch (Exception e) {
	            e.printStackTrace();
	            result = null;
	        } 
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
