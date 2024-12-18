package com.ecommerce.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Entity
public class Category extends BaseModel implements Serializable {

    private String name;

    // FetchType is LAZY to ensure that the products are not loaded when the category is loaded
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Product> products;

}
