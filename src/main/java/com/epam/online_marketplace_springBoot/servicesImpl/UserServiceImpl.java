package com.epam.online_marketplace_springBoot.servicesImpl;


import com.epam.online_marketplace_springBoot.dao.UserDao;
import com.epam.online_marketplace_springBoot.entities.Role;
import com.epam.online_marketplace_springBoot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final com.epam.online_marketplace_springBoot.entities.User userEntity = userDAO.getByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        return User.withUsername(userEntity.getUsername()).password(userEntity.getPassword()).authorities(userEntity.getRole().toString()).build();
    }

    @Override
    public boolean addUser(com.epam.online_marketplace_springBoot.entities.User user) {
        com.epam.online_marketplace_springBoot.entities.User userFromDB = userDAO.getByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        user.setRole(Role.ROLE_USER);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDAO.add(user);
        return true;
    }
}
