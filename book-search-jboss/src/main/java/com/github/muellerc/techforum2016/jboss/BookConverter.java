package com.github.muellerc.techforum2016.jboss;

import com.github.muellerc.techforum2016.jboss.pricing.model.GetBookPriceRequest;
import com.github.muellerc.techforum2016.jboss.pricing.model.GetBookPriceResponse;
import org.apache.camel.Converter;

@Converter
public final class BookConverter {

    private BookConverter() {
    }

    @Converter
    public static GetBookPriceRequest convertToGetBookPriceRequest(String payload) {
        GetBookPriceRequest request = new GetBookPriceRequest();
        request.setIsbn(payload);

        return request;
    }

    @Converter
    public static Book convertToBook(GetBookPriceResponse payload) {
        Book book = new Book();
        book.setIsbn(payload.getIsbn());
        book.setPrice(payload.getPrice());

        return book;
    }

    @Converter
    public static Book convertToBook(String payload) {
        Book book = new Book();
        book.setImage(payload);

        return book;
    }
}
