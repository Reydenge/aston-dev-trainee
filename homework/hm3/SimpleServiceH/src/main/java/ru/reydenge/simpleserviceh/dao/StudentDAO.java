package ru.reydenge.simpleserviceh.dao;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.reydenge.simpleserviceh.entity.Student;
import ru.reydenge.simpleserviceh.util.HBUtils;

import java.util.List;

public class StudentDAO {
    private static StudentDAO studentDAO;

    private StudentDAO() {

    }

    public static StudentDAO getInstance() {
        if (studentDAO == null) {
            studentDAO = new StudentDAO();
        }
        return studentDAO;
    }

    public void addStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HBUtils.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Transactional
    public void updateStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HBUtils.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.merge(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Student> findAllStudents() {
        Transaction transaction = null;
        List<Student> students = null;

        try (Session session = HBUtils.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            students = session.createQuery("from Student", Student.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return students;
    }

    public Student findStudentById(int id) {
        Transaction transaction = null;
        Student student = null;
        try (Session session = HBUtils.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            student = session.find(Student.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return student;
    }

    @Transactional
    public void deleteStudentById(int id) {
        Transaction transaction = null;
        try (Session session = HBUtils.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Student student = new Student();
            session.load(id, student);
            session.remove(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
