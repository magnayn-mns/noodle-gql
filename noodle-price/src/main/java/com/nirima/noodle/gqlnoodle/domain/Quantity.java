package com.nirima.noodle.gqlnoodle.domain;
import com.nirima.noodle.gqlnoodle.core.domain.Entity;

public class Quantity extends Entity {
    public boolean calculatedQuantity = false;

    public String numberOfUnits = "1";

    public UnitOfMeasure unitOfMeasure = UnitOfMeasure.KG;
    public Quantity() {
    }

    public Quantity(String id) {
        super(id);
    }


}
