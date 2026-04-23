package com.the703.v1;
import java.util.Arrays;
import java.util.Scanner;
public class Bankproject_v5 {

	public static void main(String[] args) {

		 //변수                          0   1    2
		///////////////////////////////////////////////////////////////////////////////////////	
		  String []id=new String[3];       //  one two three
		  String []pass = new String[3];   // 1111 2222 3333
		  double []balance = new double[3];// 1100 2200 3300   
		      int menu=-1;  
		      Scanner sc = new Scanner(System.in);
	    ///////////////////////////////////////////////////////////////////////////////////////
	 	      
		      while( menu !=9 ) {
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
				
					if (menu == 9 ) {System.out.println("종료합니다"); break;}
					
			   else if (menu == 1 ) {
				   int find = -1; 
				   //1. 0번쨰가 빈칸이라면 - 빈칸인번호 찾기 find 빈칸번호 0 넣기
				   //2. 1번째가 빈칸이라면 - 빈칸인번호 찾기 find 빈칸번호 1 넣기
				   //3. 2번째가 빈칸이라면 - 빈칸인번호 찾기 find 빈칸번호 2 넣기
				   //if (balance 0번째가 0이라면 ) {find 빈칸번호 0 넣기 }
				   //if (balance 1번째가 0이라면 ) {find 빈칸번호 1 넣기 }
				   //if (balance 2번째가 0이라면 ) {find 빈칸번호 2 넣기 }
				   if (balance[0]==0) {find=0;}
				   if (balance[1]==0) {find=1;}
				   if (balance[2]==0) {find=2;}
				   
				   if(find==1 ){System.out.println("가입불가!"); continue; } //find가 -1이면 꽉찼음! 아래 사용자값 입력받기만 하면
				   //2.0번째 입력받기
				   
					System.out.println("아이디 입력>");
					id[0] = sc.next();
					System.out.println("비밀번호 입력>");
					pass[0] = sc.next();
					System.out.println("잔액 입력>");
					balance[0] = sc.nextInt();
			   							}
					else if (menu >=2 && menu <=5 ) {
					
						//2-1 사용자 확인
						int tempid=-1, temppass=-1;
						System.out.println("아이디 입력");  tempid=sc.nextInt();
						System.err.println("비밀번호 입력"); temppass=sc.nextInt();
					
						{	System.out.println("정보를 확인해주세요"); continue; }
						
						
//						switch( menu ) {
//						case 2 : System.out.printf ("ID : %d\nPASS : %d\n BALANCE : %d\n" , id,pass,balance);    break;
//						case 3 : System.out.print("입금할 금액 > ");balance+= sc.nextInt();                       break;
//						case 4 : System.out.print("출금할 금액 >");    int tempbalance= sc.nextInt();
//						         System.out.println(tempbalance> balance ? "잔액 부족! 출금불가": " 출금완료 ! 현재잔액 :" + (balance -= tempbalance));
//						break;
//						case 5 : System.out.print("계좌 삭제 (Y / N)");char again= sc.next().charAt(0); 
//						    if (again=='Y'|| again=='y') {id = -1; pass=-1;; balance=-1;} break;
						    
						}
						
					}
					
				
				
	
	

     } 
   }
}