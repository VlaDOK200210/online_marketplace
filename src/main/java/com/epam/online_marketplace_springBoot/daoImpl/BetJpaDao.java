package com.epam.online_marketplace_springBoot.daoImpl;


import com.epam.online_marketplace_springBoot.dao.BetDao;
import com.epam.online_marketplace_springBoot.entities.Bet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BetJpaDao implements BetDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Bet> getAll() {
        List<Bet> bets = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Bet> criteria = builder.createQuery(Bet.class);
            criteria.from(Bet.class);
            bets = session.createQuery(criteria).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bets;
    }

    @Override
    public Optional<Bet> getById(Integer id) {
        Optional<Bet> bet = Optional.empty();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            bet = Optional.of(session.find(Bet.class, id));
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bet;
    }

    @Override
    public void add(Bet bet) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(bet);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Bet bet) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(bet);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Bet bet = session.load(Bet.class, id);
            session.delete(bet);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
