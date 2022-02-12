package com.epam.online_marketplace_springBoot.servicesImpl;

import com.epam.online_marketplace_springBoot.dao.AuctionDao;
import com.epam.online_marketplace_springBoot.dao.BetDao;
import com.epam.online_marketplace_springBoot.dao.UserDao;
import com.epam.online_marketplace_springBoot.dto.requestEntity.RequestBetDto;
import com.epam.online_marketplace_springBoot.dto.responseEntity.ResponseBetDto;
import com.epam.online_marketplace_springBoot.entities.Auction;
import com.epam.online_marketplace_springBoot.entities.Bet;
import com.epam.online_marketplace_springBoot.entities.User;
import com.epam.online_marketplace_springBoot.services.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class BetServiceImpl implements BetService {

    @Autowired
    private AuctionDao auctionDAO;

    @Autowired
    private BetDao betDAO;

    @Autowired
    private UserDao userDAO;


    @Override
    public ResponseBetDto addBet(RequestBetDto bet) {
        if (checkValidBet(bet)) {
            Optional<User> tempUser = userDAO.getById(bet.getUserId());
            Optional<Auction> tempAuction = auctionDAO.getById(bet.getAuctionId());
            if (tempUser.isPresent() && tempAuction.isPresent()) {
                Bet tempBet = new Bet(bet.getBet(),
                        new Timestamp(System.currentTimeMillis()), tempUser.get(), tempAuction.get());
                betDAO.add(tempBet);
                return new ResponseBetDto(tempBet);
            }
        }
        return null;
    }

    @Override
    public boolean checkValidBet(RequestBetDto bet) {
        Optional<Auction> tempAuction = auctionDAO.getById(bet.getAuctionId());
        if (tempAuction.isPresent()) {
            Optional<Bet> lastBet = auctionDAO.getLastBet(tempAuction.get());
            return lastBet.isEmpty() || lastBet.get().getBet() <= bet.getBet();
        }
        return false;
    }
}
