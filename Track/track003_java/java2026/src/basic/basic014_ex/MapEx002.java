package basic.basic014_ex;

import java.awt.print.Book;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
// 생성자 (기본, 필드), toString, hashCode + equals, setters + getters

class BookDto{
	private String title;
	private String author;
	

public BookDto(String title, String author) {
    this.title = title;
    this.author = author;
}

public String getTitle() { return title; }
public String getAuthor() { return author; }
}
public class MapEx002 {
	public static void main(String[] args) {
		

		Map<String, BookDto> map = new HashMap<>();
		map.put("978-11111", new BookDto("자바의 완성", " 가길동")  );
		map.put("978-22222", new BookDto("파이썬의 기초"," 홍길동") );
		map.put("978-33333", new BookDto("자료구조와 알고리즘", "이순신"));
		
		System.out.println("======================");
		System.out.println("ISBN\t"+"TITLE\t"+"AUTHOR");
		System.out.println("======================");
		
		for( String isbn : map.keySet()) {
			BookDto book = map.get(isbn);
			System.out.println(isbn + "|" + book.getTitle()+ "|"+ book.getAuthor());
			System.out.println("--------------------");
		}
//		for(Entry<String, BookDto> e : map.entrySet()) {
//			System.out.println("%s    %s   %s\n", e.getKey().getTitle(), map.get(key).getAuthor);
//		}
		
		Scanner sc= new Scanner(System.in);
		System.out.println("도서 정보를 제공중입니다");
		System.out.println("ISBN을 입력하세요 >");
		String input = sc.next();
		
		BookDto result = map.get(input);
		
		if(result != null) {
			System.out.println("\n"+ input + "\n"+ result.getTitle()+"\n"+"저자:"+ result.getAuthor());}
		else {System.out.println("📖도서정보가 정확하지 않습니다");}
		
	
		
	}

}
/*
     Scanner scanner = new Scanner(System.in);
      Map<String, BookDto> map = new HashMap<>();
      map.put("978-11111",  new BookDto("자바의 완성" , "가길동"));
      map.put("978-22222",  new BookDto("파이썬 기초" , "홍길동"));
      map.put("978-33333",  new BookDto("자료구조와 알고리즘" , "이순신"));
       
      System.out.println("==============================\r\n" + "ISBN        TITLE        AUTHOR\r\n" + "==============================");
      for(String key :  map.keySet() ) {  //키들의 묶음
         System.out.printf("%s    %s    %s\n" , key , map.get(key).getTitle() , map.get(key).getAuthor());
         System.out.println("------------------------------");
      }

      for(Entry<String, BookDto> e :  map.entrySet() ) {  // 쌍(key:value)의 묶음
         System.out.printf("%s    %s    %s\n" , e.getKey() , e.getValue().getTitle() , e.getValue().getAuthor());
         System.out.println("------------------------------");
      }
      
      Iterator<Entry<String, BookDto>> iter =    map.entrySet().iterator();  // 반복자
       while(iter.hasNext()) {
          Entry<String, BookDto> e  = iter.next();
         System.out.printf("%s    %s    %s\n" , e.getKey() , e.getValue().getTitle() , e.getValue().getAuthor());
         System.out.println("------------------------------");
       }
       System.out.print("도서 정보를 제공중입니다\r\n" + "ISBN을 입력하세요>");  String isbn = scanner.next();
       System.out.println( map.containsKey(isbn)? 
         "📖 선택한 도서 정보: "  + map.get(isbn).getTitle()+" / 저자: "  + map.get(isbn).getAuthor()  : "ISBN 확인바람"); 
*/