package basic.basic013_ex_;
//-클래스는 부품객체
//-상태(멤버변수: 클래스변수, 인스턴스변수)와 행위(멤버함수:클래스메서드, 인스턴스메서드)
/*
	Account a1 = new Account(1, 100);
	Account a2 = new Account(2, 200);
 	[method (정보, static, final )]  : Account.class / Account.accountCount=1
  	[heap]								[stack]
  
  	2번지:Account{balance=200, id=2}   ←   a1(2번지)
  	1번지:Account{balance=100, id=1}   ←   a1(1번지)
  										[main]
  
  
 */

class Account {
    private int balance;  // 인스턴스 변수 - 각각 (this) = heap area - new - 생성자          
    public static int accountCount=0; // 클래스변수 - 공유 - method area - new X
    public final int id; //인스턴스 변수 - 각각 (this) - heap area - new - 생성자            

	public Account(){this.id = ++accountCount;}
    public Account(int id, int balance) {
        this(); //Account()
        this.balance = balance; 
    }

    // getter/setter
    public int getBalance() { return balance; }
    public void deposit(int amount) { balance += amount; }
    public void withdraw(int amount) { balance -= amount; }

    @Override
    public String toString() {
        return "Account [id=" + id + ", balance=" + balance + "]";
    }
}

public class Repeat002_oop {
	public static void main(String[] args) {
		/* [method (정보, static, final )]  : Account.class 
		 * 
		 * */
		Account a1 = new Account(1, 100);
        Account a2 = new Account(2, 200);

        a1.deposit(50);  
        a2.withdraw(30); 

        System.out.println(a1);  //Account [id=1, balance=150]
        System.out.println(a2); //Account [id=2, balance=170]

        System.out.println("총 계좌 수 = " + Account.accountCount);  // 총계좌수 : 2
        System.out.println("a1.id = " + a1.id);  // a1.id = 1
        System.out.println("a2.id = " + a2.id);  // a2.id = 2


	}

}

/*
📝 문제: OOP 개념(2) — 캡슐화 / static / final
Q1. 캡슐화(Encapsulation)란 무엇이며, 위 코드에서 어떻게 구현되었는지 설명하시오.
	-private : 외부에서 접근을 제한
	정의: 외부에서 접근을 제한,제어
	구현: balance를 private 로 제한선언, deposit, withdraw, getBalance 메서드를 통해서만 접근
	
Q2. 접근제어자의 범위를 넓은 것부터 좁은 것까지 순서대로 쓰시오.
	pubilc (어디서든지) > protected(상속) > default(package-폴더) > private(클래스내부)
	
Q3. static 키워드의 의미를 메모리 구조와 연결지어 설명하고, 위 코드에서 어떤 변수에 적용되었는지 쓰시오.
	의미 : 모든객체에서 공유, method area 저장 - 객체생성(new)와 관계없이 접근이 가능
	메모리 :  클래스명.변수명,  클래스명.메서드
	적용 : Account.accountCount
	
Q4. final 키워드의 의미를 설명하고, 위 코드에서 어떤 변수에 적용되었는지 쓰시오.
	의미: 변경불가 클래스(상속X), 변수(상수:변경,수정,할당 안됨), 메서드(오버라이드)
	final id : 한번 초기화되면 변경불가 , this.id = ++accountCount; 초기화
	
Q5. Account.accountCount의 값은 얼마인가? 왜 그렇게 되는지 설명하시오.
	클래스변수 : Account.accountCount
	값 : 2
	Account() 객체가 2개 / 생성될떄마가 기본생성자 호출 ++accountCount;를 실행
	
	
Q6. a1.id와 a2.id의 값은 각각 얼마인가?
	a1.id = 1
	a2.id = 2
	
Q7. 출력 결과를 쓰시오.
	Account [id=1, balance=150]
	Account [id=2, balance=170]
	총 계좌 수 = 2
	a1.id = 1
	a2.id = 2

Q8. static 메서드와 인스턴스 메서드의 차이를 설명하시오.
	static 메서드 - 객체 생성 없이 호출가능, 클래스에 속함
	인스턴스 메서드 - 객체 생성 후에 호출가능, 객체에 속함
	
Q9. final 키워드가 변수, 메서드, 클래스에 각각 적용될 때 의미를 설명하시오.
	변수: 값 변경불가(상수), 메서드 : 오버라이딩 불가, 클래스: 상속 불가
	
Q10. 캡슐화의 장점은 무엇인가?
	데이터 보호 (외부 직접 접근 차단 )
	유지보수 용이 ( 내부 구현변경시 외부형향 최소화 )
	

*/