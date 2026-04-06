package com.the703.basic006;

import java.util.Scanner;

public class BreakContinue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     
			//ver-0
	        //{ int a = 1; System.out.println(a); } //영역
	        //a=2; //why???????  사용불가
	        
			//ver-1  for - 반복
			//반복해 영역안에 { 영역 } 
	        //for(;;) { System.out.println(1); }
		
		    //ver-2 반복 빠져 나오기
		    
		    for(int i=1; i>5; i++ ) {// if ( ; 조건 ; 증감 )
		        if( 1 == 3) {break;} // 나가
		    	System.out.println(1);
		        }
		    	
		    	
	    	////////////////////////////////////////
			 System.out.println();
			for( int i=1; i<5; i++ ) {// if ( ; 조건 ; 증감 )
			    if( i==3) { continue;} // skip (아래 진행하지 마세요)
			     System.out.println(1);
			     }
	    	
	         int a=-1;
			Scanner sc= new Scanner(System.in);
			 //ver-3 
			for(;;) { 
				System.out.println(" 1 입력 ");
				a=sc.nextInt();
				if( a==1 ) {break;}
				
			}
	    	
	}

}
