<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang ="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<head>
<meta charset="UTF-8">
<title> JSP  </title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
	<body>
		<%
			
			int userage = Integer.parseInt( request.getParameter("userage") );
			

		//1. utf - 8
		
		//2. userage 나이 넘어오는 데이터 확인 (paramerter)
		
		//3. 19세 마만이라면  jsp013_child.jsp 파일 넘기기 ( sendRedirect)
		//   19세 아니라면 	  jap013_adult.jsp (경로 안보이게 숨기기 - diespch 이영)
		if (userage < 19 ) { response.sendRedirect("jsp013_child.jsp?userage="+ userage); }
		else{ request.getRequestDispatcher("jsp013_adult.jsp").forward(request, response); }
		%>
	</body>
</html>				
				