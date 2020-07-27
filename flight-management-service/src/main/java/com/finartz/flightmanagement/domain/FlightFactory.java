package com.finartz.flightmanagement.domain;

import com.finartz.flightmanagement.interfaces.request.AddFlightRequest;

public interface FlightFactory {

    Flight createFrom(final AddFlightRequest request);
}
