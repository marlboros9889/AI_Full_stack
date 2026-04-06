package com.the703.basic005_ex;

import java.util.Scanner;

public class SwitchEx001 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		  숫자한개 입력받아
//		     3이면 봄
//		     6이면 여름
//		     9이면 가을
//		     12이면 겨울
	Scanner sc= new Scanner(System.in);
	int a = -1;
	System.out.println("숫자를 입력하세요");
	a = sc.nextInt();    
		  switch(a) {
		           case 3 : System.out.println("봄이다");  break;
		           case 6 : System.out.println("여름이다");  break;
		           case 9 : System.out.println("가을이다");  break;
		           case 12 : System.out.println("겨울이다");  break;
		           default : System.out.println("봄,여름,가을,겨울이 아니다");
		  }
	
	}

}
