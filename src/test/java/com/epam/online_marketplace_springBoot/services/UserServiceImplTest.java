package com.epam.online_marketplace_springBoot.services;

import com.epam.online_marketplace_springBoot.dao.UserDao;
import com.epam.online_marketplace_springBoot.entities.Role;
import com.epam.online_marketplace_springBoot.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    @MockBean
    private UserDao userDAO;

    private String name = "TestName";
    private String surname = "TestSurname";
    private String username = "Test";
    private String password = "TestPassword";

    @Test
    void loadUserByUsername() {
        User user = new User(name, surname, username, password, Role.ROLE_USER);
        Mockito.doReturn(user)
                .when(userDAO).getByUsername(username);
        assertNotNull(userService.loadUserByUsername(username));
    }

    @Test
    void loadByUsernameFailTest(){
        Mockito.doReturn(null)
                .when(userDAO).getByUsername(username);

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername(username);
        });
    }

    @Test
    void addUser() {
        User user = new User();
        user.setPassword(password);
        boolean isUserCreated = userService.addUser(user);

        assertTrue(isUserCreated);
        assertNotNull(user.getRole());
        assertNotEquals(password, user.getPassword());
        Mockito.verify(userDAO, Mockito.times(1)).add(user);

    }

    @Test
    public void addUserFailTest() {
        User user = new User();
        user.setUsername(username);
        Mockito.doReturn(new User())
                .when(userDAO).getByUsername(username);
        boolean isUserCreated = userService.addUser(user);
        assertFalse(isUserCreated);
        Mockito.verify(userDAO, Mockito.times(0)).add(user);
    }
}