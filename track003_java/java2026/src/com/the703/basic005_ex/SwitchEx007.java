package com.the703.basic005_ex;

import java.util.Scanner;

public class SwitchEx007 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		평균 한 개 입력받아
//	    90~100점대면 수
//	    80~90점(90점미만)대면  우
//	    70~80점(80점미만)대면  미
//	    60~70점(70점미만)대면  양
//	    나머지 가
		//변수
		Scanner sc= new Scanner(System.in);
		double avg=0;
		//입력
		System.out.println("숫자를 입력하세요");
		avg = sc.nextDouble();
		
		//처리+출
		switch((int)avg/10) { //89.9 → 우: 89.0/10 →8.99(int)8
        case 10 :case 9 :  System.out.println("수");  break;
        case 8 : System.out.println("우");  break;
        case 7 : System.out.println("미");  break;
        case 6 : System.out.println("양");  break;
        default : System.out.println("가");
		}
		
	}

}

//100/10= 10 93/10=9