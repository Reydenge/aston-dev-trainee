package ru.reydenge.simpleserviceh;


import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.reydenge.simpleserviceh.entity.Elective;
import ru.reydenge.simpleserviceh.entity.Student;
import ru.reydenge.simpleserviceh.entity.University;
import ru.reydenge.simpleserviceh.util.HBUtils;

import java.util.List;

public class ApplicationMain {
    public static void main(String[] args) {
        Session session = HBUtils.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        Student student = session.find(Student.class, 1);
        System.out.println(student.getUniversity());
        System.out.println(student.getElectiveList());
        transaction.commit();
        session.close();
    }
}
