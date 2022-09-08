package com.example.sub_authorization.service;

import com.example.sub_authorization.Exception.UserNotFoundException;
import com.example.sub_authorization.dao.UserDao;
import com.example.sub_authorization.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.lenient;

@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {
    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserService userService;


    @BeforeEach
    public void setup() throws UserNotFoundException {
        MockitoAnnotations.initMocks(this);
        User targetUser = User.builder().id(1).username("admin").password("admin").role("admin").build();
        User fakeUser1 = User.builder().id(2).username("user1").password("user1").role("user").build();
        User fakeUser2 = User.builder().id(3).username("user2").password("user2").role("user").build();
        lenient().when(userDao.getUserByUsername("admin")).thenReturn(targetUser);
    }
    @Test
    void getUserByUsername() {
        String username = "admin";
        String fakeUsername1 = "ierh";
        String fakeUsername2 = "amov";
        User targetUser = User.builder().id(1).username("admin").password("admin").role("admin").build();
        assertEquals(targetUser, userService.getUserByUsername(username));
        assertEquals(targetUser, userService.getUserByUsername("ierh"));
    }
}