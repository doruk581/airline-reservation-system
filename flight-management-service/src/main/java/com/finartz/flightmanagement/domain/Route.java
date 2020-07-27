package com.finartz.flightmanagement.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ROUTE")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "DEPARTURE_ID", referencedColumnName = "AIRPORT_CODE")
    @JsonManagedReference
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "ARRIVAL_ID", referencedColumnName = "AIRPORT_CODE")
    @JsonManagedReference
    private Airport arrivalAirport;
}
