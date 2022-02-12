package com.epam.online_marketplace_springBoot.dao;

import com.epam.online_marketplace_springBoot.entities.Auction;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class AuctionJpaDaoTest extends DaoTest{

    @Test
    public void getAll() {
        assertEquals(addedAuctionEntities.size(), auctionDAO.getAll().size());
    }

    @Test
    public void getById() {
        Auction expectedAuctionEntity = auctionDAO.getAll().get(1);
        assertEquals(expectedAuctionEntity.getId(), auctionDAO.getById(expectedAuctionEntity.getId()).get().getId());
    }

    @Test
    public void add() {
        int expectedSize = auctionDAO.getAll().size() + 1;
        Auction auctionEntity = new Auction(300, new GregorianCalendar(2003, Calendar.MARCH, 3), itemEntity1);
        auctionDAO.add(auctionEntity);
        assertEquals(expectedSize, auctionDAO.getAll().size());
    }

    @Test
    public void update() {
        int updatedStartPrice = 228;
        Auction auctionEntity = auctionDAO.getAll().get(0);
        auctionEntity.setStartPrice(updatedStartPrice);
        auctionDAO.update(auctionEntity);
        assertEquals(updatedStartPrice, auctionDAO.getById(auctionEntity.getId()).get().getStartPrice());
    }

    @Test
    public void delete() {
        int expectedSize = auctionDAO.getAll().size() - 1;
        Auction auctionEntity = auctionDAO.getAll().get(0);
        auctionDAO.delete(auctionEntity.getId());
        assertEquals(expectedSize, auctionDAO.getAll().size());
    }

}