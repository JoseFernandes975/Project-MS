package com.devsupra.hrpayroll.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsupra.hrpayroll.entities.Payment;
import com.devsupra.hrpayroll.services.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {
	
	@Autowired
	private PaymentService service;

	@HystrixCommand(fallbackMethod = "showPaymentAlternative")
	@GetMapping(value = "/{workerId}/days/{days}")
	public ResponseEntity<Payment> showPayment(@PathVariable Long workerId, @PathVariable Integer days){
	Payment pay = service.getPayment(workerId, days);
	return ResponseEntity.ok(pay);
	}
	
	public ResponseEntity<Payment> showPaymentAlternative(Long workerId, Integer days){
		Payment pay = new Payment("Brann", 400.0, days);
		return ResponseEntity.ok(pay);
	}
}
