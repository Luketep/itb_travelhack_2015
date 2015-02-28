package com.aakhmerov.thack.api.service.gyg.tos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by aakhmerov on 28.02.15.
 */
@XmlRootElement
public class PriceTO {
    private PriceValuesTO values;
    private String description;

    public PriceValuesTO getValues() {
        return values;
    }

    public void setValues(PriceValuesTO values) {
        this.values = values;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
