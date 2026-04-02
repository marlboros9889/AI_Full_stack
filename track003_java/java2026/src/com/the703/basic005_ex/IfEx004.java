package com.the703.basic005_ex;

import java.util.Scanner;

public class IfEx004 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	문자한개를 입력받아 
	//	   대문자인지,  소문자인지 판별하는 프로그램을 작성하시오.
	//	   ※  대문자  ch>='A' && ch<='Z' / 소문자  ch>='a'  &&  ch<='z'  
		Scanner sc = new Scanner(System.in);
		//변수
		// char ch + '\u0000':
		// Scanner sc = new Scanner(System.in);
		  System.out.print("문자 한개를 입력하세요: ");
		//입력
		 char ch = sc.next().charAt(0);
		
		 
		 //처리
		 
        if (ch >= 'A' && ch <= 'Z')       {
                                          System.out.println("대문자입니다.");}
        else if (ch >= 'a' && ch <= 'z')  {
                                           System.out.println("소문자입니다.");}
        else                              {
                                           System.out.println("영문자가 아닙니다.");}
        
        
		//출력
		// System.out.println ( ch>= 'A' && ch<= 'Z' ?  "대문자");
        // System.out.println ( ch>= 'a' && ch<= 'z' ?  "소문자");

	}

}
