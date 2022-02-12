package com.epam.online_marketplace_springBoot.services;


import com.epam.online_marketplace_springBoot.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    /**
     * @param user new user for registration to db
     * @return adding status
     */
    boolean addUser(User user);

    /**
     * @param username username of user who find in db
     * @return UserDetails of found user
     */
    UserDetails loadUserByUsername(String username);
}
