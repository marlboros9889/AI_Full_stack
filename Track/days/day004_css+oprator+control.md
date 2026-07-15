
### ■day004
### ■1. 복습문제 
---
- 1. html/css/js 역할
html :  구조를 만드는 역할
css :   style 꾸미는 역할
js :   동작을 표현하는 역할

- 2. 태그

< > </ > : 브라우저에게 명령

div : 영역 <> 내용 </> 
h :   제목 h1 ~ h6 까지 1 이 제일 큰 제목
p :   문단
pre : 문단, 줄바꿈을 인식
img : 이미지, src= "경로", alt= "설명"
a :   랭크, herf="경로", titel="설명"

- 3. java

자바의 자료형 분류( 기본형(실제) / 참조형(주소) )

기본형 : 값
2-1 논리형 : 예) boolean - true/false (1byte)
2-2 정수형 : byte(1byte) - short(2byte) - ★int★(4byte) - long(8byte : 4L) // 10, 1, 2, 처럼 완성된 숫자를 뜻함
2-3 실수형 : float(4byte : 3.14f 플롯은 f를 입력) - ★doble★(8byte : 3.14 )  // 1.1, 3.14 처럼 소수점의 숫자를 뜻함

### ■2. TOD01 : CSS 기본
- 5. css(1)
 1. 배경 : background
 2. 글자 : color
 3. 글자크기 : font-size
 4. 정렬 : text-align
 5. 밑줄 : text-decoration
 6. 글꼴 : font-family
 7. 굵게 : font-weight
 8. 가로 : wiath
 9. 바깥쪽여백 : margin
 10. 안쪽 여백 : padding
 11. 선 : border
 12. 둥근모서리 : border-raidus
 13. 그림자효과 : box-shadow 


### ■3. TOD02 : java 자료형2/ 연산자/ 제어문?


### ■4. 복습문제
- 1. css(1)
1. 배경 :  background
2. 글자 :  color
3. 글자크기 :  font-size
4. 정렬 :  text-align
5. 밑줄 :  text-dacoration
6. 글꼴 :  font-family
7. 굵게 :  font-weight
8. 가로 :  wiath
9. 바깥쪽여백:   margin
10. 안쪽여백:  padding
11. 선 :  border
12. 둥근모서리 : border-daidus  
13. 그림자효과 :  box-shadow

- 2. java
  1. 자바의 자료형 분류( 기본형  / 참조형   )
  2. 기본형 : 값
    2-1 논리형 : 예) boolean  - true/false (1byte)   
    2-2 정수형 :  byte(1)-short(2)-int-(4)-long(8/4L)
    2-3 실수형 :  float(4:3.14f) - double-(8:3.14)
    2-4 문자형 : char(2) 유니코드, 문자 저장
  3. 자동형변환은 ( boolean  )  빼고  ( byte,short,int,long,float,double,char) 기본형


- 3. java [실습]
//		연습문제1)
//	패키지명 : com.company.java003_ex
//	클래스명 : CastingEx001
//	출력내용 :  Scanner이용해서 나누기 프로그램만들기 
//	   숫자입력1>  _입력받기  10   ( ☆자료형을 int )
//	   숫자입력2>  _입력받기  3     ( ☆자료형을 int )   
	    
      
      int num1, num2;
	    double result;
	    Scanner sc= new Scanner(System.in);
      //입력
		  System.out.println("숫자를 입력하세요");
	    num1= sc.nextInt();
	    System.out.println("숫자를 입력하세요");
	    num2= sc.nextInt();

// System.out.printf("숫자를 입력하세요"); num1 = scanner.nextInt();
// System.out.printf("숫자를 입력하세요"); num1 = scanner.nextInt();

	    //처리
	    //num1/num2
	    result=(double)num1/num2;
	    //출력
	    //  System.out.println(result);
	       System.out.printf("%d/%d= %.2f",num1 ,num2 ,result);