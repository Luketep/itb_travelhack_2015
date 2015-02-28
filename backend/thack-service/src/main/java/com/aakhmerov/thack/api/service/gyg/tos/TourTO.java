package com.aakhmerov.thack.api.service.gyg.tos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by aakhmerov on 28.02.15.
 */
public class TourTO {
    @JsonProperty("tour_id")
    private Long id;
    private String title;
    @JsonProperty("abstract")
    private String description;
    private PriceTO price;
    private List<CategoryTO> categories;
    private List<DurationTO> durations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PriceTO getPrice() {
        return price;
    }

    public void setPrice(PriceTO price) {
        this.price = price;
    }
}
