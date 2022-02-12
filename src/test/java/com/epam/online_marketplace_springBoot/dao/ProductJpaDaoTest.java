package com.epam.online_marketplace_springBoot.dao;

import com.epam.online_marketplace_springBoot.entities.Product;
import com.epam.online_marketplace_springBoot.entities.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductJpaDaoTest extends DaoTest{

    @Test
    public void getAll() {
        assertEquals(addedGoods.size(), itemDAO.getAll().size());
    }

    @Test
    public void getByUserId() {
        Product expectedItemEntity = itemDAO.getAll().get(0);
        User requestedUserEntity = expectedItemEntity.getUser();
        assertEquals(requestedUserEntity.getName(), itemDAO.getByUserId(requestedUserEntity.getId()).get(0).getUser().getName());
    }

    @Test
    public void getById() {
        Product expectedItemEntity = itemDAO.getAll().get(1);
        assertEquals(expectedItemEntity.getId(), itemDAO.getById(expectedItemEntity.getId()).get().getId());
    }

    @Test
    public void add() {
        int expectedSize = itemDAO.getAll().size() + 1;
        Product itemEntity = new Product("AddName", "AddDescription", userEntity1);
        itemDAO.add(itemEntity);
        assertEquals(expectedSize, itemDAO.getAll().size());
    }

    @Test
    public void update() {
        String updatedName = "updatedName";
        Product itemEntity = itemDAO.getAll().get(0);
        itemEntity.setName(updatedName);
        itemDAO.update(itemEntity);
        assertEquals(updatedName, itemDAO.getById(itemEntity.getId()).get().getName());
    }

    @Test
    public void delete() {
        int expectedSize = itemDAO.getAll().size() - 1;
        Product itemEntity = itemDAO.getAll().get(0);
        itemDAO.delete(itemEntity.getId());
        assertEquals(expectedSize, itemDAO.getAll().size());
    }

}