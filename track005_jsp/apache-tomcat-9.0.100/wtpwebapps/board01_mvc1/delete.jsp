<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="inc/header.jsp" %>
<!-- header -->
<!-- header -->

<body>
   <div class="container card my-5">
      <h3 class="card-header"> 글 삭제 </h3>
      <form action = "#" method="post">
      
      	<div class="my-3">
      		<label for="bpass" class="form-label">비밀번호</label>  
      		<input type="password" class="form-control"	id="bpass" name="bpass"   />     	
      	</div>
      
      	<div class="my-3 text-end">
      	<a href=""         class="btn btn-primary"  title="글 취소">글 수정</a>
      	<a href=""         class="btn btn-primary"  title="목록보러가기">목록</a>
      	<a href=""         class="btn btn-primary"  title="글삭제">글 삭제</a>
      	</div>
		<%
		
		%>
      </form>    
      	<script>
      	function checkForm(){
      		let bpass = document.getElementById("bpass");
      		if(bpass.value.trim() == "" ){alert("빈칸입니다. \n 확인해주세요! "); bpass.forcus(); return false; }
      		return truel
      	}
      	</script>
   </div>
</body>

<!-- foorter -->
<!-- foorter -->
<%@include file="inc/footer.jsp" %>