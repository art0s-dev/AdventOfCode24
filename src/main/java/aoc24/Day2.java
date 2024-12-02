package aoc24;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2 {
	static Pattern pattern = Pattern.compile("\\d++", Pattern.MULTILINE);
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		var testlevellist = List.of(
		    List.of(7, 6, 4, 2, 1),
		    List.of(1, 2, 7, 8, 9),
		    List.of(9, 7, 6, 2, 1),
		    List.of(1, 3, 2, 4, 5),
		    List.of(8, 6, 4, 4, 1),
		    List.of(1, 3, 6, 7, 9)
		);
		
		var levellist = fillLists();

			
		int saveLevels = testlevellist.stream()
				.mapToInt(level -> isSave(level))
				.sum();
		
		System.out.println(saveLevels);
	}
	
	public static int isSave(List<Integer> list) {
		int save = isIncOrDec(list, 0, false) ? 1 : 0;
		System.out.println("is Save : " + save);
		return save;
	}
	
	public static boolean isIncOrDec(List<Integer> list, int index, boolean errorTolerance) {
		if(index + 1 >= list.size())
			return true;
		
		int current = list.get(index);
		int next = list.get(index +1);
			
		if(current == next) {
			if(!errorTolerance) {
				return false; 
			} else {
				errorTolerance = false;
			}
		}
		
		var followsMaximumDiff =  max(current, next) - min(current, next) <= 3;
		
		if(!followsMaximumDiff) {
			if(!errorTolerance) {
				return false; 
			} else {
				errorTolerance = false;
			}
		}

		if(index != 0) {
			Integer prev = list.get(index - 1);
			var decFirstThenInc = prev > current && next > current; //erst dekrement dann inkrement - 6 3 4
			var incFirstThenDec = prev < current && next < current; //erst inkrement dann dekrement - 4 5 2
			
			if(decFirstThenInc || incFirstThenDec) {
				if(!errorTolerance) {
					return false; 
				} else {
					errorTolerance = false;
				}
			}
				
		}

		return isIncOrDec(list, index +1, errorTolerance); 
	}
	
	private static List<List<Integer>> fillLists() throws IOException, FileNotFoundException {
		File input = new File("inputs/Day2.txt"); //the file format is like "1 2 3 4 2"
		
		List<List<Integer>> ret = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(input))){
			String line;
            while ((line = br.readLine()) != null) {
            	List<Integer> list = new ArrayList<>();
            	Matcher matcher = pattern.matcher(line);
            	while (matcher.find()) {
            		String match = matcher.group(0);
            		Integer num = Integer.parseInt(match);
		        	System.out.println("Match: " + match);
		        	list.add(num);
		        }
            	ret.add(list);
            }
		}
		
		return ret;
	}

}
