package com.aakhmerov.thack.api.service.gyg.tos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by aakhmerov on 28.02.15.
 */
@XmlRootElement
public class DurationTO {
    private Long duration;
    private String unit;

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
