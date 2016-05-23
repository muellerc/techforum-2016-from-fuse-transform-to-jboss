package com.github.muellerc.techforum2016.jboss;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton()
public class BookRepository {

    @PersistenceContext
    private EntityManager em;

    public Book get(String isbn) {
        Book book = em.find(Book.class, isbn);

        return book;
    }
}
