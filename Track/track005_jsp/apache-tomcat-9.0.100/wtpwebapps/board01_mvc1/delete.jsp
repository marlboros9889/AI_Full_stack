<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="inc/header.jsp" %>
<!-- header -->
<!-- header -->
<%
	
request.setCharacterEncoding("UTF-8");


%>
<body>
   <div class="container card my-5">
      <h3 class="card-header"> 글 삭제 </h3>
      <form action = "delete_action.jsp?bno=<%=request.getParameter("bno")%>" method="post" onsubmit="return checkForm()">
      
      	<div class="my-3">
      		<label for="bpass" class="form-label">비밀번호</label>  
      		<input type="password" class="form-control"	id="bpass" name="bpass"   />     	
      	</div>
      
      	<div class="my-3 text-end">
      	<button type="reset"         class="btn btn-primary"  title="글 취소">취소</button>
      	<a href="list.jsp"         class="btn btn-primary"  title="목록보러가기">목록</a>
      	<button type="submit"         class="btn btn-primary"  title="글삭제">글 삭제</button>
      	</div>
		
      </form>    
      	<script>
      	function checkForm(){
      		let bpass = document.getElementById("bpass");
      		if(bpass.value.trim() == "" ){alert("확인해주세요! "); bpass.forcus(); return false; }
      		return truel
      	}
      	</script>
   </div>
</body>

<!-- foorter -->
<!-- foorter -->
<%@include file="inc/footer.jsp" %>