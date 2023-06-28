package com.dh.msusers.service;

import com.dh.msusers.model.Bill;
import com.dh.msusers.model.User;
import com.dh.msusers.repository.KeyCloakUserRepository;
import com.dh.msusers.repository.feign.IBillsFeignClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final KeyCloakUserRepository keyCloakUserRepository;
    private final IBillsFeignClient billsFeignClient;

    public UserService(KeyCloakUserRepository keyCloakUserRepository, IBillsFeignClient billsFeignClient) {
        this.keyCloakUserRepository = keyCloakUserRepository;
        this.billsFeignClient = billsFeignClient;
    }

    public User getUserAndBills(String id) {
        try {
            User user = keyCloakUserRepository.findById(id);
            List<Bill> bills = billsFeignClient.findByCustomerId(user.getEmail());
            user.setBillList(bills);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
