package com.the703.basic014;
//1.클래스는 부품객체
//2.클래스는 상태(멤버변수)와 행위(멤버함수)
class farm{
	//상태(멤버변수)
	String name;//인스턴스 변수- heap area- new O- 생성자 
	int age;
	
	static String FarmName="(주) 동물농장"; //클래스 변수-클래스명.변수명
	static int FarmNum;  //클래스 변수 -method area - new X - 생성자 X
	static String FarmBoss;
	static {FarmNum=2; FarmBoss="신동엽"; } //초기화블록
	//행위(멤버함수)
	static	void numPlus() { FarmNum++; }//클래스.메서드- method area - static
	//static에서는 인스턴스메서드 사용불가 
	
	void show() { //인스턴스 메서드- heap area- new O- 생성자
		System.out.println("\n\n::::::::::::::::");
		System.out.println("::이름: "+ this.name);
		System.out.println("::나이: "+ this.age);
		System.out.println("::동물농장 인원: "+ farm.FarmNum);
		
	}
 }

public class Class005 {
	public static void main(String[] args) {
		System.out.println("\n\n0.동물농장");
		System.out.println("::회사이름>" + farm.FarmName);
		System.out.println("::회사사장>" + farm.FarmBoss);
		System.out.println("::회사인원>" + farm.FarmNum);
		
		System.out.println("\n\n1. 동물식구 - this- 각각");
		
		farm cat= new farm();//1)new객체생성 2)farm() 초기화 3)cat번지
		cat.name = "kitty"; cat.age=3; cat.show(); farm.numPlus();
		
		farm dog= new farm();//1)new객체생성 2)farm() 초기화 3)dog번지
		dog.name= "탄이"; dog.age=7; dog.show(); farm.numPlus();
		
		//farm.numPlus();
		
	}

}
//////////////////////////////////////////////////////
/*
-- 초기화 --      기본값		명시적 초기화 		초기화블록 	 생성자
 FarmName		 null		(주)동물농장		(주)동물농장		X
 FarmNum		 0			0				2				X
 FarmBoss		 null		null			신동엽			X
 -------------------------------------------------------------------
 cat	name=null	→		→				→				→
 		age=0
 dog	name=null	→		→				→				→
 		age=0
*/
//////////////////////////////////////////////////////
/* [RUNTIME DATA AREA]
-------------------------------------------------------------------
[METHOD:정보  static,final ]    Farm.class,   Class005.class
Farm.Farmname="(주) 동물농장";//Farm.FarmNum=2;//Farm.FarmBoss="신동엽";//Farm.numPlus()
-------------------------------------------------------------------
[HEAP:동적]            |  [STACK:지역]
2번지{name="탄이",   age=7}    ← dog[2번지]
1번지{name=""Kitty, age=3}    ← cat[1번지]

-------------------------------------------------------------------
*/
//////////////////////////////////////////////////////