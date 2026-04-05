package com.meerthika.service;

import com.meerthika.domain.PaymentMethod;
import com.meerthika.modal.PaymentOrder;
import com.meerthika.payload.dto.BookingDTO;
import com.meerthika.payload.dto.UserDTO;
import com.meerthika.payload.response.PaymentLinkResponse;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;

public interface PaymentService {

    PaymentLinkResponse createOrder(UserDTO user,
                                    BookingDTO booking,
                                    PaymentMethod paymentMethod
    );

    PaymentOrder getPaymentOrderById(Long id) throws Exception;
    PaymentOrder getPaymentOrderByPaymentId(String paymentId);

    PaymentLink createRazorpayPaymentLink(UserDTO user, Long amount, Long orderId) throws RazorpayException;
    String createStripePaymentLink(UserDTO user, Long amount, Long orderId);
}
