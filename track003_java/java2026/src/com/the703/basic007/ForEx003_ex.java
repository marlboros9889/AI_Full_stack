package com.the703.basic007;

public class ForEx003_ex {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//
		//출력내용 :   for 이용
		//1~10까지의 합을 구하시오.
		//변수
		int hap= 0;
	    //입력
		
		//처리
		// hap[누적박스0]   > hap = 0
		// hap[누적박스1]   > hap = 1   0+1        hap = hap+1
		// hap[누적박스3]   > hap = 3  (0+1)+2     hap = hap+2
		// hap[누적박스6]   > hap = 6  (0+1+2)+3   hap = hap+3
		
		//출력
		
		//hap[누적박스6]   > 
		// hap = 6 ( (0+1+2)+3 )   hap = hap+3 }
		 //  (hap+i)    1<10  (hap+i)+a {hap+i}
			                                     
		
           for( int a=1; a<=10; a++ )   { hap = hap+a; }
            
		   System.out.println(hap) ; 
		   
		                                     
 	}        

}
