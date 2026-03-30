package com.the703.basic003_ex;

import java.util.Scanner;

public class DataTypeEx003 {

	public static void main(String[] args) {
        //출력내용 :  Scanner이용해서 태어난 년도를 입력받아 나이 계산하기
		//태어난 년도를 입력하세요. 
		int six = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("태어난 년도를 입력하세요");
		six = sc.nextInt();
	    System.out.println("당신의"+ (2026 - six) +"입니다");
	   //처리 
	   //six = 2026 - six;
	   //당신의 나이는 25살 입니다

	}

}
