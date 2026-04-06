package com.the703.basic005_ex;

import java.util.Scanner;

public class SwitchEx003 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		   문자한개 입력받아
//		     a이면 APPLE
//		     b이면 BANANA
//		     c이면 COCONUT
		Scanner sc= new Scanner(System.in);
		char ch= '\u0000';
		
		System.out.println("문자를 입력 해주세요");
		ch = sc.next().charAt(0);
		
		switch(ch) {
        case 'a' : System.out.println("APPLE");  break;
        case 'b' : System.out.println("BANANA");  break;
        case 'c' : System.out.println("COCONUT");  break;
        
        default : System.out.println("APPLE,BANANA,COCONUT 아니다");}
		
		
	}

}
