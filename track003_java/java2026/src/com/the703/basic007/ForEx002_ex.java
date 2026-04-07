package com.the703.basic007;

import java.util.Scanner;

public class ForEx002_ex {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       
		//사용자에게 단을 입력받아 해당하는 
		//구구단을 출력해주는 프로그램을 작성하시오. FOR문을 이용하시오.
	     Scanner sc= new Scanner(System.in);
         //변수
	   
	     
	     //입력
	     System.out.println("단을 입력해주세요");
	      int dan= sc.nextInt();
	    
	     
	     //처리 + 출력
		//	      for(int i=1; i<=9; i++)
		//	     System.out.printf( "%d * %d = %d" , 2,1,2*1 );
		//	     System.out.printf( "%d * %d = %d" , 2,2,2*2 );
		//	     System.out.printf( "%d * %d = %d" , 2,3,2*3 );
	      for(int i=1; i<=9; i++)
	      { System.out.printf( "\n%d * %d = %d" , dan,i,dan*i );}
	     
	}

}
