package com.sap.bookingplatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.sap.bookingplatform.data.entities.Account;
import com.sap.bookingplatform.data.entities.Address;
import com.sap.bookingplatform.data.entities.Preference;
import com.sap.bookingplatform.data.entities.Users;
import com.sap.bookingplatform.data.repositories.AccountRepository;
import com.sap.bookingplatform.data.repositories.AddressRepository;
import com.sap.bookingplatform.data.repositories.PreferenceRepository;
import com.sap.bookingplatform.data.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepo;
    @Autowired
    PreferenceRepository prefRepo;
    @Autowired
    AccountRepository accRepo;
    @Autowired
    AddressRepository addRepo;

    @Cacheable(cacheNames = "users", key = "'userList'")
    public List<Users> getAllUsers() {
        List<Users> users = userRepo.findAll();
        return users;
    }

    @Cacheable(cacheNames = "user", key = "#userId")
    public Users getUser(@PathVariable String userId) {
        return userRepo.findByUserId(userId).orElse(null);
    }

    public Users addUser(@RequestBody Users user) {
        return userRepo.save(user);
    }

    public Users addPreference(Preference preference, int id) {
        Users user = userRepo.findById(id).get();
        preference.setUser(user);
        Preference pref = prefRepo.save(preference);
        user.setPreference(pref);
        log.debug("Preference for user {} is added", user.getName());
        return userRepo.save(user);
    }

    public Preference getPreference(int id) {
        Users user = userRepo.findById(id).get();
        return user.getPreference();
    }

    public Users addAccount(Account account, int id) {
        Users user = userRepo.findById(id).get();
        account.setUser(user);
        Account acc = accRepo.save(account);
        user.setAccount(acc);
        log.debug("Account for user {} is added", user.getName());
        return userRepo.save(user);
    }

    public Account getAccount(int id) {
        Users user = userRepo.findById(id).get();
        return user.getAccount();
    }

    public Users addAAddress(Address address, int id) {
        Users user = userRepo.findById(id).get();
        address.setUser(user);
        Address acc = addRepo.save(address);
        user.setAddress(acc);
        log.debug("Address for user {} is added", user.getName());
        return userRepo.save(user);
    }

    public Address getAddress(int id) {
        Users user = userRepo.findById(id).get();
        return user.getAddress();
    }

}
