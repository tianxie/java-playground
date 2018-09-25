package me.txie.springlog;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxiFareController {

    @GetMapping("/taxifare/get/")
    public RateCard getTaxiFare() {
        return new RateCard();
    }

    @PostMapping("/taxifare/calculate/")
    public String calculateTaxiFare(@RequestBody @Valid TaxiRide taxiRide) {
        // return the calculated fare
        return "";
    }
}