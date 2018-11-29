package Lab01;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Main {

	public static int[] generateInt(int min, int max, int size) {
		int[] arr = new int[size];

		for (int i = 0; i < size; i++) {
			arr[i] = ThreadLocalRandom.current().nextInt(min, max + 1);
		}

		return arr;
	}

	// Create a new array consisting of even numbers only. Then use nested loops to
	// solve the problem using
	// the newly created array of even numbers only.
	public static int algorithm1(int[] arr) {

		// Create new array contains even numbers only
		int evenCount = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 == 0)
				evenCount++;
		}

		int[] evenArr = new int[evenCount];
		int idx = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] % 2 == 0) {
				evenArr[idx] = arr[i];
				idx++;
			}
		}

		// Find the largest distance
		int res = 0;
		for (int i = 0; i < evenArr.length - 1; i++) {
			for (int j = i + 1; j < evenArr.length; j++) {
				int dis = Math.abs(evenArr[i] - evenArr[j]);
				if (dis > res)
					res = dis;
			}
		}

		return res;
	}

	/*
	 * Use a nested loop to solve the problem without creating an extra array.
	 */
	private static int algorithm2(int[] input) {
		int maxDis = -1;
		for (int i = 0; i < input.length - 1; i++) {
			if (input[i] % 2 != 0)
				continue;
			for (int j = i + 1; j <= input.length - 1; j++) {
				if (input[j] % 2 != 0)
					continue;
				int r = Math.abs(input[j] - input[i]);
				if (r > maxDis)
					maxDis = r;
			}
		}
		return maxDis;
	}

	/*
	 * Use one loop. Find max and min of even integers. Compute max – min.
	 */
	private static int algorithm3(int[] input) {
		int maxDis  = -1;
		int maxEven = -100000;
		int minEven = 100000;
		int m = input.length;
		for (int i = 0; i < m; i++) 
			if (input[i] % 2 == 0)
			{
				if (maxEven < input[i])
					maxEven = input[i];
				if (minEven > input[i])
					minEven = input[i];
				
			}

			if (maxEven!=-100000)
				maxDis = maxEven-minEven;
		 return maxDis;
	}

	/*
	 * Use Streams to find the max and min. Compute max – min.
	 */
	private static int algorithm4(int[] input) {
		IntSummaryStatistics ints = Arrays.stream(input).filter(x -> x % 2 == 0).summaryStatistics();
		if (ints.getCount() <= 0)
			return -1;
		return ints.getMax() - ints.getMin();

	}

	private static void createGraph(List<Long> input) throws IOException {
		final String[] algo = { "algo1", "algo2", "algo3", "algo4" };
		final String[] times = { "1000", "2000", "3000", "4000" };

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < input.size(); i++) {
			System.out.println(String.format("%s\t%s\t%s", input.get(i), algo[i % 4], times[(int) i / 4]));
			dataset.addValue(input.get(i), algo[i % 4], times[(int) i / 4]);
		}

		JFreeChart barChart = ChartFactory.createBarChart("Performance of all four algorithms.", "Algorithm",
				"Time(ms)", dataset, PlotOrientation.VERTICAL, true, true, false);

		int width = 640; /* Width of the image */
		int height = 480; /* Height of the image */
		File BarChart = new File("BarChart.jpeg");
		ChartUtilities.saveChartAsJPEG(BarChart, barChart, width, height);
	}

	public static void main(String[] args) throws IOException {
		int i = 0;
		List<Long> r = new ArrayList<>();
		while (i <= 3) {
			System.out.println("");
			int[] arr = generateInt(1, 10000, 1000 + i * 1000);
			long startTime = System.currentTimeMillis();
			System.out.println(algorithm1(arr));
			long elapsedTime = System.currentTimeMillis() - startTime;
			r.add(elapsedTime);

			startTime = System.currentTimeMillis();
			System.out.println(algorithm2(arr));
			elapsedTime = System.currentTimeMillis() - startTime;
			r.add(elapsedTime);

			startTime = System.currentTimeMillis();
			System.out.println(algorithm3(arr));
			elapsedTime = System.currentTimeMillis() - startTime;
			r.add(elapsedTime);

			startTime = System.currentTimeMillis();
			System.out.println(algorithm4(arr));
			elapsedTime = System.currentTimeMillis() - startTime;
			r.add(elapsedTime);

			i++;
		}

		createGraph(r);
	}

}
