package com.spring.test;

public class Gugudan {

	public  void execute() {
		for(int dan=2;dan<10;dan++) {
			System.out.println(dan+"단");
			for(int gop=1;gop<10;gop++) {
				System.out.println(dan +"X" +gop+"="+ dan*gop);
			}
			System.out.println();
		}
	}
}
