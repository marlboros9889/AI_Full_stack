package com.the703.basic011_ex;
public class MethodEx007 {

	//public static 리턴값 메서드( 재료 ) { return; 
	public static String  add( String a, String b) {return "맛있는" +a +b; }
	
	
	
	public static void main(String[] args) {
		
		// 두개의 문자열을 더해 다음과 같이 출력하는 메서드를 작성
		
		String a= "Choco", b= "Milk";
		String res= add(a,b);
				System.out.println(res);
				
		//public static 리턴값 메서드( 재료 ) { return; }		
	
	}

}
