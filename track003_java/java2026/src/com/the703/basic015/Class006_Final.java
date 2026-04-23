package com.the703.basic015;

//1. Final 변경하지마
//1)클래스는 부품객체
//2)클래스(상속:x)는 상태(멤버변수:상수)와 행위(멤버함수:)
/* Final Class = 재사용 하지마, 상속 하지마 */
class FinalEx extends Object{
	static final String child = "5 - 5"; //클래스변수 - method area - new x- 인스턴스(this) x
	String name; //인스턴스 변수 - heap area - new O - 생성자 () - this O
	/*Final*/ void show() { System.out.println(child+"\t"+ name);} // 인스턴스 메서드
	
	
}
class FinalExSon extends FinalEx{ 
	@Override void show() { System.out.println("나한테 맞게 수정");}
}

//class Test extends Color{ }
public class Class006_Final {
	public static void main(String[] args) {
	  //FinalEx.child = "5 - 12";
		
	}

}
/*
final ( 하지마 )

1) 클래스 (상속 X / 재사용 X  / extends 사용 X )
2) 멤버변수 (상속O / 값변경 X )
3) 멤버함수 (부모기낭 수정 X / @Override 못함)
*/ 
 