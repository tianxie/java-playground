package me.txie.springbootwithcamel;

import org.apache.camel.EndpointInject;
import org.apache.camel.FluentProducerTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring")
public class HelloRestController {

    @EndpointInject(value = "geocoder:address:Shanghai, China?type=NOMINATIM&serverUrl=https://nominatim.openstreetmap.org")
    private FluentProducerTemplate producer;

    @RequestMapping(method = RequestMethod.GET, value = "/hello",
            produces = "text/plain")
    public String hello() {
        String where = producer.request(String.class);
        return "Hello from Spring Boot and Camel. We are at: " + where;
    }
}
