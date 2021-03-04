package com.library.repository;

import com.library.model.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class BookRepository extends Repository<Book, Integer>{
    public BookRepository(){ super(Book.class);}
}
