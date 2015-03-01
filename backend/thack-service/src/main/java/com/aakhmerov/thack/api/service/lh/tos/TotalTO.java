package com.aakhmerov.thack.api.service.lh.tos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by aakhmerov on 01.03.15.
 */
@XmlRootElement
public class TotalTO {
    private Double sum;
    private String cur;

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
    }
}
