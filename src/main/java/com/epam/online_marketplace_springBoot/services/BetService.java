package com.epam.online_marketplace_springBoot.services;

import com.epam.online_marketplace_springBoot.dto.requestEntity.RequestBetDto;
import com.epam.online_marketplace_springBoot.dto.responseEntity.ResponseBetDto;

public interface BetService {
    /**
     * @param bet bet to adding
     * @return adding status
     */
    ResponseBetDto addBet(RequestBetDto bet);

    /**
     * @param bet bet to check
     * @return check status
     */
    boolean checkValidBet(RequestBetDto bet);
}
