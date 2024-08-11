package com.panfleto.panfleto.utils;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Data
public class WorkingDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime openingHour;
    private LocalTime closingHour;

    @Enumerated(value = EnumType.STRING)
    private DaysOfWeek days;

}

