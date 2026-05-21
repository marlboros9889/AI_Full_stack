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
  			
  				 <thead>
					  <tr>
					    <th>NO</th>
					    <th>NAME</th>
					    <th>PRICE</th>
					  </tr>
					</thead>
					
					<tbody>
					  <tr>
					    <td>1</td>
					    <td>초코우유</td>
					    <td>1500</td>
					  </tr>
					  <tr>
					    <td>2</td>
					    <td>딸기우유</td>
					    <td>1700</td>
					  </tr>
					  <tr>
					    <td>3</td>
					    <td>바나나우유</td>
					    <td>1500</td> 
	
					  </tr>
					  <tr>
					    <td>4</td>
					    <td>커피우유</td>
					    <td>1800</td>
					   
					  </tr>
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
			  	  				pstmt = conn.prepareStatement("select * from milk order by mno desc");
			  	  				
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
			  			
			  			%>
					  
					  
				
					  
					  
  					</tbody>
  					
  		</table>
	</div>
	
<!--   주문현황표 -->	
<!--   주문현황표 -->	
<div class="container card my-5 bg-primary text-white">
		<h2 class="card-header">Menu Order</h2>
		<table class="table table-bordered table-striped table-hover">
  			<caption> Menu Order </caption>
  			<thead>
  				<tr>
  					<th scope="col">NO</th>
  					<th scope="col">NAME</th>
  					<th scope="col">NUM</th>
  					<th scope="col">주문날짜</th>
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
  	  				pstmt = conn.prepareStatement("select * from milk_order order by ono desc");
  	  				rset = pstmt.executeQuery();   //표   executeQuery(select)  
  	  				                               //executeUpdate(insert,update,delete)
  	  				while(rset.next()){
  	  					out.println("<tr><td>"
  	  							+rset.getInt("ono")+"</td><td>"
  	  							+rset.getString("oname")+"</td><td>"
  	  							+rset.getInt("onum")+"</td><td>"
  	  							+rset.getString("odate")+"</td></tr>"
  	  							);	
  	  				}
  	  				
  				//4. JDBC 끊기   필요한 코드?	conn.close()
  	  			if(rset != null){   rset.close();     }
  				if(pstmt != null){  pstmt.close();    }   
  	  			if(conn  != null){  conn.close();     }      
  	  			}catch(Exception e){e.printStackTrace();}
  			
  			%>
  			
  			</tbody>
  		</table>
</div>
<!--   주문삽입, 수정, 삭제 -->	
<!--   주문삽입, 수정, 삭제 -->		
<div class="container card my-5 bg-primary text-white">	
	<h2 class="card-header">Milk 주문, 수정, 삭제</h2>
		<div id="accordion">
			<div class="card ">
				<div class="card-header">
				<a class="btn" data-bs-toggle="collapse" href="#collapseOne">
				Milk 주문
				</a>
				</div>
				<div id="collapseOne" class="collapse show" data-bs-parent="#accordion">
					<div class="card-body">
						<form action ="jsp012_insert.jsp" method="post" onsubmit="return order()">
							<div class="my-3">
								<label for="oname" class="form-labal">주문할 우유 이름</label>
								<input type="text" class="form-control" id="oname" name="oname" />
							</div>
							<div class="my-3">
								<label for="onum" class="form-labal">주문할 우유 갯수</label>
								<input type="text" class="form-control" id="onum" name="onum" />
							</div>
							<div class="my-3">
								<button type="submit" class="btn btn-warning"> 주문하기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		
		<div class="card">
			<div class="card-header ">
				<a class="collapsed btn " data-bs-toggle="collapse" href="#collapseTwo">
				Manu 수정
				</a>
			</div>
			<div id="collapseOne" class="collapse show" data-bs-parent="#accordion">
				<div class="card-body">
					<form action ="jsp012_update.jsp" method="post" onsubmit="return order1()">
						<div class="my-3">
							<label for="ono1" class="form-labal">수정할 우유 번호</label>
							<input type="text" class="form-control" id="ono1" name="ono" />
						</div>
						<div class="my-3">
							<label for="oname1" class="form-labal">수정할 우유 이름</label>
							<input type="text" class="form-control" id="oname1" name="oname" />
						</div>
						<div class="my-3">
							<label for="onum1" class="form-labal">수정할 우유 갯수</label>
							<input type="text" class="form-control" id="onum1" name="onum" />
						</div>
						<div class="my-3">
							<button type="submit" class="btn btn-warning"> 수정하기 </button>
						</div>
					</form>
				</div>
			</div>
		
		  <div class="card ">
		    <div class="card-header ">
		      <a class="collapsed btn" data-bs-toggle="collapse" href="#collapseThree">
		        Menu 삭제
		      </a>
		    </div>
		    <div id="collapseThree" class="collapse" data-bs-parent="#accordion">
		       <div id="collapseOne" class="collapse show" data-bs-parent="#accordion">
		      <div class="card-body">
		      	<form action ="jsp012_delete.jsp" method="post" onsubmit="return order()">
		      		<div class="my-3">
							<label for="ono" class="form-labal">삭제할 우유 번호</label>
							<input type="text" class="form-control" id="ono" name="ono" />
						</div>
		      		
		      		<div class="my-3">
		      			<button type="submit" class="btn btn-warning"> 삭제하기</button>
		      		</div>
		      	</form>
		     
		    </div>
		  </div>

		</div>
	</div>
	</div>
</div>

	
</body>
</html>		