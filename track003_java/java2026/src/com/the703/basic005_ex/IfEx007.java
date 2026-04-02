package com.the703.basic005_ex;

import java.util.Scanner;

public class IfEx007 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		. 정수를 하나 입력해주세요 > 10
//		2. 정수를 하나 입력해주세요 > 3
//		3. 연산자를 입력해주세요(+,-,*,/) > +
//		10+3=13
		
		//변수 - 정수를 하나 입력해주세요 > 10
		int num1 = 0; int num2 =0;
		char ch = 0; 
		 Scanner sc= new Scanner(System.in);
		 
		//입력 -정수를 하나 입력해주세요 > 3 // 연산자를 입력해주세요(+,-,*,/) > + 10+3 = 13
		  // 정수를 하나 입력해주세요
		 System.out.println("정수를 입력 해주세요");
		 num1 = sc.nextInt();
		 System.out.println("정수를 입력 해주세요");
		 num2 = sc.nextInt();
		 
		 // 연산자를 입력하세요
		 System.out.println("연산자를 입력해주세요");
		 ch = (char) sc.nextInt()
				
		//처리+출력
		 // 만약 + 라면 10 + 3 = 13
		 if  (ch =='+') {10+3=13}
		
	}

	private static void charAt(int i) {
		// TODO Auto-generated method stub
		
	}

}