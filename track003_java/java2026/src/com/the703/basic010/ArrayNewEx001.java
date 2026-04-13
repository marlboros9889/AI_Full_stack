package com.the703.basic010;

import java.util.Arrays;

public class ArrayNewEx001 {
	public static void main(String[] args) {
	

//	    new 연산자 이용해서 배열만들기
//	    1. 배열명 : arr     
//	    2. 값 넣기 : 1.1  , 1.2  , 1.3  , 1.4  , 1.5    
//	       for+length 이용해보기
//	    3. for + length 로 출력
		
//		double [] arr = {1.1, 1.2, 1.3, 1.4, 1.5};
//		System.out.println();
//		for(int i=0; i<=5; i++) {System.out.println(arr[i]);}
//		System.out.println();
//		for(int i=0; i<arr.length; i++) {System.out.println(arr[i]);}
		
		
		double [] arr = new double [5];
		double data = 1.1;
//		System.out.println(arr);
//		System.out.println(Arrays.toString(arr));
		// arr[0] = 1.1;
//		arr[0] = data;/*1.1 넣고*/           data += 0.1; /*0.1증가*/
//		arr[1] = data;         				data += 0.1;
//		arr[2] = data;          			data += 0.1;
//		arr[3] = data;         				data += 0.1;
//		arr[4] = data;         				data += 0.1;
	    for (int i=0; i<arr.length; i++)  {    arr[i]= data; data+= 0.1;		  }
		for (int i=0; i<arr.length; i++)  { System.out.printf("%.1f \t", arr[i]); }

}
}