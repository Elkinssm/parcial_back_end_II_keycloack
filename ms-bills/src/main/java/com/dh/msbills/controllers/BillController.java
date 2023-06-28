package com.dh.msbills.controllers;

import com.dh.msbills.models.Bill;
import com.dh.msbills.services.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService service;

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Bill>> getAll() {
        return ResponseEntity.ok().body(service.getAllBill());
    }

    @GetMapping("/filter")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Bill>> getByEmail(@RequestParam String email) {
        return ResponseEntity.ok().body(service.getByEmail(email));
    }


    @PostMapping("/bills")
    @PreAuthorize("hasAnyAuthority('PROVIDER')")
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
        Bill savedBill = service.saveBill(bill);
        return ResponseEntity.ok(savedBill);
    }


    @GetMapping("/findBy")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Bill> getBillById(@RequestParam String id) {
        return ResponseEntity.ok().body(service.findByBillId(id));
    }

}
