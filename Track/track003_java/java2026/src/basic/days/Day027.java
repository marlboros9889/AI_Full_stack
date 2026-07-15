package basic.days;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

// 1. 클래스는 부품객체
// 2. 상태( 멤버변수 ) 와 행위( 멤버함수 )
// 3. DTO 데이터 전송 객체
//	   

class UserInfo{
	private String name;
	private int age;
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserInfo(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	} 
	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", age=" + age + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(age, name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;
		return age == other.age && Objects.equals(name, other.name);
	}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;	}
	
	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}

}

public class Day027 {
	public static void main(String[] args) { 
		//추가: add, 가져오기:get, 갯수:size, 삭제:remove, 포함여부:contains
		List<UserInfo> users = new ArrayList<>(); // 중복도 출력
		users.add(new UserInfo("아이언맨", 50));
		users.add(new UserInfo("헐크", 40));
		users.add(new UserInfo("캡틴", 120));
		users.add(new UserInfo("캡틴", 120));
		
		for( int i=0; i<users.size(); i++) { //size 갯수
			UserInfo u = users.get(i);	//get(번호) 꺼내오기
			System.out.printf("%d %s %d\n", (i+1), u.getName(), u.getAge());
			
		}
		// 주머니 : set -  추가: add, 가져오기: 향상된 for/Iterator, 갯수:size, 삭제:remove, 포함여부:contains
		Set<UserInfo> sets = new HashSet<>(); // 중복은 출력하지 않음
		sets.add( new UserInfo("아이언맨", 50));
		sets.add( new UserInfo("헐크", 70));
		sets.add( new UserInfo("캡틴", 120));
		sets.add( new UserInfo("캡틴", 120));
		
		int cnt = 0;
		for(UserInfo u: sets) {
			System.out.println(u);
		}
	}
}
/*

Q1.   다음 빈칸을 채우시오
1. 콜렉션프레임워크
- [  ##1. 배열    ]의 단점을 개선한 클래스 [##2. (클래스) 객체     ]만 저장가능   
- 저장공간의 크기를 [##3.  동적배열   ]으로 관리함.

String [] arr = new String[100];  * 자료형고정, 갯수 고정
List<UserInfo> list = new ArrList<>();   *내가원하는 객체(틀), 갯수 무한대

2. 핵심 인터페이스 [##4. 3개 :  List, Set,  Map   ]
- 인터페이스를 통해서 틀이 잡혀 있는 방법으로 
  다양한 컬렉션 클래스를 이용함.

  List : ##5.   기차   인덱스여부 [ 순서: O ] ,  중복허용여부 [ O ] ,  
  				 [ add, get, size, remove, contains  ] 
  Set  : ##6.   주머니  인덱스여부 [ X ] ,  중복허용여부[ X ]  ,  
  				 [ add, get(순서)X - 향상된 for/ Iterator, size, remove, contains   ] 
  Map  : ##7.   사전   [ 키:값 ] - 쌍(Entry),        
  				 [ put, get(key), size, remove, contain   ] 


				 
Q2.  ArrayList, HashSet 을 작성하시오.
1. UserInfo    Dto 클래스만들기    - 속성 :  private  String name; private  int age;
2. users ArrayList 만들기
3. 다음의 데이터 넣기
   new UserInfo("아이언맨" , 50) , new UserInfo("헐크" , 40) , new UserInfo("캡틴" , 120) , new UserInfo("캡틴" , 120)
4. for+size 이용해서  데이터 출력

1     아이언맨       50
2     헐크       	40
3     캡틴  	    120
4     캡틴  	    120
 

5. sets  HashSet 만들기
6. 다음의 데이터 넣기
   new UserInfo("아이언맨" , 50) , new UserInfo("헐크" , 40) , new UserInfo("캡틴" , 120)
7. 향상된 for 이용해서 데이터 출력

1     아이언맨       50
2     헐크       	40
3     캡틴  	    120

*/