package basic.days_;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

class Milk{
   private String name;
   private int mprice;
   // 생성자, toString, equals, hashCode, getter/setter
   public Milk(String name, int mprice) {
      this.name = name;
      this.mprice = mprice;
   }

   public String getMname() {      return name;   }
   public int getMprice() {      return mprice;   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      Milk milk = (Milk) obj;
      return mprice == milk.mprice && Objects.equals(name, milk.name);
   }

   @Override
   public int hashCode() {
      return Objects.hash(name, mprice);
   }

   public Object getName() {
	return name;
   }
}
public class Day028 {
	public static void main(String[] args) {
      
      
      //List는 순서가 있는 구조로 데이터를 관리하며, 중복을 허용한다.
      //Lsit( 기차 ) 순서o, 중복o add, get, size, remove, contains
      List<Milk> milks = new ArrayList<>();
      
      milks.add(new Milk("바나나우유", 1300));
      milks.add(new Milk("메론맛우유", 1800));
      milks.add(new Milk("커피우유", 1500));
      milks.add(new Milk("커피우유", 1500));
      
      // for + size 이용해서 데이터 출력
      for(int i = 0; i < milks.size(); i++) {
         Milk milk = milks.get(i);
         System.out.printf("%d     %s       %d\n", i+1, milk.getName(), milk.getMprice());
      }
      System.out.println();
      //오름차순
      System.out.println("\n\n 가격 오름차순 정렬");
      //1. 익명적 객체
      milks.sort( new Comparator <Milk> () {
		@Override public int compare(Milk o1, Milk o2) { return Integer.compare(o1.getMprice(),  o2.getMprice()); }
      });
      //2. 람다식
      milks.sort((o1, o2) -> Integer.compare(o1.getMprice(), o2.getMprice()));
      
      //3. 메서드 참조 Integer 부품객체에 compare라는 기능박스
      //   error : milks.sort(Integer::compare);  compare는 정적메서드가 아니므로 메서드 참조 불가
      // milk라는 객체에서 가격 꺼내야함 
      milks.sort( Comparator.comparingInt(Milk::getMprice) );

      milks.sort((m1,m2)-> Integer.compare(m1.getMprice(), m2.getMprice()) );
      //@FunctionalInterface public interface Comparator<T> { int compare(T o1, T o2); }
      // Comparator는 두 객체를 비교하는 기능을 제공하는 인터페이스입니다.
      //  compare 메서드는 두 객체를 비교하여 음수, 0, 양수를 반환합니다.
      int i = 0;
      for(Milk milk : milks) {
         System.out.printf("%d     %s       %d\n", ++i, milk.getName(), milk.getMprice());
      }
      //void sort(Comparator<? super E> c) : 리스트의 요소들을 주어진 Comparator에 따라 정렬합니다.
      //null을 전달하면 요소들이 Comparable 인터페이스를 구현한 경우 자연 순서
      //Comparator<? super Milk> c - Comparator  비교 부품객체 <? super Milk> Milk 포하함한 부모객체

      // 내림차순 정렬 (람다식)
      System.out.println("\n\n 가격 내림차순 정렬");
      milks.sort((m1, m2) -> Integer.compare(m2.getMprice(), m1.getMprice()));
      i = 0;
      for(Milk milk : milks) {
         System.out.printf("%d     %s       %d\n", ++i, milk.getName(), milk.getMprice());
      }

      // 이름순 오름차순 정렬 (람다식)
      System.out.println("\n\n 이름 오름차순 정렬");
      milks.sort((m1, m2) -> m1.getMname().compareTo(m2.getMname()));
      i = 0;
      for(Milk milk : milks) {
         System.out.printf("%d     %s       %d\n", ++i, milk.getName(), milk.getMprice());
      }

      // 이름순 내림차순 정렬 (람다식)
      System.out.println("\n\n 이름 내림차순 정렬");
      milks.sort((m1, m2) -> m2.getMname().compareTo(m1.getMname()));
      i = 0;
      for(Milk milk : milks) {
         System.out.printf("%d     %s       %d\n", ++i, milk.getName(), milk.getMprice());
      }

      
      //Set은 순서가 없는 구조로 데이터를 관리하며, 중복을 허용하지 않는다.
      //Set(주머니) 순서x, 중복x add, get(순서x), 향상된 for/Iterator, size, remove, contains
      Set<Milk> sets = new HashSet<>();
   
      sets.add(new Milk("바나나우유", 1300));
      sets.add(new Milk("메론맛우유", 1800));
      sets.add(new Milk("커피우유", 1500));
      sets.add(new Milk("커피우유", 1500));
      
      // Iterator 이용해서 데이터 출력
      // Iterator는 Set에서 요소를 순차적으로 접근하기 위한 인터페이스입니다.
      Iterator<Milk> it = sets.iterator();
      int count = 0;
      while(it.hasNext()) {
         Milk milk = it.next();
         System.out.printf("%d     %s       %d\n", ++count, milk.getName(), milk.getMprice());
      }
      System.out.println();
      
      //Map은 key와 값의 쌍으로 데이터를 관리한다.
      //Map(지도) key-value 구조 put, get(key), size, remove, containsKey, containsValue, entrySet, keySet
      // Map은 키와 값의 쌍으로 데이터를 관리하는 자료구조입니다. 키는 중복될 수 없으며, 각 키는 하나의 값에 매핑됩니다.
      Map<String, Milk> maps = new HashMap<>();
      
      maps.put("banana", new Milk("바나나우유", 1300));
      maps.put("melon", new Milk("메론맛우유", 1800)); 
      maps.put("coffee", new Milk("커피우유", 1500));
      maps.put("coffee2", new Milk("커피우유", 1500));
       
      for(String key : maps.keySet()) {
         Milk milk = maps.get(key);
         System.out.printf("%s    %s    %d\n", key, milk.getName(), milk.getMprice());
      }
     
	}
}


/*
Q1. 빈칸 채우기
1.  List는 순서가 [ 있는 ] 구조로 데이터를 관리하며, 중복을 [ 허용 ]
    - 주요 메서드: add, get, size, remove, contains
2. Set은 순서가[ 없는 ] 구조로 데이터를 관리하며,  중복을 [ 허용불가 ]
    - 주요 메서드: add, get(순서x), 향상된 for/Iterator, size, remove, contains
3. Map은 [__key__]와 [__값__]의 쌍으로 데이터를 관리한다. 
    - 주요 메서드: put, get(key), size, remove, contains
    - containsKey, containsValue, entrySet entrykeyset
---

Q2. ArrayList, HashSet, HashMap을 작성하시오.  

1. Milk Dto 클래스 만들기  
   - 속성 : private String mname; private int mprice  

2. milks 이름으로 ArrayList 만들기  
3. 다음의 데이터 넣기  
   new Milk("바나나우유", 1300),  
   new Milk("메론맛우유", 1800),  
   new Milk("커피우유", 1500),  
   new Milk("커피우유", 1500)  
4. for + size 이용해서 데이터 출력  
```
1     바나나우유       1300
2     메론맛우유       1800
3     커피우유         1500
4     커피우유         1500
```
 
5. sets 이름으로 HashSet 만들기  
6. 다음의 데이터 넣기  
   new Milk("바나나우유", 1300),  
   new Milk("메론맛우유", 1800),  
   new Milk("커피우유", 1500),  
   new Milk("커피우유", 1500)  
7. Iterator 이용해서 데이터 출력   
```
1     바나나우유       1300
2     메론맛우유       1800
3     커피우유         1500
```
 
8. maps 이름으로 HashMap 만들기  
9. 다음의 데이터 넣기 (Key-Value 구조)  
   maps.put("banana", new Milk("바나나우유", 1300));  
   maps.put("melon", new Milk("메론맛우유", 1800));  
   maps.put("coffee", new Milk("커피우유", 1500));  
   maps.put("coffee2", new Milk("커피우유", 1500));  

10. for-each + keySet 이용해서 데이터 출력  
```
banana    바나나우유       1300
melon     메론맛우유       1800
coffee    커피우유         1500
coffee2   커피우유         1500
``` 

*/