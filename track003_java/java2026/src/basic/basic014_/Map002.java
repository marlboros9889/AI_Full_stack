package basic.basic014_;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Map002 {
	public static void main(String[] args) {
	
		Map<String, UserDto> maps = new HashMap<>();
		maps.put("first", new UserDto("first@mail.com"));
		maps.put("second", new UserDto("second@mail.com"));
		maps.put("third", new UserDto("third@mail.com"));
		maps.put("third", new UserDto("33@mail.com"));
		maps.put("third", new UserDto("33@mail.com"));
		
		System.out.println("몇명 ? " +  maps.size());  // 3
		System.out.println("몇명 ? " + maps);
		//third=UserDto  [no=5, email=33@mail.com]
		//first=UserDto  [no=1, email=first@mail.com]
		//second=UserDto [no=2, email=second@mail.com]
		
		//maps.entrySet() 이용해서 향상된 for로 출력
		for( Entry<String, UserDto> u : maps.entrySet()) {
			String key = u.getKey();
			UserDto Value = u.getValue();
			System.out.println("nickname : " + key + ", email :" + Value.getEmail());
			}
		//maps.entrySet() 이용해서 향상된 Itertor로 출력 (itretor(), hashNext(), next() )
		Iterator<Entry<String, UserDto>> iter = maps.entrySet().iterator(); //줄서기
		while(iter.hasNext()) { //처리대상 확인
			Entry<String, UserDto> m = iter.next();
			String key = m.getKey();
			UserDto Value = m.getValue();
			System.out.println("nicname:" + key +", email:" + Value.getEmail());
		}
		
		
	}

}

/*
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MapEx001 {
    public static void main(String[] args) {
        
        // 1. MAP 만들기
        Map<String, String> map = new HashMap<>();
        map.put("피구왕", "통키");
        map.put("제빵왕", "김탁구");
        map.put("요리왕", "비룡");
        
        // 2-1. 출력
        System.out.println("==============================");
        System.out.println("KING\tNAME");
        System.out.println("==============================");
        
        for (String king : map.keySet()) {
            System.out.println(king + "\t" + map.get(king));
            System.out.println("---------------------");
        }
        
        // 2-2. 사용자 입력받아 값 출력
        Scanner sc = new Scanner(System.in);
        System.out.println("KING의 정보를 제공중입니다");
        System.out.print("이름을 입력하세요> ");
        String input = sc.nextLine();
        
        String name = map.get(input);
        
        if (name != null) {
            System.out.println("\n■" + input + " : " + name);
        } else {
            System.out.println("존재하지 않는 KING입니다.");
        }
        
        sc.close();
    }
}
*/