package socalcontest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Problem2019_6 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new BufferedReader(new FileReader("C:\\Users\\Blake\\Desktop\\tee6.txt")));
		int numberOfPair = 0;
		if (sc.hasNext()) {
			numberOfPair = sc.nextInt();
		}
		for (int i = 0; i < numberOfPair; i++) {
			compareVal(sc.next(), sc.next());
		}
		sc.close();
	}

	public static void compareVal(String a, String b) {
		for (int i = 36; i > max(a); i--) {
			
			int tempA = 0;
			for (int i1 = 0; i1 < a.length(); i1++) {
				tempA += Math.pow(i, a.length()-1-i1) * toNumber(a.charAt(i1));
			}
			for (int j = 36; j > max(b); j--) {
				int tempB = 0;
				for (int j1 = 0; j1 < b.length(); j1++) {
					tempB += Math.pow(j, b.length()-1-j1) * toNumber(b.charAt(j1));
				}
				if(tempA == tempB) {
					System.out.println("yes");
					//test
					System.out.println("Match values is: " + tempA + " a : "+ i + " b: "+ j);
					return;
				}
			}
		}
		System.out.println("no");
	}

	public static int max(String a) {
		int result = 0;
		int temp;
		for (int i = 0; i < a.length(); i++) {
			temp = a.charAt(i);
			temp = toNumber(temp);
			if (temp > result) {
				result = temp;
			}
		}

		return result;
	}

	public static int toNumber(int a) {
		int temp = a;
		if (temp >= 48 && temp <= 57) {
			return temp - 48;
		} else if (temp >= 65 && temp <= 90) {
			return temp - 55;
		} else {
			return 0;
		}
	}
}
