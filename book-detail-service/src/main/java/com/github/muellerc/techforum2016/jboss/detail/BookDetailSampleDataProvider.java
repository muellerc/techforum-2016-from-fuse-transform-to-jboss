package com.github.muellerc.techforum2016.jboss.detail;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class BookDetailSampleDataProvider {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void init() {
        try {
            Book book = new Book();
            book.setIsbn("3000481109");
            book.setTitle("Abenteuer Seven Summits");
            book.setAuthor("Helga Hengge");

            em.persist(book);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
