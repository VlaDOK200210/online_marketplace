package com.epam.online_marketplace_springBoot.dao;


import com.epam.online_marketplace_springBoot.entities.Auction;
import com.epam.online_marketplace_springBoot.entities.Bet;

import java.util.List;
import java.util.Optional;

public interface AuctionDao {
    /**
     * @return List of auctions
     */
    List<Auction> getAll();

    /**
     * @param id id of auction
     * @return auction by id
     */
    Optional<Auction> getById(Integer id);

    /**
     * Add user to db
     */
    void add(Auction auction);

    /**
     * @param auction auction to be updated
     */
    void update(Auction auction);

    /**
     * @param id id of auction to be deleted
     */
    void delete(Integer id);


    /**
     * @param auction auction where we're catching last bet
     * @return last bet of auction
     */
    Optional<Bet> getLastBet(Auction auction);

    /**
     * @param userId seller id
     * @return list of seller's auctions
     */
    List<Auction> getByUserId(Integer userId);

    /**
     * @param productName name of product
     * @return auction by product name
     */
    List<Auction> getByProductName(String productName);
}
