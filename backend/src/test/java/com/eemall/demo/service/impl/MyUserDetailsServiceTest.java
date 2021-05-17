package com.eemall.demo.service.impl;



import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import com.eemall.demo.model.User;
import com.eemall.demo.service.UserService;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsServiceTest {
    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @InjectMocks
    MyUserDetailsService myUserDetailsService;

    @Mock
    UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testLoadUserByUsername() {
        String email = "i@gmail.com";
        User userInDB = new User();
        userInDB.setEmail(email);
        userInDB.setPassword("AAAAAAAA");
        when(userService.findByEmail(email)).thenReturn(userInDB);

        UserDetails ud =  myUserDetailsService.loadUserByUsername(email);
        assert(ud.getUsername().equals(userInDB.getEmail()));
    }

    // user not found exception
    @Test
    public void testLoadUserByUsernameException() {
        String email = "i@gmail.com";
        when(userService.findByEmail(email)).thenReturn(null);

        thrown.expect(UsernameNotFoundException.class);
        thrown.expectMessage(email + " user not found");
        UserDetails ud =  myUserDetailsService.loadUserByUsername(email);
    }


}

