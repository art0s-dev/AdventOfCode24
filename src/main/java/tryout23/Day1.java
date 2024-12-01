package tryout23;

import static java.lang.Integer.parseInt;

import java.util.Arrays;
import java.util.List;

public final class Day1 {
	
	static String test = "1abc2\n"
			+ "pqr3stu8vwx\n"
			+ "a1b2c3d4e5f\n"
			+ "treb7uchet";
	static int testValue = 142;

	public static void main(String[] args) {
		
		int count = 0;
		
		for(String line: test.split("\n")) {
			String a = line.replaceAll("\\D", "");
			String[] numlist = a.split("");
			List<String> list = Arrays.asList(numlist);
			String first = list.get(0);
			
			if(list.size() > 0) {
				String last = list.get(list.size() - 1);
				count +=  parseInt(first + last);
			} else {
				Integer combined = parseInt(first.toString() + first.toString());
				count += combined;
			}
		}
		
		System.out.println(count);
	}
	

}
