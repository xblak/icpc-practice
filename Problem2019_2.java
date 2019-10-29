package socalcontest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Problem2019_2 {
	public static void main(String[] args) throws FileNotFoundException {
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<ArrayList<String>> cores = new ArrayList<ArrayList<String>>();

		Scanner sc = new Scanner(new BufferedReader(new FileReader("C:\\Users\\Blake\\Desktop\\tee2.txt"))); 
		// remove non alphanumeric characters and save it
		while (sc.hasNext()) {
			words.add(sc.next().replaceAll("[^a-zA-Z0-9]", "").toLowerCase());
		}
		words.removeAll(Arrays.asList("", null));
		Collections.sort(words);
		words = (ArrayList<String>) words.stream().distinct().collect(Collectors.toList());
		// find all cores with similar words
		if (words.isEmpty()) {
			System.out.println("***");
		} else {
			for (String s : words) {
				if(!findAll(s, words).isEmpty()) {
					cores.add(findAll(s, words));
				}
			}
			// print
			if (cores.isEmpty()) {
				System.out.println("***");
			} else {
				printCores(cores);
			}
		}
		sc.close();

	}

	public static ArrayList<String> findAll(String cur, ArrayList<String> words) {
		ArrayList<String> result = new ArrayList<String>();
		// add all similar words
		result.addAll(findDelete(cur, words));
		result.addAll(findInsert(cur, words));
		result.addAll(findReplace(cur, words));
		result.addAll(findTran(cur, words));
		Collections.sort(result);
		result = (ArrayList<String>) result.stream().distinct().collect(Collectors.toList());
		if (result.size() > 0) {
			result.add(0, cur);
		}
		return result;
	}

	// Delete a single character.
	public static ArrayList<String> findDelete(String cur, ArrayList<String> words) {
		if (cur.length() == 1 || words.isEmpty()) {
			return new ArrayList<String>();
		}
		ArrayList<String> result = new ArrayList<String>();
		try {
			ArrayList<String> temp1 = (ArrayList<String>) words.stream().filter(s -> s.startsWith(cur.substring(0, 1)))
					.collect(Collectors.toList());
			ArrayList<String> temp2 = (ArrayList<String>) words.stream().filter(s -> s.startsWith(cur.substring(1, 2)))
					.collect(Collectors.toList());
			for (int i = 1; i < cur.length(); i++) {
				String curd1 = cur.substring(0, i) + cur.substring(i + 1);
				for (String s : temp1) {
					if (curd1.equals(s)) {
						result.add(s);
					}
				}
			}
			for (String s : temp2) {
				if (cur.substring(1).equals(s)) {
					result.add(s);
				}
			}
		} catch (StringIndexOutOfBoundsException e) {
		}

		return result;
	}

	// Insert a single alphabetic character.
	public static ArrayList<String> findInsert(String cur, ArrayList<String> words) {
		ArrayList<String> result = new ArrayList<String>();
		for (String s : words) {
			if (s.length() == cur.length() + 1) {
				int count = 0;
				String tempCur = cur + "*";
				String tempS = s + "*";
				for (int i = 0; i < tempCur.length(); i++) {
					if (count == 2) {
						break;
					}
					if (tempCur.charAt(i - count) != tempS.charAt(i)) {
						count++;
					}
					if (i == tempCur.length() - 1 && count == 1) {
						result.add(s);
					}
				}
			}
		}
		return result;
	}

	// Replace a single character by a different alphabetic character.
	public static ArrayList<String> findReplace(String cur, ArrayList<String> words) {
		ArrayList<String> result = new ArrayList<String>();
		for (String s : words) {
			if (s.length() == cur.length()) {
				int count = 0;
				for (int i = 0; i < cur.length(); i++) {
					if (count == 2) {
						break;
					}
					if (cur.charAt(i) != s.charAt(i)) {
						count++;
					}
					if (i == cur.length() - 1 && count == 1) {
						result.add(s);
					}
				}
			}
		}
		return result;
	}

	// Transpose any two adjacent characters.
	public static ArrayList<String> findTran(String cur, ArrayList<String> words) {
		ArrayList<String> result = new ArrayList<String>();
		for (String s : words) {
			if (s.length() == cur.length()) {
				int count = 0;
				int adj = 0;
				for (int i = 0; i < cur.length(); i++) {
					if (count == 3) {
						break;
					}
					if (cur.charAt(i) != s.charAt(i)) {
						count++;
						adj++;
					} else {
						adj = 0;
					}
					if (adj == 2 && s.charAt(i) == cur.charAt(i - 1) && cur.charAt(i) == s.charAt(i - 1)) {
						result.add(s);
					}
				}
			}
		}
		return result;
	}

	// print cores and similar words
	public static void printCores(ArrayList<ArrayList<String>> cores) {
		for (ArrayList<String> s : cores) {
			if (!s.isEmpty()) {
				System.out.print(s.get(0) + ": "); // print core
				for (String i : s) {
					if (i == s.get(0))
						continue; // skip the core
					System.out.print(i + " ");
				}
				System.out.println();
			}
		}
	}
}
