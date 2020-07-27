package com.finartz.flightmanagement.infrastructure.repository;

import com.finartz.flightmanagement.domain.Flight;
import com.finartz.flightmanagement.domain.FlightRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class DefaultFlightRepository implements FlightRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Flight flight) {
        entityManager.persist(flight);
    }

    @Override
    @Transactional
    public void update(Flight flight) {
        entityManager.merge(flight);
    }

    @Override
    public Optional<Flight> findById(final Long id) {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Flight> query = cb.createQuery(Flight.class);
        final Root<Flight> flightRoot = query.from(Flight.class);
        query.where(cb.equal(flightRoot.get("id"), id));

        final List<Flight> flightList = entityManager.createQuery(query).getResultList();

        return flightList.isEmpty() ? Optional.empty() : Optional.of(flightList.get(0));
    }

    @Override
    public List<Flight> findAllByAirline(String airlineCode) {

        final Query query = entityManager.createQuery("SELECT fl FROM Flight fl JOIN fl.airline u WHERE u.airline=:airlineCode");
        query.setParameter("airlineCode", airlineCode);

        return query.getResultList();
    }
}
