package com.ideas.workshop.dbservice;

import com.ideas.workshop.dbservice.interfaces.HotelDatabaseService;
import com.ideas.workshop.dbservice.entity.Hotel;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
@Primary
public class HotelStaticDatabaseServiceImpl implements HotelDatabaseService {

    private static final Map<String, Hotel> hotelList;
    private static final Map<Hotel, List<Hotel>> competitors;

    static {
        Hotel hotel1 = new Hotel("hotel1", BigDecimal.valueOf(10.0));
        Hotel hotel2 = new Hotel("hotel2", BigDecimal.valueOf(12.0));
        Hotel hotel3 = new Hotel("hotel3", BigDecimal.valueOf(8.0));
        Hotel hotel4 = new Hotel("hotel4", BigDecimal.valueOf(16.0));
        Hotel hotel5 = new Hotel("hotel5", BigDecimal.valueOf(20.0));

        hotelList = Map.of(
                hotel1.name(), hotel1,
                hotel2.name(), hotel2
        );

        competitors = Map.of(
                hotel1, List.of(hotel3, hotel4, hotel5),
                hotel2, List.of(hotel3, hotel5)
        );
    }

    @Override
    public Hotel getHotel(String hotelName) {
        return hotelList.get(hotelName);
    }

    @Override
    public List<Hotel> getCompetitors(Hotel hotel) {
        return competitors.get(hotel);
    }
}
