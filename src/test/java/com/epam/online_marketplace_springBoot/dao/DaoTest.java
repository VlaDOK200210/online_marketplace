package com.epam.online_marketplace_springBoot.dao;

import com.epam.online_marketplace_springBoot.entities.*;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@SpringBootTest
@Ignore
@RunWith(SpringRunner.class)
public class DaoTest {

    @Autowired
    protected ProductDao itemDAO;

    @Autowired
    protected UserDao userDAO;

    @Autowired
    protected AuctionDao auctionDAO;

    @Autowired
    protected BetDao betDAO;

    protected final User userEntity1 = new User("testName1", "testSurname1", "testUsername1", "testPassword1", Role.ROLE_USER);
    protected final User userEntity2 = new User("testName2", "testSurname2", "testUsername1", "testPassword1", Role.ROLE_USER);
    protected final User userEntityNoRefs = new User("testName3", "testSurname3", "testUsername1", "testPassword1", Role.ROLE_USER);

    protected final Product itemEntity1 = new Product("testProduct1", "testDescription1", userEntity1);
    protected final Product itemEntity2 = new Product("testProduct2", "testDescription2", userEntity2);
    protected final Product itemEntity3 = new Product("testProduct3", "testDescription3", userEntity1);
    protected final Product itemEntityNoRefs = new Product("testProduct3", "testDescription3", userEntity1);

    protected final Auction auctionEntity1 = new Auction(100, new GregorianCalendar(2001, Calendar.JANUARY,1), itemEntity1);
    protected final Auction auctionEntity2 = new Auction(200, new GregorianCalendar(2002,Calendar.FEBRUARY,2), itemEntity2);
    protected final Auction auctionEntityNoRefs = new Auction(300, new GregorianCalendar(2003,Calendar.MARCH,3), itemEntity3);

    protected final Bet betEntity1 = new Bet(110, new Timestamp(1000), userEntity1, auctionEntity2);
    protected final Bet betEntity2 = new Bet(120, new Timestamp(2000), userEntity2, auctionEntity1);


    protected final List<User> addedUserEntities = new ArrayList<>(){{
        add(userEntityNoRefs);
        add(userEntity1);
        add(userEntity2);
    }};

    protected final List<Product> addedGoods = new ArrayList<>(){{
        add(itemEntityNoRefs);
        add(itemEntity1);
        add(itemEntity2);
        add(itemEntity3);
    }};

    protected final List<Auction> addedAuctionEntities = new ArrayList<>() {{
        add(auctionEntityNoRefs);
        add(auctionEntity1);
        add(auctionEntity2);
    }};

    protected final List<Bet> addedBetEntities = new ArrayList<>(){{
        add(betEntity1);
        add(betEntity2);
    }};


    @BeforeEach
    public void initDb(){
        userDAO.add(userEntityNoRefs);
        userDAO.add(userEntity1);
        userDAO.add(userEntity2);

        itemDAO.add(itemEntityNoRefs);
        itemDAO.add(itemEntity1);
        itemDAO.add(itemEntity2);
        itemDAO.add(itemEntity3);

        auctionDAO.add(auctionEntityNoRefs);
        auctionDAO.add(auctionEntity1);
        auctionDAO.add(auctionEntity2);

        betDAO.add(betEntity1);
        betDAO.add(betEntity2);
    }

    @AfterEach
    public void cleanDb() {

        for (Bet betEntity :
                betDAO.getAll()) {
            betDAO.delete(betEntity.getId());
            addedBetEntities.remove(betEntity);
        }
        for (Auction auctionEntity :
                auctionDAO.getAll()) {
            auctionDAO.delete(auctionEntity.getId());
        }
        for (Product productEntity :
                itemDAO.getAll()) {
            itemDAO.delete(productEntity.getId());
        }
        for (User userEntity :
                userDAO.getAll()) {
            userDAO.delete(userEntity.getId());
        }
    }

}
