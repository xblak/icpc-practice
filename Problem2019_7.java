package socalcontest;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem2019_7 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new BufferedReader(new FileReader("C:\\Users\\Blake\\Desktop\\tee7.txt")));
		ArrayList<Point> allPoint = new ArrayList<Point>();
		String all = "";
		while (sc.hasNext()) {
			all += sc.next();
		}
		String[] i = all.split(";");
		for (String s : i) {
			getPoint(s, allPoint);
		}
		figure(allPoint);
		
		sc.close();
	}

	public static void getPoint(String s, ArrayList<Point> allPoint) {
		String[] p = s.substring(1, s.length() - 1).split("\\),\\(");
		String[] p1 = p[0].split(",");
		String[] p2 = p[1].split(",");
		allPoint.add(new Point(Integer.parseInt(p1[0]), Integer.parseInt(p1[1])));
		allPoint.add(new Point(Integer.parseInt(p2[0]), Integer.parseInt(p2[1])));
	}

	public static void figure(ArrayList<Point> allPoint) {
		ArrayList<ArrayList<Point>> allFigs = new ArrayList<ArrayList<Point>>();
		int poly = 0;
		while(allPoint.size()!= 0) {
			if(allFigs.isEmpty()) {
				ArrayList<Point> temp = new ArrayList<Point>();
				temp.add(allPoint.get(0));
				temp.add(allPoint.get(1));
				allFigs.add(temp);
				allPoint.remove(1);
				allPoint.remove(0);
			}else{
				boolean added = false;
				for(int j = 0; j < allFigs.size();j++) {
					//System.out.println(" j:"+j  + " realsize" + allFigs.size());
					if(allFigs.get(j).contains(allPoint.get(0)) || allFigs.get(j).contains(allPoint.get(1))) {
						ArrayList<Point> temp = allFigs.get(j);
						temp.add(allPoint.get(0));
						temp.add(allPoint.get(1));
						allFigs.set(j,temp);
						allPoint.remove(1);
						allPoint.remove(0);
						added = true;
						//System.out.println("add to " + j); 
						break;
					}
				}
				if(!added) {
					ArrayList<Point> temp = new ArrayList<Point>();
					temp.add(allPoint.get(0));
					temp.add(allPoint.get(1));
					allFigs.add(temp);
					allPoint.remove(1);
					allPoint.remove(0);
					//System.out.println("new fig");
				}
			}
		}
		for(int i = 0; i < allFigs.size();i++) {
			for(int j = 0; j < allFigs.get(i).size();j++) {
				for(int k = i+1; k < allFigs.size();k++) {
					if(allFigs.get(k).contains(allFigs.get(i).get(j))) {
						ArrayList<Point> temp = new ArrayList<Point>();
						temp.addAll(allFigs.get(i));
						temp.addAll(allFigs.get(k));
						allFigs.set(i, temp);
						allFigs.remove(k);
					}
				}
			}
		}
		for(int i = 0; i < allFigs.size();i++) {
			ArrayList<Point> temp = allFigs.get(i);
			int connect = 0;
			for(int j = 0; j < temp.size();j++) {
				connect = 0;
				for(int k = 0; k < temp.size();k++) {
					if(temp.get(j).equals(temp.get(k))) {
						connect++;
					}
				}
				if(connect>2) {
					break;
				}
			}
			if(connect == 2) {
				poly++;
			}
		}
		
		System.out.println(allFigs.size() + " " + poly);
	}
}                                          