package com.the703.basic006_ex;

import java.util.Scanner;

public class A001_ex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//변수
		//자료형 : 기본형/ 참조형 (String)
		//기본형 : 정수 / 실수
		//정수  : byte(1) < short/ char (2) < int(4) < long(8) 
		//실수  :float(4) < double(8)
		// boolean 빼고 타입변환 가능

		Scanner sc= new Scanner(System.in);
		int kor=-1, eng=-1, math=-1, total=-1; double avg=-1;
		String std="", jang="", aa="", level="";
		
		
		
		//입력
        //학번 입력 > std111
//		System.out.println("학번입력"); std= sc.next();	
//		//국어점수 입력 > 100
//		System.out.println("국어입력"); kor=sc.nextInt();
//		//수학점수 입력 > 100
//		System.out.println("수학입력"); math=sc.nextInt();
//		//영어점수 입력 > 99
//		System.out.println("영어입력"); eng=sc.nextInt();
		
		System.out.println("학번을 입력하세요");
		std=sc.next();
		// for(; !(kor>=0 && kor<=100) ;)
		for(;;)
		{
		System.out.println("국어점수를 입력하세요" );
		kor=sc.nextInt();
		if(kor>=0 && kor<=100) break;
		System.out.println("[오류] 점수를 다시 입력하세요");}
	
		for(;;) {
		System.out.println("영어점수를 입력하세요");
		eng=sc.nextInt();
		if(eng>=0 && eng<=100) break;
		System.out.println("[오류] 점수를 다시 입력하세요");}

		for(;;) {
		System.out.println("수학점수를 입력하세요");
		math=sc.nextInt();
		if(math>=0 && math<=100) break; 
		System.out.println("[오류] 점수를 다시 입력하세요");}
	
			
		// 처리 - 연산자 먼저 () 값(++ , --, 산술 ) 비교( >, <) 조건(&&, || tkagkd) 대입(=)
		//     - 제어문 (#if / #switch    반복( #for / wihle / do while )
		
		//1. 총점 구하기
		total = kor + eng + math ;
		//2. 평균 구하기
		avg = total/3.0; //강제 형변환    // 정수/ 실수
		//3. 평균이 60점이상이고  각과목이 40점 미만이면 아니라면 합격/ 아니면 불합격
					
	    if (avg>= 60 && kor >= 40 && eng >= 40 && math >= 40) { aa = "합격"; }
		 else { aa= "불합격"; }
		//4. 평균이 95점이상이면 장학생
	  //  switch(intavg/10) { //95-> tn
		 if (avg >= 95) { jang ="장학생";}
		//5. 평균이  90점이상이면 수, 80점이상이면 우, 70점이상이면 미, 60점이상이면 양, 아니라면 가 
		jang = avg >= 95 ? "장학생" : "장학생";
		aa = avg<60 ? "불합격" : kor <40 || eng <40 || math <40 ? "불합격" : "합격";
		
	
	
			 if(avg >= 95) { jang="장학생"; }
	         
	         if(avg >= 90) { level="수"; }
	    else if(avg >= 80) { level="우"; }
	    else if(avg >= 70) { level="미"; }
	    else if(avg >= 60) { level="양"; }
	    else               { level="가"; }
	         
//	          switch (int)avg/10){ //95->9 수
//	        	  case 10 : case 9: level = "수"; break;
//	        	  case 8 :  level = "우"; break;
//	        	  case 7 :  level = "미"; break;
//	        	  case 6 :  level = "양"; break;
//	        	  case 6 :  level = "가"; break;
//	        	  
	        	  
	         
	         

	       
         System.out.print("==============================================================================\n");
         System.out.print("학번\t"+"국어\t"+ "영어\t"+ "수학\t"+ "총점\t"+ "평균\t"+ "합격여부\t"+ "레벨\t"+ "장학생\n");
         System.out.print("===============================================================================\n");
         System.out.printf("%s\t %d\t %d\t %d\t %d\t %.2f\t %s\t %s\t %s\t",std,kor,eng,math,total,avg,aa,level,jang);
               
}   
}
