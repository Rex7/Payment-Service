package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class PayService {

	
	public String processPayment(String payMode) {
		System.out.print("payment service called");
		return "Paid Successfully";
	}
}
