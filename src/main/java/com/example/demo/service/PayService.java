package com.example.demo.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.controller.PaymentRequestDTO;
import com.example.demo.model.CBSRequest;
import com.example.demo.model.CBSResponse;
import com.example.demo.model.Payment;
import com.example.demo.model.PaymentResponseDTO;
import com.example.demo.repository.PaymentRepository;

@Service
public class PayService {
	
	@Autowired
	PaymentRepository repo;
	
	
	private RestTemplate restTemplate;
	
	public PayService(RestTemplate restTemplate) {
		this.restTemplate=restTemplate;
	}
	
	public PaymentResponseDTO processPayment(PaymentRequestDTO paymentRequestDTO) {
		
		Payment payment=new Payment();
		PaymentResponseDTO resposne=new PaymentResponseDTO();

		payment.setAmount(paymentRequestDTO.getAmount());
		payment.setORDER_UUID(paymentRequestDTO.getOrderId());
		String payUUID = UUID.randomUUID().toString();
		payment.setPaymentUUID(payUUID);

		payment.setStatus("Created");
		CBSResponse cbsResponse;
		Long updatedRecord=repo.processPayment(payment);
		if(updatedRecord>0) {
			CBSRequest cbsRequest=new CBSRequest();
			cbsRequest.setAccountNumber("123654789");
			cbsRequest.setAmount(paymentRequestDTO.getAmount());
			cbsRequest.setReferenceNo(payUUID);
			cbsResponse = restTemplate.postForObject("http://localhost:9191/account-service/account/processTrn",
					cbsRequest, CBSResponse.class);
			String status=cbsResponse.isPaymentStatus()==true?"DEBITED":"Failed";
			repo.updatePaymentStatus(payUUID,status);
			resposne.setPaymentId(payUUID);
			resposne.setPayStatus(status);
			resposne.setPaymentStatus(cbsResponse.isPaymentStatus()==true?"Success":"Failed");
			return resposne;
		}
		else {
			
		}
		resposne.setPaymentStatus("Failed");
		return resposne;
	}
}
