package com.github.muellerc.techforum2016.fuse;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import javax.sql.DataSource;

public class BookRepository {

    private JdbcTemplate template;

    private String query = "SELECT * FROM BOOK WHERE ISBN = ?";

    public void init() {
        Assert.notNull(template, "dataSource/template must be specified!");
        Assert.notNull(query, "query must be specified!");
    }

    public Book get(String isbn) {
        return template.queryForObject(query, Book.class);
    }

    public void setDataSource(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }
}
