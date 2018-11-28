package Lab01;

import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;
import java.util.Arrays;

public class Main {
	
	public static int[] generateInt(int min, int max, int size) {
		int[] arr = new int[size];
		
		for(int i=0;i < size; i++) {
			arr[i] = ThreadLocalRandom.current().nextInt(min, max + 1);
		}
		
		return arr;
	}
	
	// Use a nested loop to solve the problem without creating an extra array.
	public static int algorithm2(int[] arr) {
		int res = 0;
		
		return res;
	}
	
	// Create a new array consisting of even numbers only. Then use nested loops to solve the problem using
	// the newly created array of even numbers only.
	public static int algorithm1(int[] arr) {

		// Create new array contains even numbers only
		int evenCount = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] % 2 == 0) evenCount++;
		}
		
		int[] evenArr = new int[evenCount];
		int idx = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] % 2 == 0) {
				evenArr[idx] = arr[i];
				idx++;
			}
		}
		
		// Find the largest distance
		int res = 0;
		for(int i = 0; i < evenArr.length; i++) {
			for(int j = 0; j < evenArr.length; j++) {
				int dis = Math.abs(evenArr[i] - evenArr[j]);
				if (dis > res) res = dis;
			}
		}
		
		return res;
	}

	public static void main(String[] args) {
		int[] arr = generateInt(1,10000,1000);

		//System.out.print("Given random array " + Arrays.toString(arr));
		long startTime = System.currentTimeMillis();
		int dis = algorithm1(arr);
		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("\nThe largest distance between any two even integers is: " + dis);
		System.out.println("Elapsed time (miliseconds): " + elapsedTime);
		
	}

}

