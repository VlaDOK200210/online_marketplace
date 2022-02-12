package com.epam.online_marketplace_springBoot.dto.responseEntity;

import com.epam.online_marketplace_springBoot.entities.Bet;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ResponseBetDto {

    private Integer id;
    private Integer bet;
    private Timestamp betTime;
    private Integer userId;
    private Integer auctionId;

    public ResponseBetDto() {
    }

    public ResponseBetDto(Integer bet, Timestamp betTime, Integer userId, Integer auctionId) {
        this.bet = bet;
        this.betTime = betTime;
        this.userId = userId;
        this.auctionId = auctionId;
    }

    public ResponseBetDto(Bet bet) {
        this.bet = bet.getBet();
        this.betTime = bet.getBetTime();
        this.userId = bet.getUser().getId();
        this.auctionId = bet.getAuction().getId();
    }
}
