package ru.reydenge.simpleserviceh.dao;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.reydenge.simpleserviceh.entity.University;
import ru.reydenge.simpleserviceh.util.HBUtils;

import java.util.List;

public class UniversityDAO {
    private static UniversityDAO universityDAO;

    private UniversityDAO() {

    }

    public static UniversityDAO getInstance() {
        if (universityDAO == null) {
            universityDAO = new UniversityDAO();
        }
        return universityDAO;
    }

    public void addUniversity(University university) {
        Transaction transaction = null;
        try (Session session = HBUtils.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.persist(university);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Transactional
    public void updateUniversity(University university) {
        Transaction transaction = null;
        try (Session session = HBUtils.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.merge(university);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<University> findAllUniversities() {
        Transaction transaction = null;
        List<University> universities = null;

        try (Session session = HBUtils.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            universities = session.createQuery("from University", University.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return universities;
    }

    public University findUniversityById(int id) {
        Transaction transaction = null;
        University university = null;
        try (Session session = HBUtils.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            university = session.find(University.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return university;
    }

    @Transactional
    public void deleteUniversityById(int id) {
        Transaction transaction = null;
        try (Session session = HBUtils.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            University university = new University();
            session.load(id, university);
            session.remove(university);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
