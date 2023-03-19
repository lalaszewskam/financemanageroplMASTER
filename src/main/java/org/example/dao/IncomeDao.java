package org.example.dao;

import java.util.List;

import org.example.entity.Income;
import org.example.utils.DBConnection;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class IncomeDao {
    public void insert(Income income) {
        Session session = DBConnection.getSession();
        session.getTransaction().begin();
        session.persist(income);
        session.getTransaction().commit();
        session.close();
    }

    public List<Income> findAll() {
        Session session = DBConnection.getSession();
        List<Income> incomes = session.createQuery("SELECT i FROM Income i", Income.class).list();
        session.close();
        return incomes;
    }

    public void deleteById(int selectedId) {
        Session session = DBConnection.getSession();
        Query delete = session.createQuery("DELETE FROM Income i where i.id=:id")
                              .setParameter("id", selectedId);
        session.getTransaction().begin();
        delete.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
