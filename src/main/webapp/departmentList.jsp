<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách ngành học</title>
</head>
<body>
<h1>Danh sách ngành học</h1>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Tên ngành</th>
        <th>Mô tả</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="department" items="${departments}">
        <tr>
            <td>${department.id}</td>
            <td>${department.name}</td>
            <td>${department.description}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<h2>Thêm ngành học mới</h2>
<form action="departments" method="post">
    <label for="name">Tên ngành:</label>
    <input type="text" id="name" name="name" required>
    <br>
    <label for="description">Mô tả:</label>
    <textarea id="description" name="description" required></textarea>
    <br>
    <button type="submit">Thêm</button>
</form>
</body>
</html>
