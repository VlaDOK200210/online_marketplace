package com.epam.online_marketplace_springBoot.dto.requestEntity;

import lombok.Data;

@Data
public class RequestBetDto {

    private Integer bet;
    private Integer userId;
    private Integer auctionId;

    public RequestBetDto() {
    }

    public RequestBetDto(Integer bet, Integer userId, Integer auctionId) {
        this.bet = bet;
        this.userId = userId;
        this.auctionId = auctionId;
    }
}
