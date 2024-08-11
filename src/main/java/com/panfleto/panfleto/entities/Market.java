package com.panfleto.panfleto.entities;

import com.panfleto.panfleto.utils.WorkingDay;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "markets")
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private String imgUrl;

    @OneToMany
    private List<WorkingDay> openingHours;

}
