package com.company.java_ex;

import java.util.Scanner;

public class Test007 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		 Scanner sc= new Scanner(System.in);
		 System.out.println("숫자 입력");
		 int day = sc.nextInt();
		 
		 switch (day) {
		 case 1: System.out.println("1이다"); break;
		 case 2: System.out.println("2이다"); break;
		 case 3: System.out.println("3이다"); break;
		 default: System.out.println("1.2.3이 아디나");
		 
		 }
	}

}
