<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="inc/header.jsp" %>
<!-- header -->
<!-- header -->

    <section class="container my-5  ">
        <table class="table table-dark table-hover table-striped table-bordered">
          <caption> BOARD 목록</caption>  
            <thead>
                <tr>
                    <th scope="col">No</th>
                    <th scope="col">TITLE</th>
                    <th scope="col">WRTIER</th>
                    <th scope="col">DATA</th>
                    <th scope="col">HIT</th>
                </tr>
            </thead>
        
        <tbody>
        <%
         try{
    	//전체 리스트 출력
    	Connection conn=null; PreparedStatement pstmt=null; ResultSet rset= null;
    	String sql ="select b.*,(select count(*) from mvcboard1)`cnt`" + "from mvcboard1 b order by bno desc";
    	String url ="jdbc:mysql://localhost:3306/mbasic";
    	String user = "root", pass="1234";
        //String sql1="select count(*) from   mvcboard1 order by bno desc";            
        //String sql2="select *        from   mvcboard1 order by bno desc";
        
    	
    	request.setCharacterEncoding("UTF-8");
    	
    	//1. 드라이버로딩
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	
    	//2. jdbc 연동
    	conn=DriverManager.getConnection(url, user, pass);
   		
    	//3. sql 구문처리
    	pstmt= conn.prepareStatement(sql);
        pstmt = conn.prepareStatement(sql , 
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY);
        // select:executeQuery / insert, update, delete:executeUpdate    
    	rset = pstmt.executeQuery();//표
    	//1) 먼저 전체글 갯수 출력
		int cnt =1;
    	//줄
		if(rset.next()){
			cnt = rset.getInt("cnt");//칸
			rset.beforeFirst();          //다시 처음부터 표부터 처리
			} 
		//2) 
		  while(rset.next()) { //줄
              out.println("<tr><td>"+  cnt--
               +"</td><td><a href='detail.jsp?bno="+rset.getInt("bno")+"'>"
               + rset.getString("btitle") 
               +"</a></td><td>"
               + rset.getString("bname") +"</td><td>"
               + rset.getString("bdate") +"</td><td>"
               + rset.getInt("bhit") +"</td></tr>");
           } 
    	
    	
    	
    	//4. jdbc 끊기
    	if(rset != null){ rset.close();	}
    	if(pstmt != null){ pstmt.close();	}
    	if(conn != null){ conn.close();	}
   		}catch(Exception e){e.printStackTrace();}
        
        
        
        %>
        </tbody>
        </table>
        <div class="text-end p-3">
            <a href="write.jsp" title="글쓰기 폼" class="btn btn-outline-danger">글쓰기</a>
        </div>
    </section>
  
<!-- foorter -->
<!-- foorter -->
<%@include file="inc/footer.jsp" %>