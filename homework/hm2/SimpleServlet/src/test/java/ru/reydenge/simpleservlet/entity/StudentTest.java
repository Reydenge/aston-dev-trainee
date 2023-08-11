package ru.reydenge.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.reydenge.simpleservlet.entity.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {
    Student student;

    @BeforeEach
    public void createStudent() {
        student = new Student("Chloe", "IMT");
    }

    @Test
    void getStudentName() {
        assertEquals("Chloe", student.getName());
    }

    @Test
    void setStudentName() {
        student.setName("Max");
        assertEquals("Max", student.getName());
    }

    @Test
    void getStudentUniversity() {
        assertEquals("IMT", student.getUniversityName());
    }

    @Test
    void setStudentUniversityName() {
        student.setUniversityName("LETI");
        assertEquals("LETI", student.getUniversityName());
    }
}
