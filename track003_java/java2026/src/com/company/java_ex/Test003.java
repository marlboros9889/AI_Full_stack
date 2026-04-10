package com.company.java_ex;

public class Test003 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// for 버전
		for (int i = 1; i <= 4; i++) { System.out.print(i + " "); }

		// while 버전
		int i = 1;
		while (i <= 4) { System.out.print(i++ + " "); }

		// do-while 버전
		int j = 1;
		do { System.out.print(j++ + " "); } while (j <= 4);
	}

}
