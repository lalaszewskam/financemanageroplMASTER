package org.example.dao;

import java.util.List;

import org.example.entity.Category;
import org.example.utils.DBConnection;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CategoryDao {
    public Category findByName(String categoryName) {
        Session session = DBConnection.getSession();
        Category category = session.createQuery("SELECT c FROM Category c where c.name=:categoryName", Category.class)
                                   .setParameter("categoryName", categoryName).uniqueResult();
        session.close();
        return category;
    }

    public void insert(Category category) {
        Session session = DBConnection.getSession();
        session.getTransaction().begin();
        session.persist(category);
        session.getTransaction().commit();
        session.close();
    }

    public List<String> findCategoryNames() {
        Session session = DBConnection.getSession();
        List<String> list = session.createQuery("SELECT c.name from Category c", String.class).list();
        session.close();
        return list;
    }

    public void deleteByName(String categoryName) {
        Session session = DBConnection.getSession();
        Query delete = session.createQuery("DELETE FROM Category c where c.name=:categoryName")
                              .setParameter("categoryName", categoryName);
        session.getTransaction().begin();
        delete.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
