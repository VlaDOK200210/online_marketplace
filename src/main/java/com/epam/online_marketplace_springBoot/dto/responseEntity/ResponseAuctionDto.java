package com.epam.online_marketplace_springBoot.dto.responseEntity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Calendar;

@Data
public class ResponseAuctionDto {

    private Integer id;
    private Integer startPrice;
    private Calendar endDate;
    private Integer productId;
    private Integer lastBetValue;
    private Timestamp lastBetTime;

    public ResponseAuctionDto() {
    }

    public ResponseAuctionDto(Integer id, Integer startPrice, Calendar endDate, Integer productId) {
        this.id = id;
        this.startPrice = startPrice;
        this.endDate = endDate;
        this.productId = productId;
    }


}
