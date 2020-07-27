package com.finartz.flightmanagement.domain;

import java.util.List;
import java.util.Optional;

public interface FlightRepository {
    void save(final Flight flight);

    void update(final Flight flight);

    Optional<Flight> findById(final Long id);

    List findAllByAirline(final String airlineCode);


}
