package com.devsupra.hrpayroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsupra.hrpayroll.entities.Payment;
import com.devsupra.hrpayroll.entities.Worker;
import com.devsupra.hrpayroll.feignclients.WorkFeignClients;


@Service
public class PaymentService {
	
   @Autowired
   private WorkFeignClients wfg;

	public Payment getPayment(long workerId, int days) {
        Worker worker = wfg.findWorkerById(workerId).getBody();
		return new Payment(worker.getName(), worker.getDailyIncome(),days);
	}
}
