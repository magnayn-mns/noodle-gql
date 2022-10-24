package com.nirima.noodle.gqlnoodle.core.domain;

import java.util.UUID;

public abstract class Entity {
    public String id;

    public Entity() {
        // Just generate rando
        id = UUID.randomUUID().toString();
    }

    public Entity(String id) {
        this.id = id!=null?id:UUID.randomUUID().toString();
    }
}
