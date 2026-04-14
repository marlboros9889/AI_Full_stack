package com.the703.basic010;

public class Arr06_ex {
	public static void main(String[] args) {
//		1. new를 이용하여 문자열 배열 ch52개를 만드시오
	
		 
		 
		 
		
			        // 1. 52개의 문자를 담을 배열 생성
			        char[] ch = new char[52];

			        // 2. 대문자(A-Z)와 소문자(a-z) 대입
			        // 대문자 A는 65, 소문자 a는 97 
			        char data = 'A';
//			        ch[0]= data++;
//			        ch[1]= data++;
			        // i 는 변하는 숫자 또는 변하는 값?
			        // ch 는 모아논 값?
			        
			        
			        for (int i=0; i<26; i++)
			        {ch[i] = (char)('A' +i); 
			         ch[i+26]= (char) ('a'+i);
			         }
			        
			
			        // 3. 모음(a, e, i, o, u)의 개수 구하기
			        int count = 0;
			        for (int i = 0; i < ch.length; i++) {
			            char j = ch[i];
			            if (j == 'A' || j == 'E' || j == 'I' || j == 'O' || j == 'U' ||
			                j == 'a' || j == 'e' || j == 'i' || j == 'o' || j == 'u') 
			               {count++;}
			             }
			       

			        // 결과 출력
			        System.out.println("생성된 배열: " + count );
			        System.out.println("모음의 총 개수: " + count + "개");
			

		
	}

}

