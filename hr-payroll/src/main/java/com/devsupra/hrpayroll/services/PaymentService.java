package com.devsupra.hrpayroll.services;

import org.springframework.stereotype.Service;

import com.devsupra.hrpayroll.entities.Payment;

@Service
public class PaymentService {

	public Payment getPayment(Long workId, Integer days) {
		return new Payment("Bob",200.0,days);
	}
}
