package com.demo.products.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @SequenceGenerator(name = "product_generator", sequenceName = "product_generator_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    private Long id;

    @Column(name = "name", nullable = false, length = 180)
    private String name;

    @Column(name = "description", nullable = false, length = 80)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

}
