<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String studentId = (String)session.getAttribute("userId");
	String studentName = (String)session.getAttribute("userName");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버 로그인</title>
</head>
<body>
	<% if(studentId == null) { %>
	<h1>로그인 페이지</h1>
	<form action="/member/login" method="post">
		ID : <input type="text" name="user-id"> <br>
		PW : <input type="password" name="user-pwd"><br>
		<input type="submit" value="로그인">
		<input type="reset" value="취소">
		<a href="/member/enroll.html">회원가입</a>
	</form>
	<% } %>
	<% if(studentId != null && studentId != "") {%>
	<h1><%= studentName %> 님 환영합니다.</h1>
	<a href="/member/logout">로그아웃</a><br>
	<a href="/member/myinfo?studentId=<%=studentId%>">마이페이지</a><br>
	<a href="/member/listAll">전체 회원조회</a><br>
	<a href="#">파일 업로드</a><br>
	<a href="#">파일 목록조회</a><br>
	<a href="/notice/list">공지사항</a>
	<% } %>
</body>
</html>