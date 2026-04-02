package com.the703.basic005_ex;

import java.util.Scanner;

public class IfEx006 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//	 숫자를입력을받아
//		   홀수면 남자, 짝수면 여자를 출력하는 프로그램을 작성하시오.
//		   ※  num%2==0  짝수
		//변수
		int num =0;
		Scanner sc= new Scanner(System.in);
		//입력
		System.out.println("숫자를 입력하세요");
		num = sc.nextInt();
		//처리
		//  를 출력하는 프로그램을 작성하시오남자, 짝수면 여자
		if      (num%2==1 ) {System.out.println("남자");}
		else if (num%2==0 ) {System.out.println("여자");}
		
	}

}
