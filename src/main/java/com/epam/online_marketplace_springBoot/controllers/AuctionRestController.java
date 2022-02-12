package com.epam.online_marketplace_springBoot.controllers;

import com.epam.online_marketplace_springBoot.dao.AuctionDao;
import com.epam.online_marketplace_springBoot.dao.BetDao;
import com.epam.online_marketplace_springBoot.dao.ProductDao;
import com.epam.online_marketplace_springBoot.dto.requestEntity.RequestBetDto;
import com.epam.online_marketplace_springBoot.dto.responseEntity.ResponseAuctionDto;
import com.epam.online_marketplace_springBoot.dto.responseEntity.ResponseBetDto;
import com.epam.online_marketplace_springBoot.entities.*;
import com.epam.online_marketplace_springBoot.services.AuctionService;
import com.epam.online_marketplace_springBoot.services.BetService;
import com.epam.online_marketplace_springBoot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api/auctions")
public class AuctionRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductDao productDAO;

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private BetDao betDAO;

    @Autowired
    private BetService betService;


    @GetMapping
    public ResponseEntity<List<ResponseAuctionDto>> getAllAuctions(@RequestParam(value = "auctionId") Optional<Integer> auctionId,
                                                                   @RequestParam(value = "userId") Optional<Integer> userId,
                                                                   @RequestParam(value = "productName") Optional<String> productName) {
        List<ResponseAuctionDto> auctionResponseList = new ArrayList<>();
        if(auctionId.isPresent()){
            auctionResponseList.add(auctionService.getById(auctionId.get()));
            return ResponseEntity.ok().body(auctionResponseList);
        } else if(userId.isPresent()){
            auctionResponseList = auctionService.getByUserId(userId.get());
            return ResponseEntity.ok().body(auctionResponseList);
        } else if(productName.isPresent()){
            auctionResponseList = auctionService.getByProductName(productName.get());
            return ResponseEntity.ok().body(auctionResponseList);
        } else {
            auctionResponseList = auctionService.getAll();
            return ResponseEntity.ok().body(auctionResponseList);
        }
    }


    @PostMapping
    public ResponseBetDto addBet(@RequestBody RequestBetDto betDTO) {
        return betService.addBet(betDTO);
    }
}
