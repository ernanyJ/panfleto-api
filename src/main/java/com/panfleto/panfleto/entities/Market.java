package com.panfleto.panfleto.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @OneToMany(cascade = CascadeType.ALL)
    List<WorkingDays> workingDays;


    @OneToMany(orphanRemoval = true)
    List<Offer> offers;

    public void addOffers(Offer offer) {
        this.offers.add(offer);
    }

    public void removeOffer(Long id) {
        for (Offer offer : offers) {
            if (offer.getId().equals(id)) {
                offers.remove(offer);
                break;
            }
        }
    }

}
