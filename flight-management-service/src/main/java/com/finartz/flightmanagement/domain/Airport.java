package com.finartz.flightmanagement.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "AIRPORT")
public class Airport {

    @Id
    @Column(name = "AIRPORT_CODE")
    private String code;

    @Column(name = "AIRPORT_NAME")
    private String airportName;

    @Column(name = "COUNTRY")
    private String country;
}
