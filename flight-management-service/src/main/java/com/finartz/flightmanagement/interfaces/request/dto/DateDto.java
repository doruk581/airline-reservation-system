package com.finartz.flightmanagement.interfaces.request.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateDto {
    private Integer day;
    private Integer month;
    private Integer year;
    private Integer hour;
    private Integer minute;
}
