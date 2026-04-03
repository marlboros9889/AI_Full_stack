package com.the703.basic006;

import java.util.Scanner;

public class ForInfinit {

	public static void main(String[] args) {
		
	// 제어문
    // 조건문: ~라면 if  / switch
	// 반복문:  반복 for / while   / do while
	// 제어조건 : break / continue 
		
	//1. 영역 
	   //  for(;;)  { System.out.println("hello"); }  // {   }  <<  영역
		
		 Scanner sc =new Scanner(System.in);
		 int a =1;
		 
	//2. 빠져 나올 조건	 
	//	for(;;)
	//		    {
	//			System.out.println("hello");
	//			System.out.println("빠져나올래?");
	//			a = sc.nextInt();
	//			if(a==0) break;	
	//	     	}
	//	     int a = -1;    //  a [  -1  ]
	//       for(; a! = -1  ;)  // a 가 0 이면
	//	 
	//	 
	 for(;  a == -1    ;) // for (; ;)   << 영역 안에  종료 조건인  a==0 이 있을시 
			    {//여기서부터
				System.out.println("hello");
				System.out.println("빠져나올래? 0 이면 종료");
				a = sc.nextInt();
		     	}// 여기까지 무한 반복
			 

	}

}
