package com.gzz.createcode;

import java.util.ArrayList;
import java.util.List;

public class Number {

	public static void main(String[] args) {
		List<String> list1 = new ArrayList<String>();
		list1.add("A");
		list1.add("B");
		list1.add("C");
		list1.add("D");
		List<String> list2 = new ArrayList<String>();
		list2.add("B");
		list2.add("C");
		list2.add("F");

//		list1.retainAll(list2);
		list2.retainAll(list1);
		System.out.println(list2);//运行结果

	}
}
