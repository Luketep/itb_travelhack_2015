package com.aakhmerov.thack.api.service.tos.v1;

import org.joda.time.DateTime;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by aakhmerov on 28.02.15.
 */
@XmlRootElement
public class AttractionTO {
    private String name;
    private Long price;
    private String additionalInformation;
    private DateTime date;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
