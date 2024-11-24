package com.example.servlet;

import com.example.dao.DepartmentDAO;
import com.example.model.Department;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class DepartmentServlet extends HttpServlet {
    private DepartmentDAO departmentDAO;

    @Override
    public void init() {
        departmentDAO = new DepartmentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departments = departmentDAO.getAllDepartments();
        request.setAttribute("departments", departments);
        RequestDispatcher dispatcher = request.getRequestDispatcher("departmentList.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Department department = new Department(0, name, description);
        departmentDAO.addDepartment(department);
        response.sendRedirect("departments");
    }
}
