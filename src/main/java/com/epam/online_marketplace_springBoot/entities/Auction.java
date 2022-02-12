package com.epam.online_marketplace_springBoot.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "auctions")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer startPrice;
    private Calendar endDate;

    @OneToOne
    @JoinColumn(name = "goods_id")
    private Product product;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Bet> bets;

    public Auction() {
    }

    public Auction(Integer startPrice, Calendar endDate, Product product) {
        this.startPrice = startPrice;
        this.endDate = endDate;
        this.product = product;
    }
}
