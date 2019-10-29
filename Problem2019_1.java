package socalcontest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Problem2019_1 {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new BufferedReader(new FileReader("C:\\Users\\Blake\\Desktop\\tee1.txt")));
		ArrayList<String> index = new ArrayList<String>();
		ArrayList<String> file = new ArrayList<String>();
		while(sc.hasNext()) {
			String temp = sc.next();
			if(temp.contains("_")) {
				file.add(temp);
			}else {
				index.add(temp);	
			}		
		}
		for(int i = 0; i < index.size();i++) {
			boolean found = false;
			for(int j = 0; j < file.size ();j++) {
				if(file.get(j).startsWith(index.get(i))) {
					file.remove(j);
					j--;
					found = true;
				}
			}
			if(found) {
				index.remove(i);
				i--;
			}
		}
		Collections.sort(index);
		Collections.sort(file);
		
		if(index.isEmpty()&&file.isEmpty()) {
			System.out.println("No mismatches.");
		}else {
			for(String j : file) {
				System.out.println("F " + j);
			}
			for(String i : index) {
				System.out.println("I " + i);
			}
		}	
		sc.close();
	}
	
	public static boolean iMatchF(String i, String f){
		if(i.equals(f.substring(0, i.length()))) {
			return false;
		}
		return true;
	}
}
