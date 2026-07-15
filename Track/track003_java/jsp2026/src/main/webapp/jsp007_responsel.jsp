<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
<!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
  
	<h3 class="card-headder">MY SEARCH</h3>
	<pre class="alert alert-info">
	1.처리컨테이너 : jsp007_result.jsp
	2.처리방식    : get (주소표시창 줄 노출)
	3.보관용기	: para
	
	
	http://localhost:8080/jsp2026/jsp007_result.jsp?para=hello 
	</pre>

	<form action="jsp007_result.jsp" method="get"  onsubmit="return check()">
	 <div class="my-3">
	 	<label for="" class="form-lavel">SEARCH</label>
	 	<input type="text" class="form-control" id="para"  name="para"/> 
	 </div>
	 <div class="my-3 text-end">
	 	<button type="submit" class="btn btn-primary"> 전송 </button>
	 </div>
	 
	</form>
	<script>
	 function check(){
		let para = document.getElementById("para");
		if(para.value.trim() ==""){
			alert("검색어를 입력해주세요 !");
			para.focus();//커서
			return false;
		}
		return true;
	 }
	</script>
   
</body>
</html>

