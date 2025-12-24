package com.example.demo.model;

import java.math.BigDecimal;

public class Payment {
	private Long Id;
	private String paymentUUID;
	private String ORDER_UUID;
	private BigDecimal amount;
	private String status;
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getPaymentUUID() {
		return paymentUUID;
	}
	public void setPaymentUUID(String paymentUUID) {
		this.paymentUUID = paymentUUID;
	}
	public String getORDER_UUID() {
		return ORDER_UUID;
	}
	public void setORDER_UUID(String oRDER_UUID) {
		ORDER_UUID = oRDER_UUID;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreated_At() {
		return created_At;
	}
	public void setCreated_At(String created_At) {
		this.created_At = created_At;
	}
	private String created_At;
}
	