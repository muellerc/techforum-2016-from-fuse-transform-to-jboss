package com.github.muellerc.techforum2016.jboss;

import com.github.muellerc.techforum2016.jboss.pricing.service.PricingService;
import com.github.muellerc.techforum2016.jboss.pricing.service.PricingServicePort;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.camel.component.ActiveMQEndpoint;
import org.apache.camel.CamelContext;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.component.jms.JmsEndpoint;
import org.apache.camel.component.jms.JmsMessageType;
import org.apache.camel.component.properties.DefaultPropertiesParser;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.component.properties.PropertiesParser;
import org.apache.deltaspike.core.api.config.ConfigProperty;
import org.apache.deltaspike.core.api.config.ConfigResolver;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactoryDefinition;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BeanFactory {

    @Inject
    CamelContext context;

    @Inject
    @ConfigProperty(name = "pricing-service.url")
    String pricingServiceUrl;

    @Resource(lookup = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Produces
    public CxfEndpoint createCxfEndpoint() {
        CxfEndpoint endpoint = new CxfEndpoint();
        endpoint.setCamelContext(context);
        endpoint.setAddress(pricingServiceUrl);
        endpoint.setServiceClass(PricingServicePort.class);
        endpoint.setEndpointName(PricingService.SERVICE);
        endpoint.setServiceName(PricingService.PricingService);
        endpoint.setWsdlURL("techforum-sample.wsdl");

        Map<String,Object> properties = new HashMap<>();
        properties.put("schema-validation-enabled", Boolean.TRUE);
        endpoint.setProperties(properties);

        return endpoint;
    }

    @Produces
    @Named("activemq")
    @ApplicationScoped
    ActiveMQComponent createActiveComponent() {
        ActiveMQComponent component = new ActiveMQComponent();
        component.setConnectionFactory(connectionFactory);
        return component;
    }

    @Produces
    @ApplicationScoped
    @Named("properties")
    PropertiesComponent properties(PropertiesParser parser) {
        PropertiesComponent component = new PropertiesComponent();
        component.setPropertiesParser(parser);
        return component;
    }

    static class DeltaSpikeParser extends DefaultPropertiesParser {

        @Override
        public String parseProperty(String key, String value, Properties properties) {
            return ConfigResolver.getPropertyValue(key);
        }
    }
}
