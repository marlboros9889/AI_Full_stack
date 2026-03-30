package com.the703.basic003_ex;

import java.util.Scanner;

public class DataTypeEx004 {

	public static void main(String[] args) {
		//   	 Scanner 용해서 파이값을 입력받고 출력하시오. 
		Scanner sc = new Scanner(System.in);
		double dou= 3.1415592;
		//     파이값을 입력하시오 > _입력받기    3.141592    ( 자료형선택 )
		System.out.println("파이값을 입력하시오");
		dou= sc.nextDouble();
		//     파이값은 **입니다.

		System.out.println("파이값은"+ dou +"입니다");
		System.out.print("파이값은"+ dou +"입니다\n");
		
		System.out.printf("파이값은 %.0f 입니다\n"  ,dou);
		System.out.printf("파이값은 %.1f 입니다\n"  ,dou);
		System.out.printf("파이값은 %.2f 입니다\n"  ,dou);
		System.out.printf("파이값은 %.3f 입니다\n"  ,dou);
		System.out.printf("파이값은 %.4f 입니다\n"  ,dou);
		System.out.printf("파이값은 %.5f 입니다\n"  ,dou);
		System.out.printf("파이값은 %.6f 입니다\n"  ,dou);
		
		System.out.println(10/3);   //정수 나누기 정수 : 3
		System.out.println(10/3f);  //정수 나누기 실수 : 3.3333333
		System.out.println(10f/3);  //실수 나누기 정수 : 3.3333333
		
		


	}

}
