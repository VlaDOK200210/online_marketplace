package com.epam.online_marketplace_springBoot.dao;


import com.epam.online_marketplace_springBoot.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    /**
     * @return List of users
     */
    List<User> getAll();

    /**
     * @param id id of user
     * @return user by id
     */
    Optional<User> getById(Integer id);

    /**
     * @param user added user
     */
    void add(User user);

    /**
     * @param user User to be updated
     */
    void update(User user);

    /**
     * @param id id of User to be deleted
     */
    void delete(Integer id);

    User getByUsername(String username);
}
