package com.ospiridonovn.service;

import com.ospiridonovn.domain.Book;

import java.util.List;

public interface IBookService {
    public void add(Book book);
    public void update(Book book);
    public void delete(Book book);
    public Book get(Long id);
    public List list();
}
