<%@page import="java.net.InetAddress"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
    // 1. 파라미터 받기
    request.setCharacterEncoding("UTF-8");
    String oname = request.getParameter("oname");
    int     onum = Integer.parseInt(request.getParameter("onum"));
	out.println(oname + " / " + onum);
    // 2. DB 연결
   
	    try {
	    	
	    Connection conn  = null; PreparedStatement pstmt = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mbasic";
        String sql = "delete from milk where oname=? and onum=?";
        conn  = DriverManager.getConnection(url, "root", "1234");
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, oname);
        pstmt.setInt(2, onum);
        pstmt.setString(3, InetAddress.getLocalHost().getHostAddress()); 
        int result = pstmt.executeUpdate();

        if (result > 0) {
            out.println("<script>alert('삭제 완료!'); location.href='jsp012_milks.jsp';</script>");
        } else {
            out.println("<script>alert('삭제 실패! 데이터를 확인하세요.'); location.href='jsp012_milks.jsp';</script>");
        }

    	if(pstmt != null){ pstmt.close(); }
    	if(conn != null) { conn.close();  }
    	
    	
    	}catch(Exception e){e.printStackTrace();}
%>