<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách User</title>
</head>
<body>
<h2>Quản lý User</h2>
<a href="user?action=create">Thêm mới</a> |
<a href="user?action=searchFpt">Tìm user email @fpt.edu.vn (không admin)</a>
<table border="1">
    <tr><th>ID</th><th>Họ tên</th><th>Email</th><th>Admin</th><th>Thao tác</th></tr>
    <c:forEach var="u" items="${users}">
    <tr>
        <td>${u.id}</td><td>${u.fullname}</td><td>${u.email}</td>
        <td>${u.admin ? 'Có' : 'Không'}</td>
        <td><a href="user?action=edit&id=${u.id}">Sửa</a> | 
            <a href="user?action=delete&id=${u.id}" onclick="return confirm('Xóa?')">Xóa</a>
        </td>
    </tr>
    </c:forEach>
</table>
</body>
</html>