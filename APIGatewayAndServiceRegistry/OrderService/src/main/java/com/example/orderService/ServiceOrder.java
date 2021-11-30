package com.example.orderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceOrder {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public TransactionResponse saveOrder(TransactionRequest request){
        String response = "";
        Order order = request.getOrder();
        CommonPayment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());

        // so in above we actually built the payment object. we wanted to add the orderId and Amount in our Payment object.

        // make a rest call
        // method postForObject(url, request, response)

        CommonPayment paymentResponse = restTemplate.postForObject("http://PAYMENT-SERVICE/payment/makePayment", payment, CommonPayment.class);
        // in above we are making a post call because in payment service, in controller we have post call for makePayment
        // and here we have hard coded url. but when we will build service registry then we do not have to write host and port.

        response = (paymentResponse.getPaymentStatus().equals("success")) ? "payment processing successful and order placed":"there is a failure in " +
                "payment API, order added to cart";
        // as of now we are giving this string message manually. once we integrate hystrix then we can add the fault tolerant.
        // we can customize the error message there.
        orderRepository.save(order);
        return new TransactionResponse(order, paymentResponse.getTransactionId(), paymentResponse.getAmount(), response);
    }
}
