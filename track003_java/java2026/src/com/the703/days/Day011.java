package com.the703.days;

import java.util.Scanner;

public class Day011 {
	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);
		char ch= '\u0000';
		
		System.out.println(" a, b, c 중에 입력 >> ");
		ch= sc.next().charAt(0);

		// if  버전에 해당하는 다음에 연결해서 문자를 작성하시오
		// 문자를 한개 입력받아 a이면 apple, b면 banan, c이면 coconut
		
		       if(ch == 'a')   { System.out.println("  apple ");       }    
		  else if(ch == 'b')   { System.out.println(" banana " );      }
		  else if(ch == 'c')   { System.out.println("coconut");        } 
		  else                 { System.out.println("a, b, c가 아니다"); }
		       
		// switch 버전에 해당하는 다음에 연결해서 문제를 작성하시오
		// 문자를 한개 입력받아 a면 apple,  b면 banana, c면 coconut
		    
		       
        switch(ch) {
           case 'a' : System.out.print("apple");  break;
           case 'b' : System.out.print("banana");  break;
           case 'c' : System.out.print("coconut");  break;
           default  : System.out.print("a,b,c가 아니다");   
          } 
   
		       
		       
		// for, while, do while 버전으로 풀으세요
        // 1 2 3 4 5
        
        
        for(int i=1; i<=5; i++) {System.out.print(i);}
        System.out.println();
		       
      int i=1;   while( i<=5) {System.out.print(i); i++; }
          System.out.println();
		 
		
      int i1=1;    do {System.out.print(i1);i1++ ;} while ( i1<=5 );
          System.out.println();
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
