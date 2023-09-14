package com.fageniuscode.os.api.service;

import com.fageniuscode.os.api.common.Payment;
import com.fageniuscode.os.api.common.TransactionRequest;
import com.fageniuscode.os.api.common.TransactionResponse;
import com.fageniuscode.os.api.entity.Order;
import com.fageniuscode.os.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
    private String responde = "";
    @Autowired
    private OrderRepository repository;
    @Autowired
    private RestTemplate template;

    public TransactionResponse saveOrder(TransactionRequest request) {
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderid(order.getId());
        payment.setAmount((order.getPrice()));

        // Rest call
        Payment paymentResponse = template.postForObject("http://localhost:9191/payment/doPayment", payment, Payment.class);

        responde = paymentResponse.getPaymentStatus().equals("success")?"Payement processing successful and order placed":"There is a failure in payment api, order added to cart ";

        repository.save(order);
        return  new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), responde);
    }

    public Order saveOrder1(Order order) {
        return repository.save(order);
    }
}
