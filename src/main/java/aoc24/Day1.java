package aoc24;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Collections.sort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1 {
	static List<Integer> testLeft = new ArrayList<>(List.of(3,4,2,1,3,3));
	static List<Integer> testRight = new ArrayList<>(List.of(4,3,5,3,9,3));
	static List<Integer> left = new ArrayList<>();
	static List<Integer> right = new ArrayList<>();
	static Pattern pattern = Pattern.compile("\\d++", Pattern.MULTILINE);
	
	public static void main(String[] args) throws IOException {
		fillLists();
		
		System.out.println(measureSimilarity(left, right));
	}


	/**
	 * Part 1
	 * @param left
	 * @return
	 */
	public static int measureDistance(List<Integer> left, List<Integer> right) {
		sort(left);
		sort(right);
		int diff = 0;
		
		for(int i = 0; i < left.size(); i++) {
			int leftnum = left.get(i);
			int rightnum = right.get(i);
			
			diff += max(leftnum, rightnum) -  min(leftnum, rightnum);
		}
		
		return diff;
	}
	
	/**
	 * Part 2
	 * @param left
	 * @param right
	 * @return
	 */
	public static int measureSimilarity(List<Integer> left, List<Integer> right) {
		Map<Integer, Integer> quantityRight = new HashMap<>();
		for(Integer i: right) {
			if(quantityRight.containsKey(i)) {
				quantityRight.put(i, quantityRight.get(i) + 1);
			} else {
				quantityRight.put(i, 1);
			}
		}
		
		int score = 0;
		
		for(Integer i: left) {
			Integer quantifier = quantityRight.get(i);
			score += i * (quantifier == null ? 0 : quantifier);
		}
		
		return score;
	}
	
	/**
	 * fillLists
	 * Reads the input file and prepares the lists
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private static void fillLists() throws IOException, FileNotFoundException {
		File input = new File("inputs/Day1.txt"); //the file format is like "666    7777"
		try(BufferedReader br = new BufferedReader(new FileReader(input))){
			String line;
            while ((line = br.readLine()) != null) {
            	Matcher matcher = pattern.matcher(line); //Read all the decimals from string
            	boolean isLeft = true; //so we have a left and a right number we add them to list
            	while (matcher.find()) {
            		String match = matcher.group(0);
            		Integer num = Integer.parseInt(match);
            		if(isLeft) { //the first is always left
            			left.add(num);
            			isLeft = false;
            		} else {
            			right.add(num);
            			isLeft = true;
            		}
            		
		        	 System.out.println("Match: " + match);
		        }
            }
		}
	}
	
}
