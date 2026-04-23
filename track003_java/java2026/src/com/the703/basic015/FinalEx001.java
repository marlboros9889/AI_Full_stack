package com.the703.basic015;
//1. 클래스는 부품객체
//2. 상태(멤버변수-인스턴스변수/클래스변수)와 행위(멤버함수-인스턴스메서드/클래스메서드)
class User002 {
	   final String nation = "Korea";   //명시적함수 *중요콘텐츠*
	   final String jumin;   
	   String name;

	   public User002() { jumin="00000"; }
	   public User002(String jumin, String name) {
	      this.jumin = jumin;
	      this.name = name;
	   }
	}
public class FinalEx001 {
	public static void main(String[] args) {
		
		 User002 user1 = new User002("123456-1234567", "아이유");
	      System.out.println(user1);   
	      
	     // user1.nation = "USA";      
	     // user1.jumin  = "123123-1234321";  //수정이 불가 이유 : Final String Jumin 때문에.
	      user1.name = "IU"; 
	      System.out.println(user1);   
	}

}
/*
 class User002 {
   final String nation = "Korea";   
   final String jumin;   
   String name;

   public User002() { jumin="00000"; }
   public User002(String jumin, String name) {
      this.jumin = jumin;
      this.name = name;
   }
}
 public class FinalEx {
   public static void main(String[] args) {
      User002 user1 = new User002("123456-1234567", "아이유");
      System.out.println(user1);   
      
      user1.nation = "USA";      
      user1.jumin  = "123123-1234321"; 
      user1.name = "IU"; 
      System.out.println(user1);   
   }
}

 */