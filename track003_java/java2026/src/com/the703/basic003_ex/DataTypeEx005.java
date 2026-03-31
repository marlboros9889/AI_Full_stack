package com.the703.basic003_ex;

import java.util.Scanner;

public class DataTypeEx005 {

	public static void main(String[] args) {
//Scanner이용해서  성적처리를 입력받고 출력하시오.
//		Scanner sc= new Scanner(System.in);
//	   int score= 0;
//	   국어점수를 입력하시오.  _입력받기    100 
//	   System.out.print("국어점수를 입력하시오");
//	   score= sc.nextInt();
//	   영어점수를 입력하시오.  _입력받기    100
//	   System.out.print("영어점수를 입력하시오");
//	   score= sc.nextInt();
//	   수학점수를 입력하시오.  _입력받기    99
//	   System.out.print("수학점수를 입력하시오");
//	   score= sc.nextInt();
	  
//	   System.out.println("총점은" + sc + "입니다");

		
		//변수 
		int kor=-1, eng=-1, math=-1, total=-1; double avg=0;
		Scanner sc= new Scanner(System.in);
		// Scanner이용해서  성적처리를 입력받고 출력하시오.
		//   국어점수를 입력하시오.  _입력받기    100 
		//   영어점수를 입력하시오.  _입력받기    100 
		//   수학점수를 입력하시오.  _입력받기    99
		System.out.print("국어점수를 입력하시오\n");
		kor= sc.nextInt();
		System.out.print("영어점수를 입력하시오\n");
		eng= sc.nextInt();
		System.out.print("수학점수를 입력하시오\n");
		math= sc.nextInt();
		
		total= kor + eng + math;
	    avg= total/3f; //정수기 나누기 정수는 정수 10/3 
	    
	    System.out.print("총점은" + total +"입니다\n");
	    System.out.print("평균은"+ avg + "입니다\n");
	    
	    System.out.println("총점 : " + total + "\n 평균 : " +avg);
	    System.out.print("총점 : " + total + "\n 평균 : " +avg);
	    System.out.printf("총점 :%d \n 평균 :%.2f ",total,avg);
	    
	  
	}

}
