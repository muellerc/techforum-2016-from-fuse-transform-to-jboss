package com.github.muellerc.techforum2016.fuse;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet resultSet, int row) throws SQLException {
        Book book = new Book();
        book.setIsbn(resultSet.getString("ISBN"));
        book.setTitle(resultSet.getString("TITLE"));
        book.setAuthor(resultSet.getString("AUTHOR"));

        return book;
    }
}
