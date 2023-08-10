package ru.reydenge.simpleservlet.service;

import ru.reydenge.simpleservlet.entity.Elective;
import ru.reydenge.simpleservlet.entity.Student;

import java.util.List;

public interface StudentService {

    int createStudent(Student student);

    int updateStudent(Student student);

    int deleteStudentById(int studentId);

    Student getStudentById(int studentId);

    List<Student> getAllStudents();

    List<Elective> getElectivesForStudent(int studentId);

    void enrollStudentToElective(int studentId, int electiveId);
}
