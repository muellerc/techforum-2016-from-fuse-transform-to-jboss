package com.github.muellerc.techforum.proxy;

import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.common.i18n.Exception;

public class ProxyRouteBuilder extends RouteBuilder {

    private long redeliveryDelay;
    private int maximumRedeliveries;

    public void init() {

    }

    @Override
    public void configure() throws Exception {
        from("cxf:bean:proxy").routeId("proxy")
            .onException(Exception.class)
                .redeliveryDelay(redeliveryDelay)
                .maximumRedeliveries(maximumRedeliveries)
            .end()
            .to("cxf:bean:backend");
    }

    public void setRedeliveryDelay(long redeliveryDelay) {
        this.redeliveryDelay = redeliveryDelay;
    }

    public void setMaximumRedeliveries(int maximumRedeliveries) {
        this.maximumRedeliveries = maximumRedeliveries;
    }
}
