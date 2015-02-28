package com.aakhmerov.thack.api.service.gyg.tos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by aakhmerov on 28.02.15.
 */
@XmlRootElement
public class CategoryTO {
    @JsonProperty("category_id")
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
