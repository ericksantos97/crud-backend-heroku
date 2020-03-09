package com.demo.products.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDTO implements Serializable {

    Long id;
    String name;
    String description;
    Double price;

}
