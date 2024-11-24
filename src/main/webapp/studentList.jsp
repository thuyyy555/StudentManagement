<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Student" %>

<%
    List<Student> students = (List<Student>) request.getAttribute("students");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student List</title>
    
    <!-- Bootstrap CDN for styling -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="my-4">List of Students</h1>
    <div class="form-group">
        <label for="search">Search:</label>
        <input type="text" id="search" class="form-control" placeholder="Search by name or department">
    </div>
    <a href="addStudent.jsp" class="btn btn-success mb-3">Add New Student</a>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Date of Birth</th>
                <th>Gender</th>
                <th>Department</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (students != null) {
                    for (Student student : students) {
            %>
                <tr>
                    <td><%= student.getStudentId() %></td>
                    <td><%= student.getName() %></td>
                    <td><%= student.getDateOfBirth() %></td>
                    <td><%= student.isGender() ? "Male" : "Female" %></td>
                    <td><%= student.getDepartmentId() %></td>
                    <td><%= student.getEmail() %></td>
                    <td><%= student.getPhone() %></td>
                    <td>
                        <a href="editStudent.jsp?studentId=<%= student.getStudentId() %>" class="btn btn-primary btn-sm">Edit</a>
                        <a href="?action=delete&studentId=<%= student.getStudentId() %>" class="btn btn-danger btn-sm" onclick="return confirm('Xac nhan xoa sinh vien ?')">Delete</a>
                    </td>
                </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>
</div>

<script>
    document.getElementById('search').addEventListener('input', function() {
        const searchValue = this.value.toLowerCase();
        const rows = document.querySelectorAll('table tbody tr');

        rows.forEach(row => {
            const name = row.cells[1].textContent.toLowerCase();
            const department = row.cells[4].textContent.toLowerCase();

            if (name.includes(searchValue) || department.includes(searchValue)) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });
    });
</script>


    <!-- Bootstrap JS and dependencies (for potential modal or other interactions) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
