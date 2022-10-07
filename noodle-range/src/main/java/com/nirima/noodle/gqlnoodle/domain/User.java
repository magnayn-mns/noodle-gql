package com.nirima.noodle.gqlnoodle.domain;

import java.util.UUID;

public class User {
    public UUID id = UUID.randomUUID();
    public String name = "person";

    public User(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
