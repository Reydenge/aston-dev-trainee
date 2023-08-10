package ru.reydenge.simpleservlet.repository;

import ru.reydenge.simpleservlet.dbutils.DataBaseUtil;
import ru.reydenge.simpleservlet.entity.Elective;
import ru.reydenge.simpleservlet.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    private static final String CREATE = "INSERT INTO students(student_name, university_name) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE students SET student_name=?, university_name=? WHERE student_id=?";
    private static final String GET_BY_ID = "SELECT * FROM students WHERE student_id=?";
    private static final String GET_ALL = "SELECT * FROM students";
    private static final String DELETE = "DELETE FROM students WHERE student_id=?";

    public static int createStudent(Student student) {
        int status = 0;
        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE);) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getUniversityName());

            status = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Enable to create new student");
            e.printStackTrace();
        }
        return status;
    }

    public static int updateStudent(Student student) {
        int status = 0;
        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getUniversityName());
            preparedStatement.setInt(3, student.getId());

            status = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Enable to update selected student");
            e.printStackTrace();
        }
        return status;
    }

    public static Student getStudentById(int id) {
        Student student = new Student();
        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID);) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student.setId(resultSet.getInt(1));
                student.setName(resultSet.getString(2));
                student.setUniversityName(resultSet.getString(3));
            }
        } catch (SQLException e) {
            System.out.println("Enable to get this student");
            e.printStackTrace();
        }
        return student;
    }

    public static List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL);) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt(1));
                student.setName(resultSet.getString(2));
                student.setUniversityName(resultSet.getString(3));
                studentList.add(student);
            }
        } catch (SQLException e) {
            System.out.println("Enable to get all students");
            e.printStackTrace();
        }
        return studentList;
    }

    public static int deleteStudentById(int id) {
        int status = 0;
        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);) {
            preparedStatement.setInt(1, id);

            status = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Enable to delete this student");
            e.printStackTrace();
        }
        return status;
    }

    public static List<Elective> getElectivesForStudents(int studentId) {
        List<Elective> electives = new ArrayList<>();
        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT electives.elective_id, electives.elective_title FROM electives " +
                             "JOIN students_electives ON electives.elective_id = students_electives.elective_id " +
                             "WHERE students_electives.student_id = ?")) {

            preparedStatement.setInt(1, studentId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                electives.add(new Elective(title));
            }
        } catch (SQLException e) {
            System.out.println("Enable to get all electives for this student");
            e.printStackTrace();
        }
        return electives;
    }

    public static void enrollStudentInElective(int studentId, int electiveId) {
        try (Connection connection = DataBaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO students_electives (student_id, elective_id) VALUES (?, ?)")) {

            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, electiveId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Enable to enroll selected student to this elective");
            e.printStackTrace();
        }
    }
}
