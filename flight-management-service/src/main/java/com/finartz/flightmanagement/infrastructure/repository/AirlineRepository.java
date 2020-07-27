package com.finartz.flightmanagement.infrastructure.repository;

import com.finartz.flightmanagement.domain.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, String> {
}
