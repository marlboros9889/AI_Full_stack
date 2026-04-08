package com.the703.basic007;

public class ForBasic {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	   // Step1 ))  줄바꿈 안된 Print 이용해서 1  2  3 
		System.out.println("\n*step)1 출력 ");
		System.out.print(1);
		System.out.print(2);
		System.out.print(3);
		System.out.println("\n*step)2 for ");
		
		// for(;;){  빠져 나올 조건 }
		// Step2-1 ))  반복되는 영역                    System.out.println(1);
		
		// Step2-1 ))  반복되는 영역 >> 변수 찾기         System.out.println(1,2,3으로 바뀜);     
		
		// Step2-1 ))  패턴찾기 시작>종료>변환            시작:1 종료:3 변화:1  
		
		for (int i=1; i<=3; i++)     {System.out.print(i);}
		
		
		
		// Step3)) 
		
		System.out.println("\n*step)3 for 연습");
		// 패턴 : 시작 1; 종료 10 ; 변화 1
		for (int i=1; i<=10; i++)     {System.out.print(i);}  System.out.println();
		
		System.out.println("\n*step)3 for 연습");
		// 패턴 : 시작 2; 종료 8 ; 변화 1
		for (int i=2; i<=8; i++)     {System.out.print(i);}  System.out.println();
		
		System.out.println("\n*step)3 for 연습");
		// 패턴 : 시작 3; 종료 9 ; 변화 3
		for (int i=3; i<=9; i=i+3)     {System.out.print(i);}  System.out.println();
		
		System.out.println("\n*step)3 for 연습");
		// 패턴 : 시작 1; 종료 1 ; 변화 -2
		for (int i=5; i>=1; i=i-2)     {System.out.print(i);}  System.out.println();
		
		System.out.println("\n*step)3 for 연습");
		// 패턴 : 시작 1; 종료 10 ; 변화 1
		for (int i=1; i<=10; i++)     {System.out.print(i);}  System.out.println();
		
		// Hi1   Hi2  Hi3  { 변수 }    for ( 시작; 종료; 변화 )
		System.out.print("Hi" + 1);
		System.out.print("Hi" + 2);
		System.out.print("Hi" + 3);
		
		
		
		
		
		
		
		
		
	}

}
