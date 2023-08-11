package ru.reydenge.simpleservlet.service;

import ru.reydenge.simpleservlet.entity.Elective;
import ru.reydenge.simpleservlet.entity.Student;
import ru.reydenge.simpleservlet.repository.StudentRepository;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private static StudentServiceImpl studentService;

    private StudentServiceImpl() {

    }

    public static StudentServiceImpl getStudentService() {
        if (studentService == null) {
            studentService = new StudentServiceImpl();
        }
        return studentService;
    }

    @Override
    public int createStudent(Student student) {
        return StudentRepository.createStudent(student);
    }

    @Override
    public int updateStudent(Student student) {
        return StudentRepository.updateStudent(student);
    }

    @Override
    public int deleteStudentById(int studentId) {
        return StudentRepository.deleteStudentById(studentId);
    }

    @Override
    public Student getStudentById(int studentId) {
        return StudentRepository.getStudentById(studentId);
    }

    @Override
    public List<Student> getAllStudents() {
        return StudentRepository.getAllStudents();
    }

    @Override
    public List<Elective> getElectivesForStudent(int studentId) {
        return StudentRepository.getElectivesForStudents(studentId);
    }

    @Override
    public void enrollStudentToElective(int studentId, int electiveId) {
        StudentRepository.enrollStudentInElective(studentId, electiveId);
    }
}
