package com.the703.basic011;

public class Method002 {
		//1. 리턴값  메서드명(★파라미터:재료★)
		// 					hello("문자열")
	public static void hello(String name) { // String name : "설아"
		System.out.println("Hello~ "+ name);
	}
	public static void icecream(int num) { 
		System.out.println("아이스크림 "+ num + "개");
	}
	public static void info(String str , int score) {
		System.out.printf("%s 최종 %d점 \n" , str, score );
	}
	
	
	
	public static void main(String[] args) {
	
		hello("설아");
		hello("주영");
		
		icecream(1);
		icecream(2);
		icecream(3);
		
		info("설아",10);   	//최종 10점
		info("주영",9);		//최종 9점
		info("영탁",11);
		info("청청",12);
		
		
		
	}

	
}
