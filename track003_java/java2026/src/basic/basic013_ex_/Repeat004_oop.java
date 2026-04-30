package basic.basic013_ex_;

abstract class Fruit {
    public String toString() { return "Fruit"; }
    public abstract void myfruit();

public class Repeat004_oop {
	public static void main(String[] args) {
		 
		Fruit[] fruits = { new Apple(), new Banana(), new Coconut() };
	    for (Fruit f : fruits) {
	    f.myfruit();
	    }

	}

}


/*
📝 문제: OOP 개념(4) — 추상화 / 인터페이스
Q1. abstract란 무엇인가?  
	A1. Is - A : 고양이는 동물이다.
	    공통기능을 일반화하여 자식클래스가 구체적으로 구현하도록 강제
	    
Q2. interface란 무엇인가?  
	A2. Can do this : ~ 할수 있어요~
Q3. abstract와 interface의 공통점과 차이점을 설명하시오.

Q4. 다음과 같이 출력되게 코드를 작성하시오.
1) 출력 화면:
사과는 빨갛다.
바나나는 노랗다.
코코넛은 코코하다.

2) 상속도:

코드
     Fruit
↑       ↑       ↑
Apple  Banana  Coconut

3) 주어진코드
abstract class Fruit {
    @Override public String toString() { return "Fruit"; }
    public abstract void myfruit();
}
public class Abstract004 {
    public static void main(String[] args) {
        Fruit[] fruits = { new Apple(), new Banana(), new Coconut() };
        for (Fruit f : fruits) {
            f.myfruit();
        }
    }
}

*/