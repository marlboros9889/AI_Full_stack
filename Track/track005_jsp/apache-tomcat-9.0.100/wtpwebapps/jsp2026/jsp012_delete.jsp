<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// jsp012_delete.jsp

    // 1. UTF-8 설정
    request.setCharacterEncoding("UTF-8");

    // 2. 파라미터 받기 (ono만 필요!)
    int ono = Integer.parseInt(request.getParameter("ono"));

    try {
        Connection        conn  = null;
        PreparedStatement pstmt = null;

        // 3. DB 연결
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mbasic";
        conn  = DriverManager.getConnection(url, "root", "1234");

        // 4. DELETE 쿼리
        String sql = "delete from milk_order where ono=?";
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, ono);

        // 5. 실행
        int result = pstmt.executeUpdate();

        // 6. 결과 처리
        if (result > 0) {
            out.println("<script> alert('삭제에 성공했습니다!'); location.href='jsp012_milks.jsp'; </script>");
        } else {
            out.println("<script> alert('관리자에게 문의하세요!'); location.href='jsp012_milks.jsp'; </script>");
        }

        // 7. close() - 항상!
        if (pstmt != null) { pstmt.close(); }
        if (conn  != null) { conn.close();  }

    } catch (Exception e) { e.printStackTrace(); }
%>