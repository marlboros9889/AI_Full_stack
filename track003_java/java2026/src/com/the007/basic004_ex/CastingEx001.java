package com.the007.basic004_ex;

import java.util.Scanner;

public class CastingEx001 {

	public static void main(String[] args) {
//		연습문제1)
//	패키지명 : com.company.java003_ex
//	클래스명 : CastingEx001
//	출력내용 :  Scanner이용해서 나누기 프로그램만들기 
//	   숫자입력1>  _입력받기  10   ( ☆자료형을 int )
//	   숫자입력2>  _입력받기  3     ( ☆자료형을 int )   
	    int num1, num2;
	    double result;
	    Scanner sc= new Scanner(System.in);
		System.out.println("숫자를 입력하세요");
	    num1= sc.nextInt();
	    System.out.println("숫자를 입력하세요");
	    num2= sc.nextInt();
	    //처리
	    //num1/num2
	    result=(double)num1/num2;
	    
	  //  System.out.println(result);
	    System.out.printf("%d/%d= %.2f",num1 ,num2 ,result);
	    
	    
		
	
//	   10 / 3 = 3.33

	}
}