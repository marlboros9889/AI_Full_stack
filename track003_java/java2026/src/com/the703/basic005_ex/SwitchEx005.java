package com.the703.basic005_ex;

import java.util.Scanner;

public class SwitchEx005 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		숫자한개 입력받아
//	      홀수면 남자
//	      짝수면 여자
		Scanner sc= new Scanner(System.in);
         int  ch = -1;
		
		System.out.println("숫자를 입력 해주세요");
		ch= sc.nextInt();
		
		// 1%2=1   2%2=0   3%2=1    4%2=0
		switch(ch%2) {
        case 1 : System.out.println("남자");  break;
        case 0 : System.out.println("여자");  break;
       default : System.out.println("남자,여자 가 아니다");}
	}

}
