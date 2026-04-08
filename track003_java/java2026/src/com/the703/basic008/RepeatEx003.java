package com.the703.basic008;

public class RepeatEx003 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		## RepeatEx003번으로 작성해주세요~~!  for/while/do while 버젼으로
//        1에서 30 안에서 3의 배수이면서 2의 배수은 숫자와 갯수를 구하는 프로그램
/*
 *  ver-1 :  1~ 30 중에 3의 배수이면서 2의 배수인 숫자와 갯수를 구해라
 *  ver-2 : 3의 배수이면서 2의 배수인 숫자
 *          3의 배수 : a%3==0 3 6(b) 9
 *          2의 배수 : a%2==0 2 4 6(b) 8 10
 *          if ( a%3==0 && a%2==0 ) {a 갯수 카운트, 출력 }
 *          1이 3의 배수이면서 2의 배수라면 숫자 출력 갯수 카운트
 *          2가 3의 배수면서 2의 배수라면 숫자 출력 갯수 카운트 
 *          
 *          
 *         ( 1이 2의 배수면서 3의 배수라면 ){ 숫자출력 , 카운트}
 *         ( 2이 2의 배수면서 3의 배수라면 ){ 숫자출력 , 카운트}
 *         ( 3이 2의 배수면서 3의 배수라면 ){ 숫자출력 , 카운트}
 *         
 *        if ( 1이 2의 배수면서 3의 배수라면 ){ 숫자출력 , 카운트}
 *        if ( 2이 2의 배수면서 3의 배수라면 ){ 숫자출력 , 카운트}
 *        if ( 3이 2의 배수면서 3의 배수라면 ){ 숫자출력 , 카운트}
 *        
 *         
 * */		
	
		int cnt = 0;
		for (int i=1; i<=30; i++) 
		if( i%2==0 && i%3==0 )
		{System.out.println("2의 배수이면서 3의 배수 숫자:" + i); cnt++; }
		
		
		
		
//    int cut=0;
//    
//    for(int i=1; i<=30; i++) {if (i%3==0 && i%2==0) { cut += i ; System.out.print(i); }}
//    System.out.println( cut + "배수합"  );
//    	
    
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
