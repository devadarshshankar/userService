package dev.adarsh.userservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role extends BaseModel {
    private String name;

    public Role(String name) {

    }

    public Role() {

    }
}
