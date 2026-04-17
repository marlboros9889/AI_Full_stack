package StringEquals;

public class StringEquals {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String id  = "abc";
		String id2 = "abc";
		String id3 = "abc";
		String id4 = new String("abc"); // id , id2, id3  참조값 ( 주소값)
		
		System.out.println("1)" + (id==id2) );
		System.out.println("2)" + (id==id3) );
		
		System.out.println( System.identityHashCode(id) );;
		System.out.println( System.identityHashCode(id2) );;
		System.out.println( System.identityHashCode(id3) );;
		
		System.out.println("3)" + (id.equals (id3)) );
	}

}
