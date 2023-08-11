package ru.reydenge.simpleserviceh.dao;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.reydenge.simpleserviceh.entity.Elective;
import ru.reydenge.simpleserviceh.util.HBUtils;

import java.util.List;

public class ElectiveDAO {
    private static ElectiveDAO electiveDAO;

    private ElectiveDAO() {

    }

    public static ElectiveDAO getInstance() {
        if (electiveDAO == null) {
            electiveDAO = new ElectiveDAO();
        }
        return electiveDAO;
    }

    public void addElective(Elective elective) {
        Transaction transaction = null;
        try (Session session = HBUtils.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.persist(elective);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Transactional
    public void updateElective(Elective elective) {
        Transaction transaction = null;
        try (Session session = HBUtils.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.merge(elective);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Elective> findAllElectives() {
        Transaction transaction = null;
        List<Elective> electives = null;

        try (Session session = HBUtils.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            electives = session.createQuery("from Elective", Elective.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return electives;
    }

    public Elective findElectiveById(int id) {
        Transaction transaction = null;
        Elective elective = null;
        try (Session session = HBUtils.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            elective = session.find(Elective.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return elective;
    }

    @Transactional
    public void deleteElectiveById(int id) {
        Transaction transaction = null;
        try (Session session = HBUtils.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Elective elective = new Elective();
            session.load(id, elective);
            session.remove(elective);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
