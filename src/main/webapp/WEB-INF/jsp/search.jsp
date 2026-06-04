<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Kết quả tìm kiếm</title>
</head>
<body>
<h2>User có email @fpt.edu.vn và không phải admin</h2>
<c:if test="${empty resultList}">
    <p>Không có kết quả.</p>
</c:if>
<table border="1">
    <tr><th>Họ tên</th><th>Email</th></tr>
    <c:forEach var="u" items="${resultList}">
    <tr><td>${u.fullname}</td><td>${u.email}</td></tr>
    </c:forEach>
</table>
<a href="user">Quay lại danh sách</a>
</body>
</html>