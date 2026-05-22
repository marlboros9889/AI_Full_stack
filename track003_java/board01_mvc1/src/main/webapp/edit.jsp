<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="inc/header.jsp" %> 
<!-- header -->
<!-- header -->

<body>
   <div class="container card my-5">
      <h3 class="card-header"> 글 수정 </h3>
      <form action = "#" method="post" onsubmit="return checkForm()">
      	<div class="my-3">
      		<label for="bname" class="form-label">이름</label>  
      		<input type="text" class="form-control"	id="bname" name="bname"  readonly />     	
      	</div>
      	<div class="my-3">
      		<label for="bpass" class="form-label">비밀번호</label>  
      		<input type="password" class="form-control"	id="bpass" name="bpass"   />     	
      	</div>
      	<div class="my-3">
      		<label for="btitle" class="form-label">제목</label>  
      		<input type="text" class="form-control"	id="btitle" name="btitle"   />     	
      	</div>
      	<div class="my-3">
      		<label for="bcontent" class="form-label">내용</label>  
      		<input type="text" class="form-control"	id="bcontent" name="bcontent"   />     	
      	</div>
      	<div class="my-3 text-end">
      		<button type="reset" class="btn btn-primary" title="글 취소">취소</button> 
      		<a href=""         class="btn btn-primary"  title="목록보러가기">목록</a>
      		<button type="submit" class="btn btn-primary" title="글 수정">수정</button>
      			
      	</div>
		<%
		
		%>
      </form>
    	<script>
    	function checkForm(){
    		let bname = document.getElementById("bname");
    		let bpass = document.getElementById("bpass");
    		let btitle = document.getElementById("btitle");
    		let bcontent = document.getElementById("bpass");
    		
    		if(bname.value.trim() == "" ){alert("빈칸입니다. \n 확인해주세요! "); bname.forcus(); return false; }
    		if(bpass.value.trim() == "" ){alert("빈칸입니다. \n 확인해주세요! "); bpass.forcus(); return false; }
    		if(btitle.value.trim() == "" ){alert("빈칸입니다. \n 확인해주세요! "); btitle.forcus(); return false; }
    		if(bcontent.value.trim() == "" ){alert("빈칸입니다. \n 확인해주세요! "); bcontent.forcus(); return false; }
    		return truel
    	}
    	</script>
   </div>
</body>

<!-- foorter -->
<!-- foorter -->
<%@include file="inc/footer.jsp" %>