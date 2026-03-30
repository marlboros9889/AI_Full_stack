package com.the703.basic003_ex;

import java.util.Scanner;

public class DataTypeEx002 {

	public static void main(String[] args) {
//      출력내용 :  Scanner 이용해서 나이 입력받고 출력하시오.
		int one =  14;
		Scanner sc = new Scanner(System.in);
		//Scanner scanner = new Scanner(System.in);
//	    좋아하는 수(정수)   입력하시오 > _입력받기
		System.out.print("좋아하는 숫자를  입력하시오");
		one = sc.nextInt();
//	    좋아하는 숫자는 ** 입니다
		System.out.println("좋아하는 숫자는"+ one +"입니다");
		//System.out.print("좋아하는 숫자는"+ one +"입니다\n");
		


	}

}
