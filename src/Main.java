import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
        
        System.out.println("Hello World!");
        System.out.println("Hello again!");
				
		int min = Integer.MAX_VALUE;
		int max = 0;
		int n = 0;
		
		int numTrials = 100000;
		ArrayList<Integer> results = new ArrayList<Integer>();
		
		long start = System.nanoTime();
		
		for(int i = 0; i < numTrials; i++) {
			int result = run();
			results.add(result);
			if(result < min)
				min = result;
			if(result > max)
				max = result;
			
			int oldN = n;
			n = (int)((double)i/numTrials*100);
			if(n != oldN)
				System.out.println(n+" %");
		}
		
		long time = (System.nanoTime()-start)/1000000;
		System.out.println("Time elapsed: "+time+" ms");
		
		double sum = 0;
		for(int i = 0; i < results.size(); i++)
			sum += results.get(i);
		double average = (sum)/results.size();

		System.out.println();
		System.out.println("Min: "+min);
		System.out.println("Max: "+max);
		System.out.println("Average: "+average);
		
	}

	public static int run() {
		int count = 0;

		int max = 89;
		int given = 79;
		
		int[] nums = new int[max];
		
		for(int i = 0; i < given; i++) {
			int rand = (int)(Math.random()*max)+1;
			while(nums[rand-1] != 0)
				rand = (int)(Math.random()*max)+1;
			
			nums[rand-1] = 1;
		}
		
		while(true) {
			boolean finished = true;
			for(int i = 0; i < nums.length; i++) {
				if(nums[i] == 0)
					finished = false;
			}
			if(finished)
				break;
			
			//int from 1 to 72 inclusive
			int rand = (int)(Math.random()*max)+1;
			nums[rand-1] = 1;
			
			count++;
		}
		
		return count;
	}
	
}
