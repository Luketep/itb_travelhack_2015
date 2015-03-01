package com.aakhmerov.thack.api.service.lh.tos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by aakhmerov on 01.03.15.
 */
@XmlRootElement
public class PriceTO {
    private TotalTO Total;

    public TotalTO getTotal() {
        return Total;
    }

    public void setTotal(TotalTO total) {
        Total = total;
    }
}
