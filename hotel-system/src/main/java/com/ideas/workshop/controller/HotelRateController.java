package com.ideas.workshop.controller;

import com.ideas.workshop.rateservice.interfaces.HotelRateService;
import com.ideas.workshop.rateservice.model.HotelRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelRateController {

    private final HotelRateService hotelRateService;

    @Autowired
    public HotelRateController(HotelRateService hotelRateService) {
        this.hotelRateService = hotelRateService;
    }

    @GetMapping("/rate/{hotelName}")
    public HotelRate getHotelRate(@PathVariable("hotelName") String hotelName) {
        return hotelRateService.getHotelRate(hotelName);
    }
}
