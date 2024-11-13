package com.panfleto.panfleto.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.time.LocalTime;
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

    private String imgUrl;

    private LocalTime openingTimeMonday;
    private LocalTime closingTimeMonday;

    private LocalTime openingTimeTuesday;
    private LocalTime closingTimeTuesday;

    private LocalTime openingTimeWednesday;
    private LocalTime closingTimeWednesday;

    private LocalTime openingTimeThursday;
    private LocalTime closingTimeThursday;

    private LocalTime openingTimeFriday;
    private LocalTime closingTimeFriday;

    private LocalTime openingTimeSaturday;
    private LocalTime closingTimeSaturday;

    private LocalTime openingTimeSunday;
    private LocalTime closingTimeSunday;

    private String latitude;

    private String longitude;

    @OneToMany(orphanRemoval = true, mappedBy = "market")
    List<Offer> offers;

    public Market(JSONObject object, String url) {
        this.setName(object.getString("name"));
        if(object.has("openingTimeMonday")){
            this.setOpeningTimeMonday(LocalTime.parse(object.getString("openingTimeMonday")));
        }
        if(object.has("closingTimeMonday")){
            this.setClosingTimeMonday(LocalTime.parse(object.getString("closingTimeMonday")));
        }
        if(object.has("openingTimeTuesday")){
            this.setOpeningTimeTuesday(LocalTime.parse(object.getString("openingTimeTuesday")));
        }
        if(object.has("closingTimeTuesday")){
            this.setClosingTimeTuesday(LocalTime.parse(object.getString("closingTimeTuesday")));
        }
        if(object.has("openingTimeWednesday")){
            this.setOpeningTimeWednesday(LocalTime.parse(object.getString("openingTimeWednesday")));
        }
        if(object.has("closingTimeWednesday")){
            this.setClosingTimeWednesday(LocalTime.parse(object.getString("closingTimeWednesday")));
        }
        if(object.has("openingTimeThursday")){
            this.setOpeningTimeThursday(LocalTime.parse(object.getString("openingTimeThursday")));
        }
        if(object.has("closingTimeThursday")){
            this.setClosingTimeThursday(LocalTime.parse(object.getString("closingTimeThursday")));
        }
        if(object.has("openingTimeFriday")){
            this.setOpeningTimeFriday(LocalTime.parse(object.getString("openingTimeFriday")));
        }
        if(object.has("closingTimeFriday")){
            this.setClosingTimeFriday(LocalTime.parse(object.getString("closingTimeFriday")));
        }
        if(object.has("openingTimeSaturday")){
            this.setOpeningTimeSaturday(LocalTime.parse(object.getString("openingTimeSaturday")));
        }
        if(object.has("closingTimeSaturday")){
            this.setClosingTimeSaturday(LocalTime.parse(object.getString("closingTimeSaturday")));
        }
        if(object.has("openingTimeSunday")){
            this.setOpeningTimeSunday(LocalTime.parse(object.getString("openingTimeSunday")));
        }
        if(object.has("closingTimeSunday")){
            this.setClosingTimeSunday(LocalTime.parse(object.getString("closingTimeSunday")));
        }

        this.setLatitude(object.getString("latitude"));
        this.setLongitude(object.getString("longitude"));
        this.setImgUrl(url);
    }

}
