import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class RadixSortFinal {

	public static int max(int[] numbers) {
		int max = 0;
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] > max) {
				max = numbers[i];
			}
		}
		return Integer.toString(max).length();
	}
	
	public static String[] toString(int[] numbers) {
		String[] toReturn = new String[numbers.length];
		for (int i = 0; i < toReturn.length; i++) {
			toReturn[i] = Integer.toString(numbers[i]);
		}
		
		int maxDigit = max(numbers);
		
		for(int i = 0; i < toReturn.length; i++) {
			while (toReturn[i].length() != maxDigit) {
				toReturn[i] = "0" + toReturn[i]; 
			}
		}
		return toReturn;
	}
	
	public static int[] toInt(String[] numbers) {
		int[] toReturn = new int[numbers.length];
		
		for (int i = 0; i < numbers.length; i++) {
			toReturn[i] = Integer.valueOf(numbers[i]);
		}
		return toReturn;
	}
	
	public static ArrayList<LinkedList<String>> initialize(ArrayList<LinkedList<String>> queue) {
		for(int i = 0; i < 10; i++) {
			LinkedList<String> list = new LinkedList<String>();
			queue.add(list);
		}
		return queue;
	}
	public static int[] radixSortAscending(int[] numbers) {
		int iterator = max(numbers);
		String[] toReturn = toString(numbers);
		ArrayList<LinkedList<String>> queues = new ArrayList<LinkedList<String>>();
		initialize(queues);
		
		for (int i = 0; i < iterator; i++) {
			int k = 0;
			for (int j = 0; j < toReturn.length; j++) {
				int queue = Character.getNumericValue(toReturn[j].charAt(iterator - i - 1));
				queues.get(queue).addLast(toReturn[j]);
			}
			
			for (int l = 0; l < queues.size(); l++) {
				while (!queues.get(l).isEmpty()) {
					toReturn[k] = queues.get(l).removeFirst();
					k++;
				}
			}
		}
		return toInt(toReturn);
	}
	
	public static int[] radixSortDescending(int[] numbers) {
		int iterator = max(numbers);
		String[] toReturn = toString(numbers);
		ArrayList<LinkedList<String>> queues = new ArrayList<LinkedList<String>>();
		initialize(queues);
		
		for (int i = 0; i < iterator; i++) {
			int k = 0;
			for (int j = 0; j < toReturn.length; j++) {
				int queue = Character.getNumericValue(toReturn[j].charAt(iterator - i - 1));
				queues.get(9 - queue).addLast(toReturn[j]);
			}
			
			for (int l = 0; l < queues.size(); l++) {
				while (!queues.get(l).isEmpty()) {
					toReturn[k] = queues.get(l).removeFirst();
					k++;
				}
			}
		}
		return toInt(toReturn);
	}
	
	public static void main(String[] args) {
		int[] numbers = { 1000, 4, 25, 319, 88, 51, 3430, 8471, 701, 1, 2989, 657, 713 };

		System.out.println("Correct output:[1, 4, 25, 51, 88, 319, 657, 701, 713, 1000, 2989, 3430, 8471]");
		System.out.println();
		System.out.println("Ascending: " + Arrays.toString(radixSortAscending(numbers)));
		System.out.println("Descending: " + Arrays.toString(radixSortDescending(numbers)));

	}
}
