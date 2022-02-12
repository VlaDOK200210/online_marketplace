package com.epam.online_marketplace_springBoot.dto.responseEntity;


import lombok.Data;


@Data
public class ResponseProductDto {

    private Integer id;
    private String name;
    private String description;
    private Integer userId;

    public ResponseProductDto() {
    }

    public ResponseProductDto(String name, String description, Integer userId) {
        this.name = name;
        this.description = description;
        this.userId = userId;
    }
}
