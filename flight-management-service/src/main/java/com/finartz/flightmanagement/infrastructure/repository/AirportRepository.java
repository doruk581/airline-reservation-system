package com.finartz.flightmanagement.infrastructure.repository;

import com.finartz.flightmanagement.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, String> {
}
