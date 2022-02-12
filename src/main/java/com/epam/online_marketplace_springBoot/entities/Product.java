package com.epam.online_marketplace_springBoot.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "goods")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;

    @ManyToOne()
    @JoinColumn(name = "seller_id")
    private User user;

    public Product() {
    }

    public Product(String name, String description, User user) {
        this.name = name;
        this.description = description;
        this.user = user;
    }
}
