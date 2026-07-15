<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../inc/header.jsp" %>

<div class="container my-5">
  <h3>회원가입</h3> 
  <form action="${pageContext.request.contextPath}/users/join" method="post" onsubmit="return checkForm()">               	
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <div class="my-3">
      <label for="nickname" class="form-label">닉네임</label>
      <input type="text" class="form-control" id="nickname" name="nickname" />
      <button type="button" class="btn btn-sm btn-outline-secondary mt-1" onclick="checkNickname()">닉네임 중복검사</button>
    </div>
    <div class="my-3  alert  alert-warning  tnickname"> 
     닉네임 중복검사는 필수입니다.
    </div>  
               
    <div class="my-3">
      <label for="bpass" class="form-label">비밀번호</label>
      <input type="password" class="form-control" id="bpass" name="bpass" />
    </div>
    <div class="my-3">
      <label for="email" class="form-label">이메일</label>
      <input type="email"  class="form-control" id="email" name="email" />   
      <button type="button" class="btn btn-sm btn-outline-secondary mt-1" onclick="checkEmail()">이메일 중복검사</button>
    </div>  
    <div class="my-3  alert  alert-warning  target"> 
     아이디 중복검사는 필수입니다.
    </div>        
	
    <div class="my-3">
      <label for="mobile" class="form-label">휴대폰</label>
      <input type="text" class="form-control" id="mobile" name="mobile" />
    </div>
    <div class="text-end">
      <button type="reset" class="btn btn-outline-primary">취소</button>
      <button type="submit" class="btn btn-primary">가입하기</button>
    </div>
  </form>
</div> 

<script>
let nicknameChecked = false;
let emailChecked = false;
const ctx = "${pageContext.request.contextPath}";

// #2. 닉네임 중복검사
function checkNickname() {
  let nickname = document.getElementById("nickname").value.trim();
  let box = document.querySelector(".tnickname");

  if (nickname === "") { alert("닉네임을 입력하세요"); return; }

  fetch(ctx + "/users/checkNickname", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: "nickname=" + encodeURIComponent(nickname)
  })
  .then(res => res.text())
  .then(count => {
    if (parseInt(count) > 0) {
      box.textContent = "이미 사용중인 닉네임입니다.";
      box.classList.replace("alert-warning", "alert-danger");
      nicknameChecked = false;
    } else {
      box.textContent = "사용 가능한 닉네임입니다.";
      box.classList.replace("alert-warning", "alert-success");
      box.classList.remove("alert-danger");
      nicknameChecked = true;
    }
  });
}

// #1. 이메일 중복검사
function checkEmail() {
  let email = document.getElementById("email").value.trim();
  let box = document.querySelector(".target");

  if (email === "") { alert("이메일을 입력하세요"); return; }

  fetch(ctx + "/users/checkEmail", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: "email=" + encodeURIComponent(email)
  })
  .then(res => res.text())
  .then(count => {
    if (parseInt(count) > 0) {
      box.textContent = "이미 사용중인 이메일입니다.";
      box.classList.replace("alert-warning", "alert-danger");
      emailChecked = false;
    } else {
      box.textContent = "사용 가능한 이메일입니다.";
      box.classList.replace("alert-warning", "alert-success");
      box.classList.remove("alert-danger");
      emailChecked = true;
    }
  });
}

// 입력값 변경시 검사 초기화
document.getElementById("nickname").addEventListener("input", () => {
  nicknameChecked = false;
  let box = document.querySelector(".tnickname");
  box.textContent = "닉네임 중복검사는 필수입니다.";
  box.className = "my-3 alert alert-warning tnickname";
});
document.getElementById("email").addEventListener("input", () => {
  emailChecked = false;
  let box = document.querySelector(".target");
  box.textContent = "아이디 중복검사는 필수입니다.";
  box.className = "my-3 alert alert-warning target";
});

function checkForm(){
  let nickname = document.getElementById("nickname");
  let bpass = document.getElementById("bpass");
  let email = document.getElementById("email");
  let mobile = document.getElementById("mobile");

  if(nickname.value.trim()==""){ alert("닉네임을 입력하세요"); nickname.focus(); return false; }
  if(!nicknameChecked){ alert("닉네임 중복검사를 완료하세요"); nickname.focus(); return false; }
  if(bpass.value.trim()==""){ alert("비밀번호를 입력하세요"); bpass.focus(); return false; }
  if(email.value.trim()==""){ alert("이메일을 입력하세요"); email.focus(); return false; }
  if(!emailChecked){ alert("이메일 중복검사를 완료하세요"); email.focus(); return false; }
  if(mobile.value.trim()==""){ alert("휴대폰 번호를 입력하세요"); mobile.focus(); return false; }
  return true;
}
</script>

<%@include file="../inc/footer.jsp" %>