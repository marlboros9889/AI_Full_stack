package com.the703.basic005_ex;

import java.util.Scanner;

public class IfEx005 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		문자한개를 입력받아 
//		   대문자인지 이면 소문자로,  소문자이면 대문자로 변경하는 프로그램을 작성하시오.
//		   ※  a = 'A' + 32
		//변수
		char ch= '\u0000';
		Scanner sc= new Scanner( System.in);
		
		//입력
		System.out.println("문자 한개를 입력하세요");
		ch = sc.next().charAt(0);  
		//처리
		   if (ch >= 'A' && ch <= 'Z'  )  { ch= (char) (ch + 32); }
		 else if (ch >='a' && ch <= 'z' ) { ch= (char) (ch + 32); }
		   
		   System.out.println(ch);
		   
	}
}

/* ch      'a'=   'A'   +    32 
		      2byte     2byte     +  4byte 
		              (char)    ('A' +32 )
		             
			 System.out.println("소문자");
		 }
		 else if (ch >= 'a' && ch <= 'z')
		 { System.out.println("대문자");}
		 
		//출력
		 /*
		       if (대문자라면) { 'a'(97)= 'A'(65) + 32; }
		  else if (소문자라면) { 'A'(65)= 'a'(97) + 32; }
 */