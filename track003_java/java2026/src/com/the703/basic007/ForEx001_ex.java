package com.the703.basic007;

public class ForEx001_ex {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
			//		q1  for문을 이용해서 다음과 같이 출력하시오 :   1 2 3 4 5 
			 System.out.println("\n *출력* ");
			 for (int i=1; i<=5; i++ ) 
			 { System.out.print(i + "\t"); }
			 
			//		q2  for문을 이용해서 다음과 같이 출력하시오 :   5 4 3 2 1 
			 System.out.println("\n *출력* ");
			 for (int i=5; i>=1; i=i-1 )
			 { System.out.print(i + "\t"); } 
			//		q3  for문을 이용해서 다음과 같이 출력하시오 :   JAVA1 , JAVA2 , JAVA3 
			 
			 for (int i=1; i<=3; i++ )
			 {System.out.println(" JAVA" + i +"\t");}
			 //{System.out.println((i==1 ? "" : ",") + JAVA" + i +"\t");}
			 //
		 	 //System.out.println("\n \nQ4 : JAVA1, JAVA2, JAVA3");
			 //System.out.println("\nUPGRADE");
			 //System.out.print("   " + "JAVA" + 1 + "\t" );
			 //System.out.print(" , " + "JAVA" + 2 + "\t" );
			 //System.out.print(" , " + "JAVA" + 3 + "\t" );
						 
			//		q4  for문을 이용해서 다음과 같이 출력하시오 :   HAPPY3 ,HAPPY2, HAPPY1 
			//System.out.println(" \n \nQ4 : HAPPY3, HAPPY1, HAPPY1, ");
			//System.out.print("   " + "HAPPY" + 3 + "\t" );
			//System.out.print(" , " + "HAPPY" + 2 + "\t" );
			//System.out.print(" , " + "HAPPY" + 1 + "\t" );
			 for (int i=3; i>=1; i=i-1 )
			 {System.out.println("HAPPY" + i +"\t");}
			 
			//		q5  for문을 이용해서 다음과 같이 출력하시오 :   0,1,2  
			 System.out.println("\n *출력* ");
			 for (int i=0; i<=2; i++ ) 
			 { System.out.print(i); } System.out.println();
			 
			//		q6  for문을 이용해서 다음과 같이 출력하시오 :   0,1,2, ,,,중간생략 ,,, 99 
			 System.out.println(" *출력* ");
			 for (int i=0; i<=99; i++ ) 
			 { System.out.print("\t" + i); } System.out.println();
			 
			//		q7  for문을 이용해서 다음과 같이 출력하시오 :   10, 9,,,,중간생략 ,,, , 1 
			 System.out.println("\n *출력* ");
			 for (int i=10; i>=1; i=i-1 ) 
			 { System.out.print(i); } System.out.println();
			 
			//		q8  for문을 이용해서 다음과 같이 출력하시오 :   0, 2, 4, 6, 8 
			 System.out.println("\n *출력* ");
			 for (int i=0; i<=10; i=i+2 ) 
			 { System.out.print(i); } System.out.println();
			 
			//		q9  for문을 이용해서 다음과 같이 출력하시오 :   0, 2, 4, 6, 8 ,,,중간생략 ,,, 18 
			 System.out.println("\n *출력* ");
			 for (int i=0; i<=20; i=i+2 ) 
			 { System.out.print(i); } System.out.println();
			 
		
		
		
		
		
	}

}
