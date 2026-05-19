<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.* "%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<!DOCTYPE html>
<html lang = "ko">
<head>
<meta charset="UTF-8">
<title>MILK ORDER Project</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<!--   header -->
<!--   header -->
<!-- bg-primary(파랑) bg-danger(빨강) wrning(노랑) -->
<div class=" p-5 bg-primary text-white ">
  <h1>Milk Order Project</h1>
  	<p>MVC1 - PreparedStatement Ex</p>
  		
</div>

<!--   메뉴판테이블 -->	
<!--   메뉴판테이블 -->
	<div class="container card my-5 bg-primary text-white">
		<h2 class="card-header">Milk Menu</h2>
		<table class="table table-bordered table-striped table-hover">
  			<caption>우유메뉴</caption>
  			<thead>
  				<tr>
  					<th scope="col">NO</th>
  					<th scope="col">MENU</th>
  					<th scope="col">PRICE</th>
  				</tr>
  			</thead>
  			<tbody>
  			<%
  			try{
  				
  				//1. 드라이버로딩  필요한 코드?	Class.forName - com.mysql.cj.jdbc.Driver
  					Class.forName("com.mysql.cj.jdbc.Driver");
  					Connection conn=null; PreparedStatement pstmt = null; ResultSet rset = null;
  				//2. JDBC 연동   필요한 코드?	DriverManager.getConnection() - jdbc:mysql://localhost:3306/mbasic
  					 conn = DriverManager.getConnection(
  							 "jdbc:mysql://localhost:3306/mbasic", 
  							 "root", 
  							 "1234");
  	  			//3. PreparedStatement pstmt 이용해서 milk 테이블의 데이터 가져오기 - 가격낮은순으로
  	  				pstmt = conn.prepareStatement("select * from milk order by mprice asc");
  	  				
  	  				rset = pstmt.executeQuery();
  	  				while(rset.next()){
  	  					out.println("<tr><td>"
  	  							+rset.getInt("mno")+"</td><td>"
  	  							+rset.getString("mname")+"</td><td>"
  	  							+rset.getInt("mprice")+"</td></tr>"
  	  							);	
  	  				}
  	  				
  				//4. JDBC 끊기   필요한 코드?	conn.close()
  	  			if(pstmt != null){  pstmt.close();    }   
  	  			if(conn  != null){  conn.close();     }      
  	  			}catch(Exception e){e.printStackTrace();}
  			/*
  			
  			
  			alter table milk modify mnum int null;
  			alter table milk modify mtotal int null;
  			
  			
  			*/
  			%>
  			</tbody>
  		</table>
	</div>
	
<!--   주문현황표 -->	
<!--   주문현황표 -->	
<div class="container card my-5 bg-primary text-white">
		<h2 class="card-header">Menu Order</h2>
		<table class="table table-bordered table-striped table-hover">
  			<caption></caption>
  			<thead>
  				<tr>
  					<th scope="col">NO</th>
  					<th scope="col">NAME</th>
  					<th scope="col">NUM</th>
  					<th scope="col">주문날짜</th>
  				</tr>
  			</thead>
  		</table>
</div>
<!--   주문삽입, 수정, 삭제 -->	
<!--   주문삽입, 수정, 삭제 -->		
<div class="container card my-5 bg-primary text-white">	
	<h2 class="card-header">Milk 주문하러 가기</h2>

</div>
</body>
</html>