package com.epam.online_marketplace_springBoot.daoImpl;


import com.epam.online_marketplace_springBoot.dao.AuctionDao;
import com.epam.online_marketplace_springBoot.dao.ProductDao;
import com.epam.online_marketplace_springBoot.entities.Auction;
import com.epam.online_marketplace_springBoot.entities.Bet;
import com.epam.online_marketplace_springBoot.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class AuctionJpaDao implements AuctionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ProductDao productDAO;

    @Override
    public List<Auction> getAll() {
        List<Auction> auctions = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Auction> criteria = builder.createQuery(Auction.class);
            criteria.from(Auction.class);
            auctions = session.createQuery(criteria).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return auctions;
    }

    @Override
    public Optional<Auction> getById(Integer id) {
        Optional<Auction> auction = Optional.empty();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            auction = Optional.of(session.find(Auction.class, id));
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return auction;
    }

    @Override
    public void add(Auction auction) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(auction);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Auction auction) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(auction);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Auction auction = session.load(Auction.class, id);
            session.delete(auction);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Bet> getLastBet(Auction auction) {
        Optional<Bet> bet = Optional.empty();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Bet> criteria = builder.createQuery(Bet.class);
            Root<Bet> root = criteria.from(Bet.class);
            criteria.select(root).where(builder.equal(root.get("auction"), auction.getId()));
            List<Bet> bets;
            bets = session.createQuery(criteria).getResultList();
            bet = Optional.of(bets.stream().max(Comparator.comparing(Bet::getBetTime)).get());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bet;
    }

    @Override
    public List<Auction> getByUserId(Integer userId) {
        List<Auction> auctions = new ArrayList<>();
        List<Product> products = productDAO.getByUserId(userId);
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Auction> criteria = builder.createQuery(Auction.class);
            Root<Auction> root = criteria.from(Auction.class);
            criteria.select(root).where(root.get("product").in(products));
            auctions = session.createQuery(criteria).getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return auctions;
    }

    @Override
    public List<Auction> getByProductName(String productName) {
        List<Auction> auctions = new ArrayList<>();
        List<Product> products = productDAO.getByName(productName);
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Auction> criteria = builder.createQuery(Auction.class);
            Root<Auction> root = criteria.from(Auction.class);
            criteria.select(root).where(root.get("product").in(products));
            auctions = session.createQuery(criteria).getResultList();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return auctions;
    }
}

