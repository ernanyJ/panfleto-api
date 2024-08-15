package com.panfleto.panfleto.entities;

import com.panfleto.panfleto.enums.DaysOfWeek;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Data
public class WorkingDays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DaysOfWeek weekDay;
    private LocalTime openingTime;
    private LocalTime closingTime;
}
