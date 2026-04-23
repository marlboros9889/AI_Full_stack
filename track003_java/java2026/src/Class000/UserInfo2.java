package Class000;

/*  ★  */
public class UserInfo2 {

		public  	String name;      	 //아무데서나 다 접근 가능	
		protected 	String safeCode;     //자식에서 사용가능 (extends  시)
					String house;		 //패키지(폴더_
		private int iQ;					 //클래스 내부- The value of the field UserInfo.iQ is not used
		
		public int getiQ() {return iQ;}
		public void setiQ(int iQ) {this.iQ = iQ;}
		
	}
//public  > Protected > default (package)아무것도 안붙은 지정접근자 > private
