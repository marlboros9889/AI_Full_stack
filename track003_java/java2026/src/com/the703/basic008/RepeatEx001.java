package com.the703.basic008;

public class RepeatEx001{
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
//		1.  for , while , do while문을 이용해서 다음과 같이 출력하시오 :  1 2 3 4 5
//		2.  for , while , do while문을 이용해서 다음과 같이 출력하시오 :  5 4 3 2 1
//		3.  for , while , do while문을 이용해서 다음과 같이 출력하시오 :  JAVA1   JAVA2  JAVA3
		System.out.println("\n\n for");
		System.out.print("\n Q1-1. for 1 2 3 4 5 " );
		for(int i=1; i<=1; i++){ System.out.println(i); }
		
	    System.out.print("\n Q1-1. for 5 4 3 2 1 " );
		for(int i=5; i>=1; i=i-1 ) {System.out.println(i);  }
		
		//		System.out.print("JAVA" + 1 );
		//		System.out.print("JAVA" + 2 );
		//		System.out.print("JAVA" + 3 );
		for (int i=1; i<=3; i++) { System.out.print("\n JAVA" + i ); }
		
		
		System.out.println("\n\n while");
		System.out.print("\n Q1-1. for 1 2 3 4 5 " );
		int i=1;while( i<=1 ){ System.out.println(i); i++;}
		
	    System.out.print("\n Q1-1. for 5 4 3 2 1 " );
		 i=5;while (  i>=1 ) {System.out.println(i); i=i-1; }
		
		i=1; while (  i<=3) { System.out.print("\n JAVA" + i );  i++;}
		
	   
		
		System.out.println("\n\n do while");
		System.out.print("\n Q1-1. for 1 2 3 4 5 " );
		 i=1; do { System.out.println(i); i++;} while( i<=1 );
		
	    System.out.print("\n Q1-1. for 5 4 3 2 1 " );
		 i=5; do {System.out.println(i); i=i-1; } while (  i>=1 );
		
		i=1; do { System.out.print("\n JAVA" + i );  i++;} while (  i<=3);
		
		
	}

}
