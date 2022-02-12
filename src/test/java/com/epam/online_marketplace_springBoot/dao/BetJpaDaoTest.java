package com.epam.online_marketplace_springBoot.dao;

import com.epam.online_marketplace_springBoot.entities.Bet;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class BetJpaDaoTest extends DaoTest{

    @Test
    public void getAll() {
        assertEquals(addedBetEntities.size(), betDAO.getAll().size());
    }

    @Test
    public void getById() {
        Bet expectedBetEntity = betDAO.getAll().get(1);
        assertEquals(expectedBetEntity.getId(), betDAO.getById(expectedBetEntity.getId()).get().getId());
    }

    @Test
    public void add() {
        int expectedSize = betDAO.getAll().size() + 1;
        Bet betEntity = new Bet(150, new Timestamp(10000), userEntity2, auctionEntity1);
        betDAO.add(betEntity);
        assertEquals(expectedSize, betDAO.getAll().size());
    }

    @Test
    public void update() {
        int updatedBet = 228;
        Bet betEntity = betDAO.getAll().get(0);
        betEntity.setBet(updatedBet);
        betDAO.update(betEntity);
        assertEquals(updatedBet, betDAO.getById(betEntity.getId()).get().getBet());

    }

    @Test
    public void delete() {
        int expectedSize = betDAO.getAll().size() - 1;
        Bet betEntity = betDAO.getAll().get(0);
        betDAO.delete(betEntity.getId());
        assertEquals(expectedSize, betDAO.getAll().size());
    }

}