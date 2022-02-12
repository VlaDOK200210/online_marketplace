package com.epam.online_marketplace_springBoot.controllers;


import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {

    @GetMapping("/")
    public String mainPage() {
        return "auctions";
    }

    @GetMapping(value = "/login")
    public String login() {

        Authentication authenticator = SecurityContextHolder.getContext().getAuthentication();
        if (authenticator == null || authenticator instanceof AnonymousAuthenticationToken) {
            return "/login";
        }
        return "/";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:/login";
    }
}
