package com.example.dao;

import com.example.model.Grade;
import com.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeDAO {
    public List<Grade> getGradesByStudentId(String studentId) {
        List<Grade> grades = new ArrayList<>();
        String query = "SELECT * FROM grades WHERE student_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, studentId);  // Use String for studentId
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                grades.add(new Grade(
                        resultSet.getInt("id"),
                        resultSet.getString("student_id"),  // Changed to String for consistency
                        resultSet.getString("course_name"),
                        resultSet.getDouble("grade"),
                        resultSet.getString("semester")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grades;
    }

    public void addGrade(Grade grade) {
        String query = "INSERT INTO grades (student_id, course_name, grade, semester) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, grade.getStudentId());  // Use String for studentId
            preparedStatement.setString(2, grade.getCourseName());
            preparedStatement.setDouble(3, grade.getGrade());
            preparedStatement.setString(4, grade.getSemester());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
