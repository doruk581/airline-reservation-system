package com.finartz.flightmanagement.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AIRLINE")
public class Airline {

    @Id
    @Column(name = "COMPANY_NAME")
    private String airline;

    @OneToMany(mappedBy = "airline")
    @JsonManagedReference
    private List<Flight> flightList = new ArrayList<>();
}
