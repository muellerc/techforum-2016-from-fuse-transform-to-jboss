package com.github.muellerc.techforum2016.fuse;

import com.github.muellerc.techforum2016.jboss.pricing.model.GetBookPriceRequest;
import com.github.muellerc.techforum2016.jboss.pricing.service.PricingService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class BookSearchRouteBuilder extends RouteBuilder {

    private String imageService;

    private String pricingService;

    private BookRepository bookRepository;

    private AggregationStrategy aggregationStrategy;

    @Override
    public void configure() throws Exception {
        from("cxfrs:bean:jaxrsServer").routeId("get-book")
            .multicast(aggregationStrategy).parallelProcessing()
                .to("direct:get-book-details", "direct:get-book-price", "direct:get-book-image")
            .end();

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