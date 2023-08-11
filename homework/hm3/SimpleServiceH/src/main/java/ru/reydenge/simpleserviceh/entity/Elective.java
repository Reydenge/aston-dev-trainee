package ru.reydenge.simpleserviceh.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "electives")
public class Elective {
    @Id
    @Column(name = "elective_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "elective_name")
    private String name;

    @ManyToMany
    @JoinTable(name = "student_elective",
            joinColumns = @JoinColumn(name = "elective_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> studentList;

    public Elective() {

    }

    public Elective(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return name;
    }
}
