package com.nirima.noodle.gqlnoodle.domain;

public class Pricing {
    Location location;
    Price price;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
