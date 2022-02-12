package com.epam.online_marketplace_springBoot.controllers;


import com.epam.online_marketplace_springBoot.dto.requestEntity.RequestUserDto;
import com.epam.online_marketplace_springBoot.entities.User;
import com.epam.online_marketplace_springBoot.services.UserService;
import com.epam.online_marketplace_springBoot.util.UserDtoValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RegistrationController {

    private final UserService userService;
    private final UserDtoValidator userValidator;

    public RegistrationController(UserService userService, UserDtoValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/registration")
    public String registration(ModelMap model) {
        RequestUserDto requestUserDTO = new RequestUserDto();
        model.addAttribute("user", requestUserDTO);
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") RequestUserDto user, BindingResult bindingResult, ModelMap model) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "registration";
        } else if (userService.addUser(new User(user.getName(), user.getSurname(), user.getUsername(), user.getPassword()))) {
            return "redirect:/login";
        } else {
            return "registration";
        }
    }
}
