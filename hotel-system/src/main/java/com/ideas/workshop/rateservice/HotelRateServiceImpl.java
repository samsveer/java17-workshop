package com.ideas.workshop.rateservice;

import com.ideas.workshop.engine.model.HotelOptimizedRate;
import com.ideas.workshop.engine.interfaces.OptimizationService;
import com.ideas.workshop.dbservice.interfaces.HotelDatabaseService;
import com.ideas.workshop.dbservice.entity.Hotel;
import com.ideas.workshop.rateservice.interfaces.HotelRateService;
import com.ideas.workshop.rateservice.model.HotelRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class HotelRateServiceImpl implements HotelRateService {

    private final HotelDatabaseService hotelDatabaseService;
    private final OptimizationService optimizationService;

    @Autowired
    public HotelRateServiceImpl(HotelDatabaseService hotelDatabaseService,
                                OptimizationService optimizationService) {
        this.hotelDatabaseService = hotelDatabaseService;
        this.optimizationService = optimizationService;
    }

    public HotelRate getHotelRate(String hotelName) {
        Hotel hotel = hotelDatabaseService.getHotel(hotelName);
        HotelOptimizedRate optimizedRate = optimizationService.getOptimizedRate(hotel);
        return prepareHotelRate(optimizedRate);
    }

    private HotelRate prepareHotelRate(HotelOptimizedRate optimizedRate) {
        return new HotelRate(optimizedRate.hotel().name(), optimizedRate.rate());
    }

}
