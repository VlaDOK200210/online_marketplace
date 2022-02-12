package com.epam.online_marketplace_springBoot.dao;

import com.epam.online_marketplace_springBoot.entities.Role;
import com.epam.online_marketplace_springBoot.entities.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserJpaDaoTest extends DaoTest {



    @Test
    public void getAll() {
        assertEquals(addedUserEntities.size(), userDAO.getAll().size());

    }

    @Test
    public void getById() {
        User expectedUserEntity = userDAO.getAll().get(1);
        assertEquals(expectedUserEntity.getId(), userDAO.getById(expectedUserEntity.getId()).get().getId());
    }

    @Test
    public void add() {
        int expectedSize = userDAO.getAll().size() + 1;
        User userEntity = new User("AddName", "AddSurname", "addTestUsername1", "addTestPassword1", Role.ROLE_USER);
        userDAO.add(userEntity);
        assertEquals(expectedSize, userDAO.getAll().size());
    }

    @Test
    public void update() {
        String updatedName = "updatedName";
        User userEntity = userDAO.getAll().get(0);
        userEntity.setName(updatedName);
        userDAO.update(userEntity);
        assertEquals(updatedName, userDAO.getById(userEntity.getId()).get().getName());
    }

    @Test
    public void delete() {
        int expectedSize = userDAO.getAll().size() - 1;
        User userEntity = userDAO.getAll().get(0);
        userDAO.delete(userEntity.getId());
        assertEquals(expectedSize, userDAO.getAll().size());
    }

}

