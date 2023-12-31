package com.fageniuscode.os.api.service;

import com.fageniuscode.os.api.common.Payment;
import com.fageniuscode.os.api.common.TransactionRequest;
import com.fageniuscode.os.api.common.TransactionResponse;
import com.fageniuscode.os.api.entity.Order;
import com.fageniuscode.os.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RefreshScope
public class OrderService {
    private String responde = "";
    @Autowired
    private OrderRepository repository;
    @Autowired
    @Lazy
    private RestTemplate template;

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;

    public TransactionResponse saveOrder(TransactionRequest request) {
        Order order = request.getOrder();
        Payment payment = request.getPayment();
        payment.setOrderid(order.getId());
        payment.setAmount((order.getPrice()));

        // Rest call
        // Payment paymentResponse = template.postForObject("http://PAYMENT-SERVICE/payment/doPayment", payment, Payment.class);
        Payment paymentResponse = template.postForObject(ENDPOINT_URL, payment, Payment.class);

        responde = paymentResponse.getPaymentStatus().equals("success")?"Payement processing successful and order placed":"There is a failure in payment api, order added to cart ";

        repository.save(order);
        return  new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), responde);
    }

    public Order saveOrder1(Order order) {
        return repository.save(order);
    }
}
