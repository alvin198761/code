package com.alvin.mock;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

	public static void main(String  [] args) {
		String regex ="(\\d+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher("[[72093367,72117435,72151455,1]]");
		while(m.find()){
			System.out.println(m.group(1));
		}
	}
}
