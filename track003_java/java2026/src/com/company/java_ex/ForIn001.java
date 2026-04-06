package com.company.java_ex;

import java.util.Scanner;

public class ForIn001 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//		출력내용 :  성적처리 프로그램입니다.
//
//		0. 국어,영어, 수학(0~100만입력받기)  
//		1. 총점 구하기
//		2. 평균 구하기
//		3. 평균이 60점이상이고  각과목이 40점 미만이면 아니라면 합격/ 아니면 불합격
//		4. 평균이 95점이상이면 장학생
//		5. 평균이  90점이상이면 수, 80점이상이면 우, 70점이상이면 미, 60점이상이면 양, 아니라면 가 

        //변수
		Scanner sc= new Scanner(System.in);
		int kor=-1, eng=-1, math=-1, total=-1; double avg=0;
		String std="", jang="", aa="",level="";

		
		//입력
		
		System.out.println(" 학번을 입력하세요 ");
		std=sc.next(); 
		
		for(;;) {
		System.out.println(" 국어점수를 입력하세요 ");
		kor=sc.nextInt();
		if (kor >= 0 &&  kor <= 100 ) break;
		System.out.println(" 점수를 다시 입력하세요 ");
		}
		
		for(;;) {
		System.out.println(" 영어점수를 입력하세요 ");
		eng=sc.nextInt();
		if (eng >= 0 &&  eng <= 100 ) break;
		System.out.println(" 점수를 다시 입력하세요 ");
		}
		
		for(;;) {
		System.out.println(" 수학점수를 입력하세요 ");
		math=sc.nextInt();
		if (math >= 0 &&  math <= 100 ) break;
		System.out.println(" 점수를 다시 입력하세요 ");
		}
		
		
		/* 
		 *  for(;;) {// 무한반복 
		 *  if( 국어점수의 범위가 아니라면 ){
		 *     1. 국어점수 입력 > 100 입력받기
		 *     
		 *     
		 *     System.out.print("국어 점수 입력: ");
                   kor = sc.nextInt();
               if(kor < 0 || kor > 100) {
                            continue;}
                            
                            
		 *     2. 건너뛰기 (continue) }
		 *     
		 *   if( 영어점수의 범위가 아니라면 ){
		 *     1. 국어점수 입력 > 100 입력받기
		 *     2. 건너뛰기 (continue) }
		 *     
		 *   if( 수학점수의 범위가 아니라면 ){
		 *     1. 국어점수 입력 > 100 입력받기
		 *     2. 건너뛰기 (continue) }
		 *     
		 *     //break; 나오기 - 이 위치까지 왔다면 잘 입력한것
		 *     
		 *     
		 */
		
		
		//처리
		total= kor+ eng+ math;
		avg= total/3f;
	    if (avg>= 60 && kor >=40 && eng >= 40 && math >= 40) {aa= "합격";}
	    else {aa ="불합격";}
	    
	    if (avg >= 95) { jang="장학생"; }
        
        if(avg >= 90) { level="수"; }
   else if(avg >= 80) { level="우"; }
   else if(avg >= 70) { level="미"; }
   else if(avg >= 60) { level="양"; }
   else               { level="가"; }
		
		//출력
        System.out.print("==============================================================================\n");
        System.out.print("학번\t"+"국어\t"+ "영어\t"+ "수학\t"+ "총점\t"+ "평균\t"+ "합격여부\t"+ "레벨\t"+ "장학생\n");
        System.out.print("===============================================================================\n");
        System.out.printf("%s\t %d\t %d\t %d\t %d\t %.2f\t %s\t %s\t %s\t",std,kor,eng,math,total,avg,aa,level,jang);
        
//		학번 입력 > std111
//		국어점수 입력 > 100  
//		수학점수 입력 > 100
//		영어점수 입력 > 99
//		=================================================================================== 
//		학번   국어   영어   수학   총점   평균   합격여부   레벨   장학생
//		=================================================================================== 
//		std111   100   100   99   299   99.67   합격   수   장학생
//		
		
		
	}

}
