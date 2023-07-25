package com.ideas.workshop.engine.interfaces;

import com.ideas.workshop.engine.model.HotelOptimizedRate;
import com.ideas.workshop.dbservice.entity.Hotel;

public interface OptimizationService {

    HotelOptimizedRate getOptimizedRate(Hotel hotel);

}
