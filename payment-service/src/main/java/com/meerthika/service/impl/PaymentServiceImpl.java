package com.meerthika.service.impl;

import com.meerthika.domain.PaymentMethod;
import com.meerthika.modal.PaymentOrder;
import com.meerthika.payload.dto.BookingDTO;
import com.meerthika.payload.dto.UserDTO;
import com.meerthika.payload.response.PaymentLinkResponse;
import com.meerthika.repository.PaymentOrderRepository;
import com.meerthika.service.PaymentService;
import com.razorpay.PaymentLink;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentOrderRepository paymentOrderRepository;

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @Value("${razorpay.api.key}")
    private String razorpayApiKey;

    @Value("${razorpay.api.secret}")
    private String razorpayApiSecret;

    @Override
    public PaymentLinkResponse createOrder(UserDTO user, BookingDTO booking, PaymentMethod paymentMethod) {

        Long amount = (long) booking.getTotalPrice();

        PaymentOrder order = new PaymentOrder();
        order.setAmount(amount);
        order.setPaymentMethod(paymentMethod);
        order.setBookingId(booking.getId());
        order.setSalonId(booking.getSalonId());

        PaymentOrder savedOrder = paymentOrderRepository.save(order);

        PaymentLinkResponse paymentLinkResponse = new PaymentLinkResponse();

        if(paymentMethod.equals(PaymentMethod.RAZORPAY)){
            PaymentLink payment= createRazorpayPaymentLink(
                    user, savedOrder.getAmount(), savedOrder.getId()
            );

            String paymentUrl = payment.get("short_url");
            String paymentUrlId = payment.get("id");

            paymentLinkResponse.setPayment_link_url(paymentUrl);
            savedOrder.setPaymentLinkId(paymentUrlId);

            paymentOrderRepository.save(savedOrder);
        }else{
            String paymentUrl= createStripePaymentLink(
                    user, savedOrder.getAmount(), savedOrder.getId()
            );
            paymentLinkResponse.setPayment_link_url(paymentUrl);
        }
        return paymentLinkResponse;
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) throws Exception{
        PaymentOrder paymentOrder = paymentOrderRepository.findById(id).orElse(null);
        if(paymentOrder == null){
            throw new Exception("payment order not found");
        }
        return paymentOrder;
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentId) {
        return null;
    }

    @Override
    public PaymentLink createRazorpayPaymentLink(UserDTO user, Long amount, Long orderId) {
        return null;
    }

    @Override
    public String createStripePaymentLink(UserDTO user, Long amount, Long orderId) {
        return "";
    }
}
