package com.fageniuscode.ps.api.controller;

import com.fageniuscode.ps.api.entity.Payment;
import com.fageniuscode.ps.api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService service;

    @PostMapping("/doPayment")
    public Payment doPayment(@RequestBody Payment payment){
        return service.doPayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment findPaymentHistoriqueByOrderId(@PathVariable int orderId){
        return service.findPaymentHistoriqueByOrderId(orderId);
    }

}
