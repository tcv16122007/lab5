<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa User</title></head>
<body>
<h2>Sửa thông tin User</h2>
<form action="user" method="post">
    <input type="hidden" name="action" value="update"/>
    <input type="hidden" name="id" value="${user.id}"/>
    Mật khẩu (SĐT): <input type="text" name="password" value="${user.password}" required/><br/>
    Họ tên: <input type="text" name="fullname" value="${user.fullname}" required/><br/>
    Email: <input type="email" name="email" value="${user.email}" required/><br/>
    Admin: <input type="checkbox" name="admin" ${user.admin ? 'checked' : ''}/><br/>
    <input type="submit" value="Cập nhật"/>
</form>
<a href="user">Quay lại</a>
</body>
</html>