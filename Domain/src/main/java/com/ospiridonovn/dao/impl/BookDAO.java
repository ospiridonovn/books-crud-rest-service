package com.ospiridonovn.dao.impl;

import com.ospiridonovn.dao.IBookDAO;
import com.ospiridonovn.domain.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAO implements IBookDAO {

    @Autowired
    SessionFactory sessionFactory;

    public void add(Book book) {
        getSession().save(book);
    }

    public void update(Book book) {
        getSession().update(book);
    }

    public void delete(Book book) {
        getSession().delete(book);
    }

    public Book get(Long id) {
        return (Book) getSession().get(Book.class, id);
    }

    public List list() {
        return getSession().createCriteria(Book.class).list();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
