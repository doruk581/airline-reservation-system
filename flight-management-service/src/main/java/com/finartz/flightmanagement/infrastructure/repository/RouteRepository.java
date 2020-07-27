package com.finartz.flightmanagement.infrastructure.repository;

import com.finartz.flightmanagement.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
}
