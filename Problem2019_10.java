package socalcontest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Problem2019_10 {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new BufferedReader(new FileReader("C:\\Users\\Blake\\Desktop\\tee10.txt")));
		int a, b, c, result;
		while (sc.hasNextInt()) {
			a = sc.nextInt();
			b = sc.nextInt();
			c = a + b;
			if (!hasPrimeFactor(a, b)) { // if no prime factor in common get rad
				result = compareRad(a, b, c);
				if (result > a*b) {
					System.out.println("less");
				} else if (result < a*b) {
					System.out.println("greater");
				} else if (result == a*b) {
					System.out.println("equal");
				}
			}
		}
		sc.close();
	}

	public static int compareRad(int a, int b, int c) {
		int result = 1;
		if(a + b == 0) {
			return 0;
		}
		for (int i = 2; i <= a; i++) {
			if (a % i == 0) {
				a /= i;
				while (a % i == 0) {
					a /= i;
					result *= i;
				}
			}
		}
		for (int i = 2; i <= b; i++) {
			if (b % i == 0) {
				b /= i;
				while (b % i == 0) {
					b /= i;
					result *= i;
				}
			}
		}
		for (int i = 2; i <= c; i++) {
			if (c % i == 0) {
				c /= i;
				while (c % i == 0) {
					c /= i;
					result *= i;
				}
			}
		}
		return result;
	}

	public static boolean hasPrimeFactor(int a, int b) {
		if (a < 2 || b < 2) {
			return false;
		}
		for (int i = 2; i <= a; i++) {
			if (a % i == 0 && b % i == 0) {
				System.out.println("bad");
				return true;
			}
		}
		return false;
	}
}
