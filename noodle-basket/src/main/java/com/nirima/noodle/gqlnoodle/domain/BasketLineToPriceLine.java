package com.nirima.noodle.gqlnoodle.domain;

public class BasketLineToPriceLine {
    public String lineId;
    public String quoteId;

    public BasketLineToPriceLine(String quoteId, String lineId) {
        this.lineId = lineId;
        this.quoteId = quoteId;
    }
}
