복습문제  [#1 ~ #5로 들어가져있는 부분 채우세요 !]
Q1) 다음 form 태그를 완성하시오.
1. 처리해줄경로              :  result.jsp
2. 주소표시창줄 노출여부 o
3. 쿼리스트링으로 해서 넘길때 이름  userage

     <form action="[ result.jsp ]" method="[ get ]" onsubmit="return check()">
      <div class="my-2">
         <label for="userage">나이입력</label> 
         <input type="text" class="form-control" placeholder="" 
                id="userage" name="[ userage ]" />
         <button type="submit" class="btn btn-success mt-3 d-block">성인여부</button>
      </div>
    </form>

Q2. 다음과 같이 처리
1. 위의폼      jsp013_implicit.jsp (나이입력받는폼)
2. 처리         result.jsp  처리
                             19세 미만이라면 -   c.jsp    (경로가 바뀜) 
                             19세 이상이라면 -   보여주는 주소표시창줄은   result.jsp   /  보이는화면은   a.jsp      
<%
//1.  나이 넘어오는데이터 확인  (getParameter)  [ String age = request.getParameter("userage"); ]
//2.  만약 19세 미만이라면 c.jsp 파일 넘기기 (sendRedirect) [ response.sendRedirect("c.jsp"); ]
//    아니라면           a.jsp (경로 안보이게 숨기기 - dispatch 이용)
	String age = request.getParameter("userage");
	int userAge = Integer.parseInt(age);
	
	if(userAge < 19) {
		response.sendRedirect("c.jsp");
	} else {
		RequestDispatcher rd = request.getRequestDispatcher("a.jsp");
		rd.forward(request, response);
	}
%>
