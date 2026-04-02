package com.the703.basic005_ex;

import java.util.Scanner;

public class IfEx002 {


	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		출력내용 : 숫자 한개를 입력받아 
//		   양수라면 양수  , 음수라면 음수  ,0이라면 zero를 출력하는 프로그램을 작성하시오.

		    //변수
		int a = 0; 
		Scanner sc= new Scanner(System.in);
		
		    //입력
		System.out.println("숫자를 입력하세요");
		 a= sc.nextInt();
		
		    //처리   
		//     양수라면                     양수  
		     if ( a > 0 ) {System.out.println("양수");}
		//     음수라면                         음수
		else if ( a < 0 )  {System.out.println("음수");}
		//      0이라면                         zero
		else if ( a == 0) {System.out.println("zero");}
		
		
		 // 조건 ? 참 : 거짓
		   // System.out.println( a > 0 > " 양수" : ( a<0 ? " 음수" : " zero"));
		     
		     
	}

}
