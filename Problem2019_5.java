package socalcontest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem2019_5 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new BufferedReader(new FileReader("C:\\Users\\Blake\\Desktop\\tee5.txt")));
		ArrayList<Double> allPoint = new ArrayList<>();
		int N = sc.nextInt();
		for (int i = 0; i < N * 2; i++) {
			allPoint.add(sc.nextDouble());
		}
		findLongest(allPoint);
	}

	public static void findLongest(ArrayList<Double> allPoint) {
		double result = 100;
		for (int i = 0; i < allPoint.size(); i += 2) {
			double lineXA = allPoint.get(i);
			double lineYA = allPoint.get(i + 1);
			double lineXB, lineYB;
			if(i + 2 != allPoint.size()) {
				 lineXB = allPoint.get(i + 2);
				 lineYB = allPoint.get(i + 3);
			}else {
				 lineXB = allPoint.get(0);
				 lineYB = allPoint.get(1);
			}
			
			double length = 0;
			
			for (int j = 0; j < allPoint.size(); j += 2) {
				double pX = allPoint.get(j);
				double pY = allPoint.get(j + 1);
				if (pointToLine(lineXA, lineYA, lineXB, lineYB, pX, pY) > length) {
					length = pointToLine(lineXA, lineYA, lineXB, lineYB, pX, pY);
				}
			}
			if(length<result) {
				result = length;
			}
		}
		System.out.printf("%.2f", result);
	}

	public static double pointToLine(double lineXA, double lineYA, double lineXB, double lineYB, double pX, double pY) {
		double a = lineYA - lineYB;
		double b = (lineXB - lineXA);
		double c = lineXA * lineYB - lineYA * lineXB;

		double d = Math.abs(((a * pX + b * pY + c)) / (Math.sqrt(a * a + b * b)));
		return d;
	}
}
