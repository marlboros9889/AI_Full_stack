package ClassEX;

//privated(Apple 클래스 내부)<<default(package)폴더<<protected 상속자식<<public 아무데서나
public class Apple {
	 
	 private String name; 
	 private int price;
	 //생성자 - 기본생성자, 필드생성자, toString, getters + setters
		@Override
		public String toString() {
			return "Apple [name=" + name + ", price=" + price + "]";
		}

		public Apple(String name, int price) {
			super();
			this.name = name;
			this.price = price;
		}
	 public Apple() {
		super();
		
	 }
 
}
