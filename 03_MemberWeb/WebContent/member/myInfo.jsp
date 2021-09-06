<%@page import="member.model.vo.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Student student = (Student)request.getAttribute("student");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세 정보</title>
</head>
<body>
	<form action="/member/modify" method="post">
	<fieldset>
		<legend>회원 상세 정보</legend>
		아이디 : <input type="text" name="user-id" value=" <%=student.getStudentId() %>" readonly><br>
		비밀번호 : <input type="password" name="user-pwd" value="<%=student.getStudentPwd() %>"> <br>
		이름: <input type="text" name="user-name" value="<%=student.getStudentName() %>" readonly> <br>
		성별 : <%=student.getStudentGender() %><br>
		이메일 : <input type="text" name="user-email" value="<%=student.getStudentEmail() %>"> <br>
		폰번호 : <input type="text" name="user-phone" value="<%=student.getStudentPhone() %>"> <br>
		주소 : <input type="text" name="user-address" value="<%=student.getStudentAddress() %>"> <br>
		취미 : <input type="text" name="user-hobby" value="<%=student.getStudentHobby() %>"> <br>
		<input type="submit" value="수정하기">
		<input type="reset" value="취소">
	</fieldset>
	</form><br>
	<a href="#">메인페이지로 이동</a>
	<a href="/member/remove">회원 탈퇴하기</a>
</body>
</html>