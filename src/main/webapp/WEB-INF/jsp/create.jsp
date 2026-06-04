<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm User</title></head>
<body>
<h2>Thêm User mới</h2>
<form action="user" method="post">
    <input type="hidden" name="action" value="create"/>
    ID: <input type="text" name="id" required/><br/>
    Mật khẩu (SĐT): <input type="text" name="password" required/><br/>
    Họ tên: <input type="text" name="fullname" required/><br/>
    Email: <input type="email" name="email" required/><br/>
    Admin: <input type="checkbox" name="admin"/><br/>
    <input type="submit" value="Thêm"/>
</form>
<a href="user">Quay lại</a>
</body>
</html>