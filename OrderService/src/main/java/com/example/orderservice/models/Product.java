package com.example.orderservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Product extends BaseModel {

    private String name;

    private String description;

    private Double price;

    private String imageUrl;

    private String category;

}
