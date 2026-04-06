package com.the703.basic005_ex;

import java.util.Scanner;

public class SwitchEx002 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		   숫자한개 입력받아
//		     3,4,5이면 봄
//		     6,7,8이면 여름
//		     9,10,11이면 가을
//		     12,1,2이면 겨울

		 Scanner sc= new Scanner(System.in);
		 int a = -1;
		 
		 System.out.println("숫자를 입력하세요");
		 a = sc.nextInt();
		 
		 switch(a) {
		 // 3/3=1(몫) 4/3=1(몫) 5/3=1(몫)
		         case 3 : case 4 : case 5 :  System.out.println("봄이다");  break;
		 // 6/3=2(몫) 7/3=2(몫) 8/3=2(몫)       
		         case 6 : case 7 : case 8 :  System.out.println("여름이다");  break;
		 // 9/3=3(몫) 10/3=3(몫) 11/3=(몫)        
		         case 9 :case 10 :case 11 :   System.out.println("가을이다");  break;
		 // 12/3=4(몫)  1/3=0(몫)   2/3=0(몫)      
		         case 12 :case 1 :case 2  :   System.out.println("겨울이다");  break;
		         default : System.out.println("봄,여름,가을,겨울이 아니다");

//		         case 1 :   System.out.println("겨울이다");  break;
//		         case 2 :   System.out.println("겨울이다");  break;
//		         case 3 :   System.out.println("겨울이다");  break;
//		 case 4 :case 0 :   System.out.println("겨울이다");  break;
	}

}

}