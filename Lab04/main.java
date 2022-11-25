import pkg.*;
import java.util.*;
import java.io.*;
import java.time.*;
import java.lang.*;
import java.rmi.dgc.VMID;


class main {
	// static final long createdNano = System.nanoTime();

	public static void main(String args[]) throws IOException {
		Scanner yo = new Scanner(new File("births.csv"));
		yo.nextLine();
		yo.useDelimiter(",");
		String[] line = new String[5];
		ArrayList<DayBirth> data = new ArrayList<DayBirth>();
		while(yo.hasNext()) {
			String ln = yo.nextLine();
			String[] values = ln.split(",");
			for(int i = 0; i < 5; i++) {
				line[i] = values[i];
			}
			if (line[2].equals("null")) {
				data.add(new DayBirth(Integer.valueOf((String)line[0]), Integer.valueOf((String)line[1]), -1, line[3], Integer.valueOf(line[4])));
			}
			else{
				data.add(new DayBirth(Integer.valueOf((String)line[0]), Integer.valueOf((String)line[1]), Integer.valueOf(line[2]), line[3], Integer.valueOf(line[4])));
			}
		}
		int m = 0;
		int f = 0;
		for(DayBirth x: data) {
			if(x.getGender().equals("M")) {
				m++;
			}
			else {
				f++;
			}
		}
		int[][] maleBirthsMap = new int[m][2];
		int[][] femaleBirthsMap = new int[f][2];
		int[] maleBirths = new int[m];
		int[] femaleBirths = new int[f];
		int k = 0;
		int j = 0;
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).getGender().equals("M")) {
				maleBirthsMap[k][0] = data.get(i).getBirths();
				maleBirths[k] = data.get(i).getBirths();
				maleBirthsMap[k++][1] = i;
			}
			else if (data.get(i).getGender().equals("F")) {
				femaleBirthsMap[j][0] = data.get(i).getBirths();
				femaleBirths[j] = data.get(i).getBirths();
				femaleBirthsMap[j++][1] = i;
			}
		}
		BaseClass.quickSort(maleBirths, 0, maleBirths.length - 1);
		BaseClass.quickSort(femaleBirths, 0, femaleBirths.length - 1);
		
		for (int o = 0; o < femaleBirths.length; o++) {
			System.out.println(femaleBirths[o] + " + " + femaleBirthsMap[o][0] + " + " + data.get(femaleBirthsMap[o][1]));
		}

		File maleFile = new File("maleBirths.csv");
		File femaleFile = new File("femaleBirths.csv");
		PrintWriter maleOut = new PrintWriter(maleFile);
		PrintWriter femaleOut = new PrintWriter(femaleFile);
		maleOut.println("year,month,day,gender,births");
		femaleOut.println("year,month,day,gender,births");

		ArrayList<String> usedYears = new ArrayList<String>();
		boolean pass = true;
		for (int n = 0; n < femaleBirths.length; n++) {
			if (n != 0 && femaleBirths[n] != femaleBirths[n - 1]){
				usedYears.clear();
			}
			for (int b = 0; b < femaleBirths.length; b++) {
				if(femaleBirths[n] == femaleBirthsMap[b][0]) {
					for (int i = 0; i < usedYears.size(); i++) {
						if(data.get(femaleBirthsMap[b][1]).toString().equals(usedYears.get(i))) {
							pass = false;
						}
					}
					if (pass) {
					// System.out.println("AAA" + femaleBirths[n] + " + " + femaleBirthsMap[n][1] + " + " + data.get(femaleBirthsMap[n][1]));
					femaleOut.println(data.get(femaleBirthsMap[b][1]).toString());
					usedYears.add(data.get(femaleBirthsMap[b][1]).toString());
					pass = true;
					}
					pass = true;
				}
			}
		}
		for (int n = 0; n < maleBirths.length; n++) {
			if (n != 0 && maleBirths[n] != maleBirths[n - 1]){
				usedYears.clear();
			}
			for (int b = 0; b < maleBirths.length; b++) {
				if(maleBirths[n] == maleBirthsMap[b][0]) {
					for (int i = 0; i < usedYears.size(); i++) {
						if(data.get(maleBirthsMap[b][1]).toString().equals(usedYears.get(i))) {
							pass = false;
						}
					}
					if (pass) {
					// System.out.println("AAA" + femaleBirths[n] + " + " + femaleBirthsMap[n][1] + " + " + data.get(femaleBirthsMap[n][1]));
					maleOut.println(data.get(maleBirthsMap[b][1]).toString());
					usedYears.add(data.get(maleBirthsMap[b][1]).toString());
					pass = true;
					}
					pass = true;
				}
			}
		}
		maleOut.close();
		femaleOut.close();
		
		
		// long start = System.nanoTime();
		// long finish = System.nanoTime();
		// System.out.println("Created: " + createdNano);
		
		// int [] times = {10, 100, 1000, 10000, 100000, 1000000, 10000000};
		// int [] nums = new int[100];


		// System.out.println("-------------------Test-------------------");
		// System.out.println("");
		// for(int i : times){
		// 	System.out.println("Interval: " + i);
			
		// 	nums = new int[i];
		// 	start = System.nanoTime();

		// 	BaseClass yo = new BaseClass();
		// 	//  Put your method between these two comments
		// 	yo.mergeSort(nums);
		// 	//

		// 	finish = System.nanoTime();
		// 	System.out.println("Started: " + start);
		// 	System.out.println("Finished: " + finish);
		// 	System.out.println("Duration: " + (finish-start));
		// 	System.out.println("");
		// }
	}
}
