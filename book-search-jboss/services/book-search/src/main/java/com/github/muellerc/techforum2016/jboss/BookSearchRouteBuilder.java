package com.github.muellerc.techforum2016.jboss;

import com.github.muellerc.techforum2016.jboss.pricing.model.GetBookPriceRequest;
import com.github.muellerc.techforum2016.jboss.pricing.service.PricingService;
import org.apache.activemq.camel.component.ActiveMQEndpoint;
import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.cdi.Uri;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.component.jms.JmsEndpoint;

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@Startup
@ContextName("book-search-context")
public class BookSearchRouteBuilder extends RouteBuilder {

    @Inject
    @Uri("activemq:queue:IMAGE")
    private Endpoint imageService;

    @Inject
    private CxfEndpoint pricingService;

    @Inject
    private BookRepository bookRepository;

    @Inject
    private BookResultAggregationStrategy aggregationStrategy;

    @Override
    public void configure() throws Exception {
        from("direct:get-book").routeId("get-book")
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
}