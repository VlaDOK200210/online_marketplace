package com.epam.online_marketplace_springBoot.services;

import com.epam.online_marketplace_springBoot.dto.responseEntity.ResponseAuctionDto;
import com.epam.online_marketplace_springBoot.dto.responseEntity.ResponseBetDto;
import com.epam.online_marketplace_springBoot.entities.Auction;

import java.util.List;
import java.util.Optional;

public interface AuctionService {

    /**
     * @return List of auctionDto
     */
    List<ResponseAuctionDto> getAll();

    /**
     * @param id id of auction
     * @return auctionDto by id
     */
    ResponseAuctionDto getById(Integer id);

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
     * @return last betDto of auction
     */
    Optional<ResponseBetDto> getLastBet(Auction auction);

    /**
     * @param userId seller id
     * @return list of seller's auctionDto
     */
    List<ResponseAuctionDto> getByUserId(Integer userId);

    /**
     * @param productName name of product
     * @return auctionDto by product name
     */
    List<ResponseAuctionDto> getByProductName(String productName);

}
