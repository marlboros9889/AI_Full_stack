package basic.basic014_ex;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import basic.basic014.UserDto;


public class MapEx001 {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("피구왕" ,"통키");
		map.put("제빵왕", "김탁구");
		map.put("요리왕", "비룡");

		System.out.println("=================");
		System.out.println("KING\t"+ "NAME");
		System.out.println("=================");
		
		for( String king : map.keySet()) {
			// (Entry<String, String> m: map.entrySet()) {
			// System.out.println("%s    %s\n", m.getkey(), m.getValue());
			// }
			System.out.println(king + "\t" + map.get(king));
			System.out.println("----------------");
			}
		
		Scanner sc = new Scanner(System.in);
		System.out.println(" KING 의 정보를 제공중입니다 ");
		System.out.println(" 이름을 입력하세요 > ");
		String input = sc.nextLine();
		
		String name = map.get(input);
		
		if(name != null) {System.out.println("\n"+ input + ":" + name);	}
		
		else {System.out.println(" 존재하지 않는 KING 입니다");	}
		
		// 만약 key가 있다면 key를 주면 value 값 줄게
		// if (map.containsKey(사용자가 입력한 값)) { map.get(key값) } 
		}
	}





/*

1. MAP 만들기
KEY         VALUE
978-11111   new BookDto("자바의 완성" , "가길동")
---------------------
978-22222   new BookDto("파이썬 기초" , "홍길동")
---------------------
978-33333   new BookDto("자료구조와 알고리즘" , "이순신")

Map<String, BookDto> map = new HashMap<>();


2 다음과 같이 문제풀기
2-1. BookDto 만들기   {    private String title;  private String author;}
2-2. 다음과 같이 출력
2-3. 사용자에게 KING의 이름을 입력받아 해당하는 값 출력
==============================
ISBN        TITLE        AUTHOR
==============================
978-11111 | 자바의 완성 | 가길동
------------------------------
978-22222 | 파이썬 기초 | 홍길동 
------------------------------
978-33333 | 자료구조와 알고리즘 | 이순신 
------------------------------
도서 정보를 제공중입니다
ISBN을 입력하세요> 978-22222

 선택한 도서 정보: 파이썬 기초 / 저자: 홍길동


























import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class BookDto {
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

        // 1. MAP 만들기
        Map<String, BookDto> map = new HashMap<>();
        map.put("978-11111", new BookDto("자바의 완성", "가길동"));
        map.put("978-22222", new BookDto("파이썬 기초", "홍길동"));
        map.put("978-33333", new BookDto("자료구조와 알고리즘", "이순신"));

        // 2-2. 출력
        System.out.println("==============================");
        System.out.println("ISBN\t\tTITLE\t\tAUTHOR");
        System.out.println("==============================");

        for (String isbn : map.keySet()) {
            BookDto book = map.get(isbn);
            System.out.println(isbn + " | " + book.getTitle() + " | " + book.getAuthor());
            System.out.println("------------------------------");
        }

        // 2-3. 사용자 입력받아 값 출력
        Scanner sc = new Scanner(System.in);
        System.out.println("도서 정보를 제공중입니다");
        System.out.print("ISBN을 입력하세요> ");
        String input = sc.nextLine();

        BookDto result = map.get(input);

        if (result != null) {
            System.out.println("\n■" + input + " | " + result.getTitle() + " | " + result.getAuthor());
        } else {
            System.out.println("존재하지 않는 ISBN입니다.");
        }

        sc.close();
    }
}




*/