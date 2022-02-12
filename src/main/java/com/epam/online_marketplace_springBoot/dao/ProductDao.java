package com.epam.online_marketplace_springBoot.dao;


import com.epam.online_marketplace_springBoot.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    /**
     * @return List of goods
     */
    List<Product> getAll();

    /**
     * @param userId seller id
     * @return list of seller's goods
     */
    List<Product> getByUserId(Integer userId);

    /**
     * @param id id of goods
     * @return goods by id
     */
    Optional<Product> getById(Integer id);

    /**
     * Add product to db
     */
    void add(Product product);

    /**
     * @param product product to be updated
     */
    void update(Product product);

    /**
     * @param id id of goods to be deleted
     */
    void delete(Integer id);

    /**
     * @param name name of product
     * @return product by name
     */
    List<Product> getByName(String name);
}
