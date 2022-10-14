package com.nirima.noodle.gqlnoodle.domain;

import com.nirima.noodle.gqlnoodle.core.domain.Entity;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Quote extends Entity {
    public List<String> coupons = new ArrayList<>();
    public ZonedDateTime creationDate = ZonedDateTime.now();
    public String currency = "GBP";
    public Identity customer = new Identity();
    public ZonedDateTime effectiveDate = ZonedDateTime.now();
    public List<QuoteInvalidReason> invalidReasons = new ArrayList<QuoteInvalidReason>();
    public List<LineItem> lineItems = new ArrayList<LineItem>();
    public Location location = new Location();
    public List<QuoteReward> rewards = new ArrayList<QuoteReward>();
    public QuoteState state = QuoteState.OPEN;
    public Totals totals = new Totals();
    public boolean valid = true;

    public Quote() {

    }

    public Quote(String id) {
        super(id);
    }

    public void addLineItem(LineItem lineItem) {
        lineItem.id = id + "." + (lineItems.size() + 1);
        lineItems.add(lineItem);
    }
}
