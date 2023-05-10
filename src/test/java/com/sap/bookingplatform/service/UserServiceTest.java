package com.sap.bookingplatform.service;

import com.sap.bookingplatform.data.entities.Account;
import com.sap.bookingplatform.data.entities.Address;
import com.sap.bookingplatform.data.entities.Preference;
import com.sap.bookingplatform.data.entities.Users;
import com.sap.bookingplatform.data.repositories.AccountRepository;
import com.sap.bookingplatform.data.repositories.AddressRepository;
import com.sap.bookingplatform.data.repositories.PreferenceRepository;
import com.sap.bookingplatform.data.repositories.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private PreferenceRepository preferenceRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#getAllUsers()}
     */
    @Test
    void testGetAllUsers() {
        assertNotNull(userService.getAllUsers());
    }

    /**
     * Method under test: {@link UserService#getUser(String)}
     */
    @Test
    void testGetUser() {
        Users user = new Users();
        when(userRepository.findByUserId(anyString())).thenReturn(Optional.of(user));
        assertNotNull(userService.getUser("user1"));
    }

    /**
     * Method under test: {@link UserService#addUser(Users)}
     */
    @Test
    void testAddUser() {
        Users user = new Users();
        when(userRepository.save(user)).thenReturn(user);
        assertNotNull(userService.addUser(user));
    }

    /**
     * Method under test: {@link UserService#addPreference(Preference, int)}
     */
    @Test
    void testAddPreference() {
        Users user = new Users();
        user.setName("name");
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        Preference pref = new Preference();
        when(preferenceRepository.save(any())).thenReturn(pref);
        when(userRepository.save(user)).thenReturn(user);
        assertNotNull(userService.addPreference(new Preference(), 1));
    }

    /**
     * Method under test: {@link UserService#getPreference(int)}
     */
    @Test
    void testGetPreference() {
        Users user = new Users();
        Preference pref = new Preference();
        user.setPreference(pref);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        assertNotNull(userService.getPreference(1));
    }

    /**
     * Method under test: {@link UserService#addAccount(Account, int)}
     */
    @Test
    void testAddAccount() {
        Users user = new Users();
        user.setName("name");
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        Account pref = new Account();
        when(accountRepository.save(any())).thenReturn(pref);
        when(userRepository.save(user)).thenReturn(user);
        assertNotNull(userService.addAccount(new Account(), 1));
    }

    /**
     * Method under test: {@link UserService#getAccount(int)}
     */
    @Test
    void testGetAccount() {
        Users user = new Users();
        Account pref = new Account();
        user.setAccount(pref);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        assertNotNull(userService.getAccount(1));
    }

    /**
     * Method under test: {@link UserService#addAAddress(Address, int)}
     */
    @Test
    void testAddAAddress() {
        Users user = new Users();
        user.setName("name");
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        Address pref = new Address();
        when(addressRepository.save(any())).thenReturn(pref);
        when(userRepository.save(user)).thenReturn(user);
        assertNotNull(userService.addAAddress(new Address(), 1));
    }

    /**
     * Method under test: {@link UserService#getAddress(int)}
     */
    @Test
    void testGetAddress() {
        Users user = new Users();
        Address pref = new Address();
        user.setAddress(pref);
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        assertNotNull(userService.getAddress(1));
    }
}

