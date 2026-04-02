package com.the703.basic005_ex;

import java.util.Scanner;

public class IfEx001 {

	public static void main(String[] args) {
		
	//	출력내용 : 평균을 입력받아 60점이상이면 합격,  불합격여부를 출력하는 프로그램을 IF로 작성하시오.
		
		//변수
		   int axe = 100;
		   Scanner sc= new Scanner(System.in);
		   
	    //입력
		   System.out.println("평균을 입력하세요");
		   axe= sc.nextInt();
		   
		//처리 if(  평균을 입력받아 60점이상이면 ) { 합격 } else { 불합격 }
		   if( axe >= 60 ) { System.out.println("   합격 "); } 
		   else { System.out.println("  불합격 "); }
		//출력
		  //삼함 연산사 -  조건?  참 : 거짓
		   // System.out.pirintl(" axe >= 60 ?"합격  : 불합격 ");
		   // if( axe < 60 ) { System.out.println("   불합격 "); } 
		  //  else            { System.out.println("  합격 "); }
	}

}
