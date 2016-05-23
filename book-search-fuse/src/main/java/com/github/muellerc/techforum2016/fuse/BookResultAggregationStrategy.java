package com.github.muellerc.techforum2016.fuse;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class BookResultAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            return newExchange;
        }

        Book partialBookData1 = oldExchange.getIn().getBody(Book.class);
        Book partialBookData2 = newExchange.getIn().getBody(Book.class);
        Book book = merge(partialBookData1, partialBookData2);
        oldExchange.getIn().setBody(book);

        return oldExchange;
    }

    private Book merge(Book partialBookData1, Book partialBookData2) {
        Book book = new Book();
        book.setIsbn(partialBookData1.getIsbn());
        book.setTitle(partialBookData1.getTitle() != null ? partialBookData1.getTitle() : partialBookData2.getTitle());
        book.setAuthor(partialBookData1.getAuthor() != null ? partialBookData1.getAuthor() : partialBookData2.getAuthor());
        book.setPrice(partialBookData1.getPrice() != null ? partialBookData1.getPrice() : partialBookData2.getPrice());
        book.setImage(partialBookData1.getImage() != null ? partialBookData1.getImage() : partialBookData2.getImage());
        return book;
    }
}
