package com.epam.online_marketplace_springBoot.dao;


import com.epam.online_marketplace_springBoot.entities.Bet;

import java.util.List;
import java.util.Optional;

public interface BetDao {
    /**
     * @return List of bets
     */
    List<Bet> getAll();

    /**
     * @param id id of bet
     * @return bet by id
     */
    Optional<Bet> getById(Integer id);

    /**
     * Add user to db
     */
    void add(Bet bet);

    /**
     * @param bet bet to be updated
     */
    void update(Bet bet);

    /**
     * @param id id of bet to be deleted
     */
    void delete(Integer id);
}
