<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
h1 {
	color: blue;
	font-family: verdana;
	font-size: 100%;
}

error {
	color: red;
	font-family: verdana;
	font-size: 100%;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Apple Twitter Search</title>
</head>
<body>
	<form action="/tweets">

		<table>
			<tr>
				<td>
					<h1>Search Keyword</h1>
				</td>
				<td><input type="text" name="tag" required pattern=".*\S.*"
					title="Field cannot be blank"></input></td>
			</tr>
			<tr>
				<td><h1>Limit</h1></td>
				<td><input type="number" name="num" required min="1" max="200"></input></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Search" /></td>
			</tr>
		</table>
	</form>
	<c:if test="${not empty errorResponse}">
		<erorr>${errorResponse}</erorr>
	</c:if>
	<c:if test="${not empty tweetResponse}">
		<table border="1">
			<tr>
				<td>Twitter ID</td>
				<td>Tweet By</td>
				<td>Tweet</td>
				<td>Created At</td>
				<td>Retweet Count</td>
			</tr>
			<c:forEach items="${tweetResponse}" var="tweet">
				<tr>
					<td>${tweet.id}</td>
					<td>${tweet.fromUser}</td>
					<td>${tweet.text}</td>
					<td>${tweet.createdAt}</td>
					<td align='center'>${tweet.retweetCount}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>