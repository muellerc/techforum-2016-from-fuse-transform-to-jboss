package com.github.muellerc.techforum2016.fuse;

import com.github.muellerc.techforum2016.jboss.pricing.model.GetBookPriceRequest;
import com.github.muellerc.techforum2016.jboss.pricing.service.PricingService;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.math.BigDecimal;

public class BookSearchRouteBuilder extends RouteBuilder {

    private String imageService;

    private String pricingService;

    private BookRepository bookRepository;

    private AggregationStrategy aggregationStrategy;

    @Override
    public void configure() throws Exception {
        from("cxfrs:bean:jaxrsServer").routeId("get-book")
            .process(new Processor() {
                @Override
                public void process(Exchange exchange) throws Exception {
                    Book book = new Book();
                    book.setIsbn("3000481109");
                    book.setTitle("Abenteuer Seven Summits");
                    book.setAuthor("Helga Hengge");
                    book.setPrice(new BigDecimal("24.00"));
                    book.setImage("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAp1JREFUeNqEU21IU1EYfu7unW5Ty6aBszYs6MeUjGVYokHYyH5E1B9rZWFEFPQnAwmy6Hc/oqhfJsRKSSZGH1JIIX3MNCsqLTD9o1Oj6ebnnDfvvefezrnbdCHhCw/n433P8z7nPe/hBEEAtX0U7hc164uwuvVSXKwZLoOmaRDim+7m9vZa0WiEKSUFFpNpCWlmMyypqTDRuYn6t3k8vmQ2gRDCxs0t9fW45F52aBTROJLtZl7nEZad2m+KtoQCQ0FBARyOCGRZ/q92I1WgqqXlfdd95VsrK8/pChIEqqpCkiQsiCII0aBQZZoWl8lzFDwsFjMl0DBLY8Lj41hBwK4jSQrWOIphL6xYyhwJDWGo6wFSaH1Y3PTCAsITE1oyAa8flhWkbSiCLX8vun11eiGIpiJ/z2nYdx5HqLdVV7elrOzsuqysL3rmBIGiKPizKCHHWY4PLVeQbnXAdegqdhy+hu8dDTBnbqQJZJ1A7u+vz7RaiymWCZgCRSF6Edk8b9cx+B/W6WuVxPaZnyiqXoPpyUmVYvkKTIFClHigEieKjYuSvETUllaF4GAUM1NT6ooaJDKx+aDfC9fByxj90REb+9ppmIoAscH/6leg8MS9DJXPAM9xHCM443K57C6biMjcHDaVVCHw9RmCA2/RGC5C00AqXk/m4p20HZK4CM/J3Zk9n0ecMBhDQnJHcrTisyMfdQXOilrdMfxcwoHq/fg5R59TiQV3hYGKo6X2J/c7LyQIjOx9GXhOw/zoJ8wEevRGyp53o/lGMNYsBgPtEwLecwov7/jGDKa1twT6o3KpL4MdZgGsWZLtfPr7f1q58k1JNHy7YYaM+J+K3Y2PmAIbRavX66229hrGVvvL5uzsHDEUvUu+NT1my78CDAAMK1a8/QaZCgAAAABJRU5ErkJggg==");
                    exchange.getIn().setBody(book);
                }
            })
            .log(LoggingLevel.INFO, "executed")
            /*.multicast(aggregationStrategy).parallelProcessing()
                .to("direct:get-book-details", "direct:get-book-price", "direct:get-book-image")
            .end()*/;

        from("direct:get-book-details").routeId("get-book-details")
            .bean(bookRepository, "get");

        from("direct:get-book-price").routeId("get-book-price")
            .setHeader(CxfConstants.OPERATION_NAMESPACE, constant(PricingService.SERVICE.getNamespaceURI()))
            .setHeader(CxfConstants.OPERATION_NAME, constant("getBookPrice"))
            .convertBodyTo(GetBookPriceRequest.class)
            .to(pricingService)
            .convertBodyTo(Book.class);

        from("direct:get-book-image").routeId("get-book-image")
            .to(imageService)
            .convertBodyTo(Book.class);
    }

    public void setImageService(String imageService) {
        this.imageService = imageService;
    }

    public void setPricingService(String pricingService) {
        this.pricingService = pricingService;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void setAggregationStrategy(AggregationStrategy aggregationStrategy) {
        this.aggregationStrategy = aggregationStrategy;
    }
}