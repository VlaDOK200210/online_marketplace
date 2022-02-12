package com.epam.online_marketplace_springBoot.util;


import com.epam.online_marketplace_springBoot.dao.UserDao;
import com.epam.online_marketplace_springBoot.dto.requestEntity.RequestUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserDtoValidator implements Validator {

    @Autowired
    private UserDao userDAO;

    @Override
    public boolean supports(Class<?> clazz) {
        return RequestUserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RequestUserDto requestUserDTO = (RequestUserDto) target;
        if (userDAO.getByUsername(requestUserDTO.getUsername()) != null) {
            errors.rejectValue("username", "", "This username already is used");
        }
        if (requestUserDTO.getName().length() < 1) {
            errors.rejectValue("name", "", "Too short name");
        }
        if (requestUserDTO.getSurname().length() < 1) {
            errors.rejectValue("surname", "", "Too short surname");
        }
        if (requestUserDTO.getPassword().length() < 1) {
            errors.rejectValue("password", "", "Too short password");
        }
        if (!requestUserDTO.getPassword().equals(requestUserDTO.getMatchingPassword())) {
            errors.rejectValue("matchingPassword", "", "Passwords are not equals");
        }
    }
}
