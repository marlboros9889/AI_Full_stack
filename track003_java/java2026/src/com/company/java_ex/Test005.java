package com.company.java_ex;

import java.util.Scanner;

public class Test005 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
//		Scanner scanner = new Scanner(System.in);
//		System.out.print("숫자 입력(1,2,3) > ");
//		int day = scanner.nextInt();
//
//		1. if 버전
//		정수를 하나 입력받아 다음 조건에 따라 요일을 출력하는 프로그램을 작성하시오.
//		1이라면 1이다, 2라면 2이다, 3이라면 3이다    1,2,3이 아니다
//
//		2. switch 버전 - 위의 문제를 switch 문으로 작성하시오.
//
//		3. for, while, do while 버전
//		1 2 3 4  
		
		
		Scanner sc= new Scanner(System.in);
		System.out.println( "숫자 입력 >");
		int day = sc.nextInt();

		
		     if (day==1) {
			System.out.println("1이다");}
		else if (day==2) {
			System.out.println("2이다");}
		else if (day==3) {
			System.out.println("3이다");}
		
		else {System.out.println("1.2.3이 아니다");}
		
		
		
		switch (day) {
		case 1: System.out.println("1이다"); break;
		case 2: System.out.println("2이다"); break;
		case 3: System.out.println("3이다"); break;
	    default: System.out.println("1.2.3이 아니다");
		}
		
		// for
		for (int i = 1; i <= 4; i++) { System.out.print(i + " "); }

		// while
		int i = 1;
		while (i <= 4) { System.out.print(i++ + " "); }

		// do-while
		int j = 1;
		do { System.out.print(j++ + " "); } while (j <= 4);
		
		
		
		    for (int i1 = 1; i1 <= 3;   i1++) {
		    for (int j1 = 1; j1 <= i1; j1++) {
		        System.out.print("★");
		    }
		    System.out.println();
		}
		
		int[] arr2 = {1, 2, 3};
		System.out.println(arr2[2]); // 인덱스는 0부터 시작하므로 3은 index 2
		
		// 1. 배열 생성 (크기 5)
		double[] arr = new double[5];

		// 2. 값 넣기 (for + length)
		for (int i11 = 0; i11 < arr.length; i11++) {
		    arr[i11] = 1.1 + (i11 * 0.1); 
		}

		// 3. 출력 (for + length)
		for (int i22 = 0; i22 < arr.length; i22++) {
		    System.out.print(arr[i22] + "  ");
		}
		
		
		
		
	}

}
