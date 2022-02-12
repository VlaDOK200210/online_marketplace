package com.epam.online_marketplace_springBoot.servicesImpl;

import com.epam.online_marketplace_springBoot.dao.AuctionDao;
import com.epam.online_marketplace_springBoot.dto.responseEntity.ResponseAuctionDto;
import com.epam.online_marketplace_springBoot.dto.responseEntity.ResponseBetDto;
import com.epam.online_marketplace_springBoot.entities.Auction;
import com.epam.online_marketplace_springBoot.entities.Bet;
import com.epam.online_marketplace_springBoot.services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private AuctionDao auctionDao;

    @Override
    public List<ResponseAuctionDto> getAll() {
        return wrapAuctionListInAuctionResponseDtoList(auctionDao.getAll());
    }

    @Override
    public ResponseAuctionDto getById(Integer id) {
        Optional<Auction> auction = auctionDao.getById(id);
        if (auction.isPresent()) {
            ResponseAuctionDto responseAuctionDTO = new ResponseAuctionDto(auction.get().getId(), auction.get().getStartPrice(), auction.get().getEndDate(), auction.get().getProduct().getId());
            Optional<Bet> bet = auctionDao.getLastBet(auction.get());
            if (bet.isPresent()) {
                responseAuctionDTO.setLastBetTime(bet.get().getBetTime());
                responseAuctionDTO.setLastBetValue(bet.get().getBet());
            }
            return responseAuctionDTO;
        }
        return null;
    }

    @Override
    public void add(Auction auction) {
        auctionDao.add(auction);
    }

    @Override
    public void update(Auction auction) {
        auctionDao.update(auction);
    }

    @Override
    public void delete(Integer id) {
        auctionDao.delete(id);
    }

    @Override
    public Optional<ResponseBetDto> getLastBet(Auction auction) {
        Optional<Bet> tempBet = auctionDao.getLastBet(auction);
        Optional<ResponseBetDto> responseBetDto = Optional.empty();
        if(tempBet.isPresent()){
            responseBetDto = Optional.of(new ResponseBetDto(tempBet.get()));
        }
        return responseBetDto;
    }

    @Override
    public List<ResponseAuctionDto> getByUserId(Integer userId) {
        return wrapAuctionListInAuctionResponseDtoList(auctionDao.getByUserId(userId));
    }

    @Override
    public List<ResponseAuctionDto> getByProductName(String productName) {
        return wrapAuctionListInAuctionResponseDtoList(auctionDao.getByProductName(productName));
    }

    private List<ResponseAuctionDto> wrapAuctionListInAuctionResponseDtoList(List<Auction> auctions) {
        List<ResponseAuctionDto> auctionResponseList = new ArrayList<>();
        for (Auction auction : auctions) {
            ResponseAuctionDto responseAuctionDTO = new ResponseAuctionDto(auction.getId(), auction.getStartPrice(), auction.getEndDate(), auction.getProduct().getId());
            Optional<Bet> bet = auctionDao.getLastBet(auction);
            if (bet.isPresent()) {
                responseAuctionDTO.setLastBetTime(bet.get().getBetTime());
                responseAuctionDTO.setLastBetValue(bet.get().getBet());
            }
            auctionResponseList.add(responseAuctionDTO);
        }
        return auctionResponseList;
    }

}
