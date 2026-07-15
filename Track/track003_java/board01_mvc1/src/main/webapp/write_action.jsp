<%@page import="java.net.InetAddress"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 
 
 <%
        request.setCharacterEncoding("UTF-8");
 
 		String bname  = request.getParameter("bname");
 		String bpass  = request.getParameter("bpass");
 		String btitle = request.getParameter("btitle");
 		String bcontent= request.getParameter("bcontent");
 		String bip= InetAddress.getLocalHost().getHostAddress();
         try{
    	//전체 리스트 출력
    	Connection conn=null; PreparedStatement pstmt=null; ResultSet rset= null;
    	String sql ="insert into mvcboard1(bname, bpass, btitle, bcontent, bip) values( ?,?,?,?,? )";
    	String url ="jdbc:mysql://localhost:3306/mbasic";
    	String user = "root", pass="1234";
    	//1. 드라이버로딩
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	
    	//2. jdbc 연동
    	conn=DriverManager.getConnection(url, user, pass);
   		
    	//3. sql 구문처리
    	pstmt= conn.prepareStatement(sql);
    	//select: executeQuery / insert,update,delete : executeUpdate
    	pstmt.setString(1,bname);
    	pstmt.setString(2,bpass);
    	pstmt.setString(3,btitle);
    	pstmt.setString(4,bcontent);
    	pstmt.setString(5,bip);
    	
    	
    	int result = pstmt.executeUpdate();//표
    	
    
    	if(result > 0 ){ out.println("<script> alert('글쓰기에  성공했습니다!'); location.href='list.jsp'; </script>"); 
    	}else{
    		out.println("<script> alert('관리자에게 문의하세요!'); location.href='list.jsp'; </script>");
    	}
    	
    	
    	//4. jdbc 끊기
    	
    	if(pstmt != null){ pstmt.close();	}
    	if(conn != null){ conn.close();	}
   		}catch(Exception e){e.printStackTrace();}
        
        
        
        %>