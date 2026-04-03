package com.the703.basic005_ex;

import java.util.Scanner;

public class IfEx008 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

          //변수
		Scanner scanner= new Scanner(System.in);
		   int kor=-1, eng=-1,math=-1,total=-1; double avg=0;
		   String std="" , jang="" , aa="" , level="";
		   
		   
				   
//		학번 입력 > std111
		// System.out.println("학번입력>");  std = scanner.next();
//		국어점수 입력 > 100
		// System.out.println("국어점수입력>"); kor = scanner.nextInt();
//		수학점수 입력 > 100
		// System.out.println("수학점수입력>"); math = scanner.nextInt();
//		영어점수 입력 > 99
		// System.out.println("영어점수입력>"); eng = scanner.nextInt();
		   
		  //입력
		 System.out.println("학번을 입력하세요");
		 std=scanner.next();
		 System.out.println("국어점수를 입력하시오>");
		 kor=scanner.nextInt();
		 System.out.println("영어점수를 입력하시오>");
		 eng=scanner.nextInt();
		 System.out.println("수학점수를 입력하시오>");
		 math=scanner.nextInt();
		 
		//처리+
//			1. 총점 구하기
		 // total = kor + eng + math ; 
		 
//			2. 평균 구하기
		 // avg = total/3.0;
		 
//			3. 평균이 60점이상이고  각과목이 40점 미만이면 아니라면 합격/ 아니면 불합격
		 //   if (avg>= 60 && kor >= 40 && eng >= 40 && math >= 40) { pass = "합격"; }
		 // else { pass= "불합격"; }
		 
//			4. 평균이 95점이상이면 장학생
		 // if (avg >= 95) { jang ="장학생"}
		 
//			5. 평균이  90점이상이면 수, 80점이상이면 우, 70점이상이면 미, 60점이상이면 양, 아니라면 가
		 
		  // pass = avg<60 ? "불합격" : kor <40 || eng <40 || math <40 ? "불합격" : "합격";
		  //  jang = avg <95?"" : "장학생";
		 total= kor + eng + math;
         avg= total/3f;   
        // level = (int) (avg/10);
          if(avg >= 60 && kor >= 40 && eng >= 40 && math >= 40 ){ aa="합격"; }
          else { aa="불합격"; }
          
          if(avg >= 95) { jang="장학생"; }
          
               if(avg >= 90) { level="수"; }
          else if(avg >= 80) { level="우"; }
          else if(avg >= 70) { level="미"; }
          else if(avg >= 60) { level="양"; }
          else               { level="가"; }
               
//             level =  avg>= 90? "수"
//            		    avg>= 80? "우"
//            		    avg>= 70? "미"
//            		    avg>= 60? "양"
//            		    avg>= 50? "가"
               
              
         //출력
         //		============================================= 
//		학번   국어   영어   수학   총점   평균   합격여부   레벨   장학생
//		============================================= 100
//		std111   100   100   99   299   99.67   합격   수   장학생
		
         System.out.print("==============================================================================\n");
         System.out.print("학번\t"+"국어\t"+ "영어\t"+ "수학\t"+ "총점\t"+ "평균\t"+ "합격여부\t"+ "레벨\t"+ "장학생\n");
         System.out.print("===============================================================================\n");
         System.out.printf("%s\t %d\t %d\t %d\t %d\t %.2f\t %s\t %s\t %s\t",std,kor,eng,math,total,avg,aa,level,jang);
         
         //삼항연산
          
	}

}
