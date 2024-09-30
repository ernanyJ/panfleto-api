package com.panfleto.panfleto.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

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

    @ManyToMany
    List<WorkingDays> workingDays;

    @OneToMany(orphanRemoval = true, mappedBy = "market")
    List<Flyer> flyers;

    public void addFlyer(Flyer flyer) {
        this.flyers.add(flyer);
    }

    public void removeFlyer(Long id) {
        for (Flyer flyer : flyers) {
            if (flyer.getId().equals(id)) {
                flyers.remove(flyer);
                break;
            }
        }
    }

    public Market(JSONObject object, List<WorkingDays> workingDaysList, String url) {
        this.setName(object.getString("name"));
        this.setAddress(object.getString("address"));
        this.setWorkingDays(workingDaysList);
        this.setImgUrl(url);
    }

}
