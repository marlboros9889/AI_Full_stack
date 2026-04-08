package com.the703.basic008;

public class RepeatEx002 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
		//Q  for 1 ~ 10 사이에서 3의 배수의 누적합을 구하라 : 18  , 갯수 세기 : 3
		// ver=1 : 1이 3의 배수라면 누적합 , 카운트
		// ver=1 : 2이 3의 배수라면 누적합 , 카운트
		// ver=1 : 3이 3의 배수라면 누적합 , 카운트
		
		// ver=1 : if (1이 3의 배수라면) { 누적합 , 카운트}
		// ver=1 : if (2이 3의 배수라면) { 누적합 , 카운트}
		// ver=1 : if (3이 3의 배수라면) { 누적합 , 카운트}   
		
         int hap = 0;
		// ver=1 : if (1% 3 == 0) { hap += 1; cnt++;}
		// ver=1 : if (2% 3 == 0) { hap += 2; cnt++;}
		// ver=1 : if (3% 3 == 0) { hap += 3; cnt++;}
         for (int i=1; i<=10; i++ ){if (i% 3 == 0) { hap += i;}} 
         System.out.println("누적합"+ hap );
		
		
		//Q  while 1 ~ 10 사이에서 3의 배수의 누적합을 구하라 : 18
        int i=1; hap = 0; while ( i<=10 ){if (i% 3 == 0) { hap += i;} i++;} 
        System.out.println("누적합"+ hap );
         
		//Q  do while 1 ~ 10 사이에서 3의 배수의 누적합을 구하라 : 18
		
        i=1; hap = 0; do {if (i% 3 == 0) { hap += i;} i++;} while ( i <= 10 );
         System.out.println("누적합"+ hap );
		//for(1에서10 )          { 3배수 합을 구해라  }
		 //for(int i=1; i<=10; i++) { if( i%3==0 ) { } }
		
		
          
                   
             
		
	
		
	}

}
/*    Q 1 ~ 10의 사이에서 3의 배수의 누적합을 구하세요 ::: 18
 * ver-1   말로 풀어서 쓰기
 * 만약 1이 3의 배수의 누적합
 * 만약 2이 3의 배수의 누적합
 * 만약 3이 3의 배수의 누적합
 * 
 * 
 * ver-2   구조 - 제어 , 반복
 * if( 만약 1이 3의 배수의 ){  누적합  }
 * if( 만약 2이 3의 배수의 ){  누적합  }
 * if( 만약 3이 3의 배수의 ){  누적합  }
 * 
 * ver-3 코드
 * int hap = 0
 * if( 1%3==0) {hap += 1; }
 * if( 2%3==0) {hap += 2; }
 * if( 3%3==0) {hap += 3; }
 * 
 * */
 