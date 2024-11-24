<%@ page import="com.example.model.Student" %>
<%@ page import="com.example.dao.StudentDAO" %>

<%
    String studentId = request.getParameter("studentId");
    StudentDAO studentDAO = new StudentDAO();
    Student student = studentDAO.getStudentById(studentId); 
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Student</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="my-4">Edit Student</h1>

        <form action="updateStudent" method="post">
            <input type="hidden" name="studentId" value="<%= student.getStudentId() %>"/>

            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" name="name" value="<%= student.getName() %>" required>
            </div>

            <div class="form-group">
                <label for="dateOfBirth">Date of Birth</label>
                <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" value="<%= student.getDateOfBirth() %>" required>
            </div>

            <div class="form-group">
                <label for="gender">Gender</label><br>
                <input type="radio" name="gender" value="true" <%= student.isGender() ? "checked" : "" %>/> Male
                <input type="radio" name="gender" value="false" <%= !student.isGender() ? "checked" : "" %>/> Female
            </div>

            <div class="form-group">
                <label for="departmentId">Department</label>
                <input type="text" class="form-control" id="departmentId" name="departmentId" value="<%= student.getDepartmentId() %>" />
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="<%= student.getEmail() %>" required>
            </div>

            <div class="form-group">
                <label for="phone">Phone</label>
                <input type="text" class="form-control" id="phone" name="phone" value="<%= student.getPhone() %>" required>
            </div>

            <button type="submit" class="btn btn-success">Save Changes</button>
            <a href="students" class="btn btn-secondary">Cancel</a>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
