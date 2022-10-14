package com.nirima.noodle.gqlnoodle.domain;

import com.nirima.noodle.gqlnoodle.core.domain.Entity;

public class Totals extends Entity {
    public String grandTotal = "79.99";


    public String totalAfterDiscounts = "79.99";


    public String totalBeforeDiscounts = "79.99";


    public String totalReturns = "0.00";

    public Totals() {
    }

    public Totals(String id) {
        super(id);
    }
}
