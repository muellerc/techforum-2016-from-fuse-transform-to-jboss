package com.github.muellerc.techforum2016.jboss.pricing;

import com.github.muellerc.techforum2016.jboss.pricing.model.GetBookPriceRequest;
import com.github.muellerc.techforum2016.jboss.pricing.model.GetBookPriceResponse;
import com.github.muellerc.techforum2016.jboss.pricing.service.PricingServicePort;
import com.github.muellerc.techforum2016.jboss.pricing.service.ServiceException;

import javax.jws.WebService;
import java.math.BigDecimal;

@WebService(targetNamespace = "http://www.company.de/techforum/service", name = "PricingServicePort")
public class PricingService implements PricingServicePort {

    @Override
    public GetBookPriceResponse getBookPrice(GetBookPriceRequest request) throws ServiceException {
        GetBookPriceResponse response = new GetBookPriceResponse();
        response.setIsbn(request.getIsbn());
        response.setPrice(new BigDecimal("24.00"));
        return response;
    }
}
