package com.the703.v1;

import java.util.Scanner;

public class Bankproject_v1 {

	public static void main(String[] args) {
		
//		Q1. 메뉴판나오게 만들고 사용자가 메뉴 선택시
//	      1을 입력하면 추가기능입니다. 출력구문까지만
//	      2를 입력하면 조회기능입니다. 출력구문까지만
//	      3을 입력하면 입금기능입니다. 출력구문까지만
//	      4를 입력하면 출금기능입니다. 출력구문까지만
//	      5를 입력하면 삭제기능입니다. 출력구문까지만
//	      9를 입력하면 종료합니다.    출력구문까지만
    Scanner sc= new Scanner(System.in);
	//변수	
    int menu= 0; 
    int id= -1, pass= -1, balance= -1; 
		//입력
    for(;;)
           {
		 System.out.println("🌟💰 WELCOME TO BANK SYSTEM 💰🌟\r\n"
		 		+ "\r\n"
		 		+ "[1] ➕ 계좌 추가\r\n"
		 		+ "[2] 🔍 계좌 조회\r\n"
		 		+ "[3] 💵 입금하기\r\n"
		 		+ "[4] 💸 출금하기\r\n"
		 		+ "[5] 🗑️ 계좌 삭제  \r\n"
		 		+ "\r\n"
		 		+ "👉 번호를 선택하세요:");
		 menu = sc.nextInt();	

			// 처리
			if (menu == 1) {
				System.out.println("추가 기능입니다");
				System.out.println("ID 입력하세요");
				id=sc.nextInt();
				System.out.println("PASS 입력하세요");
				pass=sc.nextInt();
				System.out.println("금액을 입력하세요");
				balance=sc.nextInt();
				
			}

			else if (menu == 2) {
				System.out.println("조회 기능입니다");
				//변수  
				int tid=-1, tpass= -1;
				//입력  임시공간에 아이디,비번 입력받기
				//처리
				System.out.println("ID를 입력하세요");
				tid=sc.nextInt();
				System.out.println("PASS 입력하세요");
				tpass=sc.nextInt();
				//아이디와 임시아이디가 같고 비번과 임시비번이 같으면 정보출력
				// 다르다면 비밀번호를 확인해주세요
				
		     		 if (id == tid && pass == tpass)
		     			  System.out.println("\n id" + id +
		     					             "\n PASS" + pass +
		     					             "\n 금액" + balance
		     					             );
		     		 
				else  {System.out.println("비밀번호를 확인해주세요");}
 			}

		    	else if (menu == 3) {
				System.out.println("입금 기능입니다");
			} else if (menu == 4) {
				System.out.println("출금 기능입니다");
			} else if (menu == 5) {
				System.out.println("삭제 기능입니다");
			} else if(menu == 9) {
				System.out.println("종료합니다");
				break;
			}
		}
    
		 
	}

}


/*
Q1. 메뉴판나오게 만들고 사용자가 메뉴 선택시
      1을 입력하면 추가기능입니다. 출력구문까지만
      2를 입력하면 조회기능입니다. 출력구문까지만
      3을 입력하면 입금기능입니다. 출력구문까지만
      4를 입력하면 출금기능입니다. 출력구문까지만
      5를 입력하면 삭제기능입니다. 출력구문까지만
      9를 입력하면 종료합니다.    출력구문까지만





Q2. 무한반복으로 메뉴나오게, 9 나오면 종료
   ■ 힌트
   for(;;) { 
      System.out.println("숫자1을 입력하세요.");
      int a = scanner.nextInt();
      if(a == 1) { break;}
   }
   
   
   
     
 * */

 