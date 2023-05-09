package com.wym.springcloud.service;

import com.wym.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
	public int create(Payment payment);

	public Payment getPaymentById(@Param("id") Long id);
}
