package com.ideas.workshop.engine;

import com.ideas.workshop.dbservice.entity.Hotel;
import com.ideas.workshop.dbservice.interfaces.HotelDatabaseService;
import com.ideas.workshop.engine.interfaces.OptimizationService;
import com.ideas.workshop.engine.model.HotelOptimizedRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Primary
public final class OptimizationServiceImpl implements OptimizationService {

    private final HotelDatabaseService hotelDatabaseService;

    @Autowired
    public OptimizationServiceImpl(HotelDatabaseService hotelDatabaseService) {
        this.hotelDatabaseService = hotelDatabaseService;
    }

    @Override
    public HotelOptimizedRate getOptimizedRate(Hotel hotel) {
        List<Hotel> competitorList = hotelDatabaseService.getCompetitors(hotel);
        return calculateOptimizedRate(hotel, competitorList);
    }

    private HotelOptimizedRate calculateOptimizedRate(Hotel hotel, List<Hotel> competitorList) {
        BigDecimal optimizedRate = competitorList.stream()
                .map(Hotel::rate)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(competitorList.size()));

        return new HotelOptimizedRate(hotel, optimizedRate.doubleValue());
    }
}
