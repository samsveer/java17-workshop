package com.ideas.workshop.dbservice.interfaces;

import com.ideas.workshop.dbservice.entity.Hotel;

import java.util.List;

public interface HotelDatabaseService {

    Hotel getHotel(String hotelName);

    List<Hotel> getCompetitors(Hotel hotel);

}
