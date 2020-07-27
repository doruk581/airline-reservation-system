package com.finartz.flightmanagement.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "FLIGHT_INFORMATION")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FK_ROUTE")
    @JsonBackReference
    private Route route;

    @ManyToOne
    @JoinColumn(name = "FK_AIRLINE")
    @JsonBackReference
    private Airline airline;

    @Column(name = "SEATCOUNT")
    private Integer availableSeatCount;

    @Column(name = "CAPACITY")
    private Integer capacity;

    @Column(name = "DEPARTURE_TIME")
    private LocalDateTime departureTime;

    @Column(name = "TICKET_PRICE")
    private BigDecimal ticketPrice;

    @Column(name = "PROCESS_DATE")
    private LocalDateTime created;

    @PrePersist
    public void prePersist() {
        this.created = LocalDateTime.now();
    }

    public void decrementAvailableSeatCount(){
        this.availableSeatCount--;
    }

    public void incrementAvailableSeatCount(){
        this.availableSeatCount++;
    }

    public void incrementTicketPriceBy10Percent(){
       final BigDecimal percentageAmount = this.ticketPrice.multiply(Constants.INCREMENT_PERCENTAGE)
               .divide(Constants.PERCENT,RoundingMode.DOWN);

       this.ticketPrice = this.ticketPrice.add(percentageAmount);
    }

    @JsonIgnore
    public Boolean isAvailableForReservation(){
        return this.availableSeatCount > 0;
    }
}
