package Class000;

public class UserInfoSon2 extends UserInfo2{
	public void show() {
		System.out.println("::홍길동 아버지    이름> "+ name); // public
		System.out.println("::홍길동 아버지 비밀번호> "+ safeCode);//protected
		System.out.println("::홍길동 아버지     집> "+ house); // 같은패키지(폴더) 아닐경우
		//System.out.println("::홍길동 아버지  아이큐> "+ iQ);
		System.out.println("::홍길동 아버지  아이큐> "+ getiQ()); //private: getters + setters 
		
	}
}