package socalcontest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem2019_9 {

	public static void main(String[] args) throws FileNotFoundException {
		String plate, accuracy;
		ArrayList<String> accEli = new ArrayList<String>();
		Scanner sc = new Scanner(new BufferedReader(new FileReader("C:\\Users\\Blake\\Desktop\\tee9.txt")));
		while (sc.hasNextLine()) {
			accuracy = sc.nextLine();
			if (accuracy.length() > 1 && accuracy.charAt(1) == ' ') {
				accEli.add(accuracy);
			} else {
				break;
			}
		}
		while (sc.hasNext()) {
			plate = sc.next();
			printProb(plate, accEli);
		}
		sc.close();
	}

	public static void printProb(String plate, ArrayList<String> accEli) {
		double prob = 1;
		for (int i = 0; i < plate.length(); i++) { //plate
			for (int j = 0; j < accEli.size(); j++) { //accEli
				if(plate.charAt(i) == accEli.get(j).charAt(0)) {
					prob *= Double.parseDouble(accEli.get(j).substring(2));
				}
			}
		}
		System.out.printf("%.3f", prob);
		System.out.println();
	}
}
