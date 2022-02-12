package com.epam.online_marketplace_springBoot.dto.requestEntity;


import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class RequestUserDto {

    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String matchingPassword;

    public RequestUserDto() {
    }

    public RequestUserDto(String name, String surname, String username, String password, String matchingPassword) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }
}
