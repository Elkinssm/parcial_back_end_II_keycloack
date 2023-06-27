package com.dh.msusers.service;

import com.dh.msusers.model.Bill;
import com.dh.msusers.model.User;
import com.dh.msusers.repository.IUserRepository;
import com.dh.msusers.repository.feign.IBillsFeignClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {

    private final IUserRepository userRepository;
    private final IBillsFeignClient billsFeignClient;

    public UserService(IUserRepository userRepository, IBillsFeignClient billsFeignClient) {
        this.userRepository = userRepository;
        this.billsFeignClient = billsFeignClient;
    }

    public User getUserAndBills(String id) {
        User user = userRepository.findById(id);
        List<Bill> bills = billsFeignClient.findByCustomerId(id);
        user.setBillList(bills);
        return user;
    }

}
