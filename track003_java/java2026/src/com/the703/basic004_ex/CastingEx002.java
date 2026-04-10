package com.the703.basic004_ex;

import java.util.Scanner;

public class CastingEx002 {

	public static void main(String[] args) {
	
		
// 1.  국어, 영어, 수학, 총점☆자료형을 int 
//		 2.  레벨 - 평균이 90점대이면 레벨 9, 80점대면 8, 70점대면 7, 60점대면 6,,,,
//
//		국어점수 입력 > 100
//		영어점수 입력 > 100
//		수학점수 입력 > 99
         int kor=-1, eng=-1, math=-1, total=-1, level; double avg=0;
         Scanner sc = new Scanner(System.in);
         
         System.out.print("국어점수를 입력하세요\n");
          kor= sc.nextInt();
          System.out.print("영어점수를 입력하세요\n");
          eng= sc.nextInt();
          System.out.print("수학점수를 입력하세요\n");
          math= sc.nextInt();

//		:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//		:: GOOD  IT SCORE ::
//		:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//		국어   영어   수학   총점   평균   레벨
//		100   100   99   299   99.67   9
//
         //처리
          total= kor + eng + math;
          avg= total/3f;              //정수 / 실수(float)     
          //avg= total/3.0;           //정수 / 실수(double)
          //avg= (double)total/3;     //실수(double) / 정수
          //99.67 → 99.67/10 = 9.967  →  (int) 9.967 → 9
          level = (int) (avg/10);
          
          System.out.print("::::::::::::::::::::\n");
          System.out.print(":: GOOD  TI  SCORE ::\n");
          System.out.print("::::::::::::::::::::\n");
          System.out.println();
          System.out.println("국어\t 영어\t 수학\t 총점\t 평균\t 레벨\t");
          System.out.printf("%d\t%d\t%d\t%d\t%.2f\t%d",kor,eng,math,total,avg,level);
          
       
		}

	}


