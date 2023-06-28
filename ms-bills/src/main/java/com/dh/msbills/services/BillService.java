package com.dh.msbills.services;

import com.dh.msbills.models.Bill;
import com.dh.msbills.repositories.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillService {

    private final BillRepository repository;

    public List<Bill> getAllBill() {
        return repository.findAll();
    }

    public Bill findByBillId(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found with id " + id));
    }

    public List<Bill> getByEmail(String email) {
        return repository.findAllByEmail(email);
    }
}
