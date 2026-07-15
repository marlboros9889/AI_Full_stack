### 2. TOD01. css기본
- 6. css(2) 내부적용 / id vs class

1. block  vs  inline
Q) a 태그에 margin 적용x, text-align 적용x,  width 적용x
The dasplay: inlinn propety prevents width from having an effect.
Try setting display to something oher than inline.

display:block   박스 (갈치냄비)              - width o ,  줄바꿈 o
         예) div, p, pre 
display:inline  박스 안에 내용물(갈치,무,국물) - width x(△) ,줄바꿈 x 
         예) img, a, span, strong


- 7. css(2) 내부적용 / id vs class
css 적용방법
1) 인라인 스타일 - 태그안에 직접 적용
```thml
<p style="color:red">color</p>
```


2) 내부 스타일 시트 - head 안에 style을 사용해 작성
```html
<style> p{ color:red; } </style>
```





---



### ■4. 복습문제
> 정리문제 (1)
1. 배경을 파란색으로 설정하는 속성은?  
background-color: blue
2. 글자색상을 빨간색으로 지정하는 속성은?  
font-color: red   
3. 글자 크기를 20px로 지정하는 속성은?  
font-size: 20px
4. 글자를 가운데 정렬하는 속성은?  
text-align: center;
5. 글자에 밑줄을 추가하는 속성은?  
text-decoration:
6. 글꼴을 Arial로 지정하는 속성은?  

7. 글자를 굵게 표시하는 속성은?  

8. 요소의 가로 길이를 300px로 지정하는 속성은?  
widt
9. 요소의 바깥쪽 여백을 10px로 지정하는 속성은?  
margin
10. 요소의 안쪽 여백을 15px로 지정하는 속성은?  
padding
11. 요소에 1px 실선 테두리를 추가하는 속성은?  

12. 모서리를 둥글게 10px로 만드는 속성은?  
border-radius:10px;
13. 그림자 효과를 추가하는 속성은?  
box-shadow:
14. 천천히 움직이는 장면전환효과를 주는 속성은? 
div:hover


> 정리문제 (2)
15.  가로 사이즈 지정가능한것은 block   /    inline

16.  a태그에 margin-top 줄수   o   /   x

17.  css 적용방법 3가지 ( 인라인  / 내부   /  외부   )

18.  css 적용 내부적용방법은 ( head  ) 태그안에 (  style  ) 태그 적용해서 사용

> 정리문제 (3)
1.  연산자의 우선순위를 적으시오.
먼저 ( ) 값 ( ++,--,+,-,*,/,% ) → 비교 ( <,>,!=,== ) → 조건( &&,|| ) 대입 ( = )

2.  다음오류 해결
short sh1 = 1 , sh2=2;
short result = sh1 + sh2;


3. 필수조건
q1-1 int형 변수 x가 3보다 크고 10보다 작을때 true인 조건식 
q1-2 char형 변수 ch가 'a' 또는 'A'일때   true인 조건식    
q1-3 char형 변수 ch가 숫자('0'~'9')일때   true인 조건식     
q1-4 char형 변수 ch가 영문자(대문자 또는 소문자) 일때   true인 조건식
