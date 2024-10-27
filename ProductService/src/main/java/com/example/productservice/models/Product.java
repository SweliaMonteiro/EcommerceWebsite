package com.example.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;


@Getter
@Setter
@Entity
public class Product extends BaseModel implements Serializable {

    private String name;

    private String description;

    private double price;

    private String imageUrl;

    @ManyToOne
    private Category category;

}
