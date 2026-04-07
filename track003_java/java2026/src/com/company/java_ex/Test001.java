package com.company.java_ex;

import java.util.Scanner;

public class Test001 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     
		Scanner sc= new Scanner(System.in);
		
		
		   //변수
		int a= -1, kor=-1, eng=-1, math=-1;
		
	      //입력
	      /*      for(;;){
	       *          국어점수 입력 > 100  
	       *          if(잘썼으면,,,, 국어점수의 범위라면){ 나와! break;}
	             } 
	       */ 
	       for(;;) {
	    	   System.out.println("국어점수를 입력하세요");
	    	  kor=sc.nextInt();
	    	  if(kor>= 0 || kor<= 100) break;
	    	  System.out.println("[오류]점수를 다시 입력하세요"); }
	    	   
	    	   /*      for(;;){
	       }
	       *          영어점수 입력 > 100  
	       *          if(잘썼으면,,,, 영어점수의 범위라면){ 나와! break;}
	             } 
	       */
	       for(;;) {
	    	   System.out.println("영어점수를 입력하세요");
	    	  eng=sc.nextInt();
	    	  if(eng>= 0 || eng<= 100) break;
	    	  System.out.println("[오류]점수를 다시 입력하세요"); }
	      /*      for(;;){
	       *          수학점수 입력 > 100  
	       *          if(잘썼으면,,,, 수학점수의 범위라면){ 나와! break;}
	      */
	       
	       for(;;) {
	    	   System.out.println("수학점수를 입력하세요");
	    	  math=sc.nextInt();
	    	  if(math>= 0 || math<= 100) break;
	    	  System.out.println("[오류]점수를 다시 입력하세요"); }
	      //처리
	      //출력
	
		
	}

}
