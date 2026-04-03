package com.the703.basic005_ex;

import java.util.Scanner;

public class IfEx007_2 {

	public static void main(String[] args) {
//		. 정수를 하나 입력해주세요 > 10
//		2. 정수를 하나 입력해주세요 > 3
//		3. 연산자를 입력해주세요(+,-,*,/) > +
//		10+3=13
		
		//변수 - 정수를 하나 입력해주세요 > 10
		int num1 = 1; int num2 = 1;
		char ch = '\u0000';  String result="";
		Scanner sc= new Scanner(System.in);
		 
		//입력 -정수를 하나 입력해주세요 > 3 // 연산자를 입력해주세요(+,-,*,/) > + 10+3 = 13
		  // 정수를 하나 입력해주세요
		 System.out.println("정수를 입력 해주세요");
		 num1 = sc.nextInt();
		 System.out.println("정수를 입력 해주세요");
		 num2 = sc.nextInt();
		 System.out.println("연산자를 입력해주세요(+,-,*,/)>");
		 ch = (char) sc.next() .charAt(0);
		
		//처리+출력
		 // 만약 + 라면 10 + 3 = 13
		      if  (ch =='+')  { result += (num1 + num2);}
		      
		 else if  (ch =='-')  { result += (num1 - num2);}
		      
		 else if  (ch =='*')  { result += (num1 * num2);}
		      
		 else if  (ch =='/')  { result += String.format("%.2f", num1/(double) num2);}
	   	    
		 System.out.println("" + num1 + ch + num2 + "=" + result);
		 
		 
		 
	}

}
