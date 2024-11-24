package com.example.model;

public class Grade {
    private int id;
    private String studentId;     
    private String courseName;
    private double grade;         
    private String semester;

    public Grade() {
    }

    public Grade(int id, String studentId, String courseName, double grade, String semester) {
        this.id = id;
        this.studentId = studentId;
        this.courseName = courseName;
        this.grade = grade;
        this.semester = semester;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
