package com.example.servlet;

import com.example.dao.StudentDAO;
import com.example.model.Student;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/")
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO;

    @Override
    public void init() {
        studentDAO = new StudentDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            String studentId = request.getParameter("studentId");
            System.out.println("Deleting student with ID: " + studentId); // Debug log
            if (studentId != null) {
                studentDAO.deleteStudent(studentId);
            } else {
                System.out.println("studentId is null");
            }
            response.sendRedirect("students");
            return;
        }


        List<Student> students = studentDAO.getAllStudents();
        request.setAttribute("students", students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("studentList.jsp");
        dispatcher.forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        String name = request.getParameter("name");
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        Integer departmentId = (request.getParameter("departmentId") != null) ? Integer.parseInt(request.getParameter("departmentId")) : null;
        String enrollmentYear = request.getParameter("enrollmentYear");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        Student student = new Student(studentId, name, dateOfBirth, gender, departmentId, enrollmentYear, email, phone);

        if (studentDAO.getStudentById(studentId) != null) {
            studentDAO.updateStudent(student);
        } else {
            studentDAO.addStudent(student);
        }
        
        response.sendRedirect("students"); 
    }

}
