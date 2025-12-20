package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.PayService;

@RestController
@RequestMapping("/payment")
public class PayController {
	
	@Autowired
	PayService payService;
	
	
	@PostMapping("/pay")
	public String processPayment(@RequestBody  String payId) {
	return 	payService.processPayment(payId);
	}
	
	@PostMapping("/paymnetRefund")
	public String paymnetRefund(@RequestBody  String payId) {
	return 	"refunded";
	}

}
