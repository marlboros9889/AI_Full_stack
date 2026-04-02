package com.the703.basic005_ex;

import java.util.Scanner;

public class IfEx003 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		숫자한개를 입력받아 
//		   만약 1을 입력했다면   one ,   
//		   만약 2을 입력했다면   two ,
//		   만약 3을 입력했다면   three ,
//		   1,2,3이 아니라면  1,2,3이 아니다를 출력하는 프로그램을 작성하시오.
		 
		//변수
		int aa = 0;
		Scanner sc= new Scanner(System.in);
		
		//입력
		System.out.println("숫자를 입력하세요");
		aa= sc.nextInt();
		
		
		//처리 
		       if(aa == 1)   { System.out.println("  one"); }    
		
		  else if(aa == 2)   { System.out.println(" two " ); }
		  else if(aa == 3)   { System.out.println("three");  } 
		  else               { System.out.println("1,2,3이 아니다"); }
		
		//출력
		 // System.out.println(aa == 1 ? "one"   :
		       //          :    aa == 2 ? "two"
		       //          :    aa == 3 ? "three"
		       //          :    "1,2,3이 아니다");        
		
		
	}

}
