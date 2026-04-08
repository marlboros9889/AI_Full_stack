package com.the703.basic008;

public class WhileBasic {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//for 1 2 3 출력
		 System.out.print(1);
		 System.out.print(2);
		 System.out.print(3);
	    
		 System.out.println("ver-1 for");
		for(int i=1; i<=3; i++) { System.out.print(i );} //영역 
		
		
	
		System.out.println("ver-2 while");
		int i=1;
		while ( i<=3 ) { System.out.print(i ); i++; }
		
		
		System.out.println("ver-3 while");
		int i1=1;
		do { System.out.print(i1 ); i1++; } while ( i1<=3 );
		
		
		
		
		
		
	}

}
