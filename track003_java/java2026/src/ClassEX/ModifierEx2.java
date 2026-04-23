package ClassEX;


import Class000.Score;

public class ModifierEx2 {
	public static void main(String[] args) {
	      Score iron = new Score();     
	      Score hulk = new Score("hulk" , 20,50,30 );    
	      
	      // Score.info()위에 메서드작성해주세요!  ##
	      // setter를 이용해주세요!
	      iron.setName("iron"); iron.setKor(100); iron.setEng(100); iron.setMath(100);
	      
	      Score.info();     // 클래스메서드
	      iron.show();          
	      hulk.show();   
	   }
}


/*

ㅁ출력된화면
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
이름   국어   영어   수학   총점   평균   합격여부   장학생   랭킹
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
iron   100   100   100   300   100.00   합격   장학생   **********
hulk   20   50   30   100   33.33   불합격      ***


ㅁ주어진조건
public class Score{    // com.the703.basic010 패키지에 설정해주세요
   private String name;
   private int kor, eng, math , total;
   private double aver;
   private String p  , s  , rank;
} // java011_ex에 설정해주세요!

public class ModifierEx2{    // com.the703.basic010   패키지에 설정해주세요.
   public static void main(String[] args) {
      Score iron = new Score();     
      Score hulk = new Score("hulk" , 20,50,30);    
      
      // Score.info()위에 메서드작성해주세요!  ##
      // setter를 이용해주세요!
      iron.setName("iron"); iron.setKor(100); iron.setEng(100); iron.setMath(100);
      
      Score.info();     // 클래스메서드
      iron.show();          
      hulk.show();   
   }

}

Score.info() 사용시화면
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
이름   국어   영어   수학   총점   평균   합격여부   장학생   랭킹
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::


*/