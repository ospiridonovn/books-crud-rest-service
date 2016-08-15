package com.ospiridonovn.service.impl;

import com.ospiridonovn.dao.IBookDAO;
import com.ospiridonovn.domain.Book;
import com.ospiridonovn.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    private IBookDAO bookDAO;

    @Transactional
    public void add(Book book) {
        bookDAO.add(book);
    }

    @Transactional
    public void update(Book book) {
        bookDAO.update(book);
    }

    @Transactional
    public void delete(Book book) {
        bookDAO.delete(book);
    }

    @Transactional
    public Book get(Long id) {
        return bookDAO.get(id);
    }

    @Transactional
    public List list() {
        return bookDAO.list();
    }
}
