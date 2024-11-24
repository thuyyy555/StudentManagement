package com.example.dao;

import com.example.model.Student;
import com.example.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getString("student_id"),
                        resultSet.getString("name"),
                        resultSet.getDate("date_of_birth").toLocalDate(),
                        resultSet.getBoolean("gender"),
                        resultSet.getInt("department_id"),
                        resultSet.getString("enrollment_year"),  
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }


    public void addStudent(Student student) {
        String query = "INSERT INTO students (student_id, name, date_of_birth, gender, department_id, enrollment_year, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, student.getStudentId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setDate(3, Date.valueOf(student.getDateOfBirth()));
            preparedStatement.setBoolean(4, student.isGender());

            if (student.getDepartmentId() != null) {
                preparedStatement.setInt(5, student.getDepartmentId());
            } else {
                preparedStatement.setNull(5, Types.INTEGER);
            }

            preparedStatement.setString(6, student.getEnrollmentYear()); 
            preparedStatement.setString(7, student.getEmail());
            preparedStatement.setString(8, student.getPhone());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Student getStudentById(String studentId) {
        String query = "SELECT * FROM students WHERE student_id = ?";
        Student student = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                student = new Student(
                        resultSet.getString("student_id"),
                        resultSet.getString("name"),
                        resultSet.getDate("date_of_birth").toLocalDate(),
                        resultSet.getBoolean("gender"),
                        resultSet.getInt("department_id"),
                        resultSet.getString("enrollment_year"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }
    
    public void updateStudent(Student student) {
        String query = "UPDATE students SET name = ?, date_of_birth = ?, gender = ?, department_id = ?, email = ?, phone = ? WHERE student_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setDate(2, Date.valueOf(student.getDateOfBirth()));
            preparedStatement.setBoolean(3, student.isGender());
            preparedStatement.setInt(4, student.getDepartmentId() != null ? student.getDepartmentId() : 0);
            preparedStatement.setString(5, student.getEmail());
            preparedStatement.setString(6, student.getPhone());
            preparedStatement.setString(7, student.getStudentId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteStudent(String studentId) {
        String query = "DELETE FROM students WHERE student_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, studentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
