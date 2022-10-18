package com.devsupra.hrpayroll.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsupra.hrpayroll.entities.Payment;
import com.devsupra.hrpayroll.services.PaymentService;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {
	
	@Autowired
	private PaymentService service;

	@GetMapping(value = "/{workerId}/days/{days}")
	public ResponseEntity<Payment> showPayment(@PathVariable Long workerId, @PathVariable Integer days){
	Payment pay = service.getPayment(workerId, days);
	return ResponseEntity.ok(pay);
	}
}
