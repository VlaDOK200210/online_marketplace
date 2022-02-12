package com.epam.online_marketplace_springBoot.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer bet;
    private Timestamp betTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction;

    public Bet() {
    }

    public Bet(Integer bet, Timestamp betTime, User user, Auction auction) {
        this.bet = bet;
        this.betTime = betTime;
        this.user = user;
        this.auction = auction;
    }
}
