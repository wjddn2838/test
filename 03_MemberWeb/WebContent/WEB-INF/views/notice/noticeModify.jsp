<%@page import="notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Notice notice = (Notice)request.getAttribute("notice");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정</title>
</head>
<body>
	<h1>공지사항 수정</h1>
	<form action="/notice/modify" method="post">
		<input type="text" name="notice-subject" size="100" value="<%= notice.getNoticeSubject()%>"><br>
		<textarea rows="30" cols="100" name="notice-contents">
			<%= notice.getNoticeContents() %>
		</textarea><br>
		<input type="hidden" name="noticeNo" value="<%= notice.getNoticeNo() %>">
		<input type="submit" value="수정완료">
		<input type="reset" value="취소">
	</form>
</body>
</html>