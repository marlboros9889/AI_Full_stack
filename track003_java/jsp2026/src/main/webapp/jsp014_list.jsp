<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="./jsp014_header.jsp" %>


    <section class="container my-5 ">
        <table class="table table-dark table-hover table-striped table=bordered">
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
              
            </tbody>
        </table>
        <div class="text-end p-3">
            <a href="#" title="글쓰기" class="btn btn-outline-danger">글쓰기</a>
        </div>
    </section>

<%@include file="./jsp014_footer.jsp" %>