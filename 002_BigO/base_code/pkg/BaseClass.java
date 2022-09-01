package pkg;
import java.util.Scanner;
import java.util.Random;


public class BaseClass {
	public BaseClass() {
		
	}
	public void randomize(int[] arr) {
			for (int i = 0; i < arr.length; i++) {
				arr[i] =(int) Math.random()*200000;
			}
	}
	// Interval: 10
	// Duration: 1323715
	
	// Interval: 100
	// Duration: 102562
	
	// Interval: 1000
	// Duration: 1123688
	
	// Interval: 10000
	// Duration: 1052555
	
	// Interval: 100000
	// Duration: 3100710
	
	// Interval: 1000000
	// Duration: 21225214
	
	// Interval: 10000000
	// Duration: 188972894
	public boolean search(int[] arr) {
		randomize(arr);
		int rand = (int) Math.random()*200000;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == rand) {
				return true;
			}
		}
		return false;
	}
	
// Interval: 10
// Duration: 1547496

// Interval: 100
// Duration: 150164

// Interval: 1000
// Duration: 561617

// Interval: 10000
// Duration: 818789

// Interval: 100000
// Duration: 4426997

// Interval: 1000000
// Duration: 20870879

// Interval: 10000000
// Duration: 187158285

	public void bubbleSort(int[] arr) {
		randomize(arr);
		int len = arr.length;
		int t = 0;
		for (int i = 0; i < len; i++) {
			for (int j = 1; j < len - i; j++) {
				if (arr[j - 1] > arr[j]) {
					t = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = t;
				}
			}
		}
		System.out.println("done");
	}
	// Interval: 10
	// Duration: 1388947
	
	// Interval: 100
	// Duration: 340843
	
	// Interval: 1000
	// Duration: 6210891
	
	// Interval: 10000
	// Duration: 21441568
	
	// Interval: 100000
	// Duration: 1090142082
	
	// Interval: 1000000
	
	public void insertion(int[] arr) {
		randomize(arr);
		int len = arr.length;
		int key, j;
		for (int i = 1; i < len; i++) {
			key = arr[i];
			j = i - 1;

			while(j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = key;
		}
	}
// 	Interval: 10
// Duration: 1195564

// Interval: 100
// Duration: 112813

// Interval: 1000
// Duration: 537368

// Interval: 10000
// Duration: 1237432

// Interval: 100000
// Duration: 5796730

// Interval: 1000000
// Duration: 25308076

// Interval: 10000000
// Duration: 192452563
	public void selection(int[] arr) {
		randomize(arr);
		int j, minIndex;
		int len = arr.length;
		for (int i = 0; i < len - 1; i++) {
			minIndex = i;
			for (j = i + 1; j < len; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
				int temp = arr[i];
				arr[i] = arr[minIndex];
				arr[minIndex] = temp;
			}
		}
	}
// 	Interval: 10
// Duration: 1554843

// Interval: 100
// Duration: 322393

// Interval: 1000
// Duration: 7682882

// Interval: 10000
// Duration: 66893217

// Interval: 100000
// Duration: 1115418916

// Interval: 1000000


}
