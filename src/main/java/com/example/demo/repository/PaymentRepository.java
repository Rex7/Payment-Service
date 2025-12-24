package com.example.demo.repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Payment;

@Repository
public class PaymentRepository {
	
	private JdbcTemplate jdbcTemplate;
	
	public PaymentRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	public long processPayment(Payment payment) {
		String sql = """
		        INSERT INTO PAYMENTS (
		            id,PAYMENT_UUID, order_uuid,AMOUNT, STATUS, CREATED_AT )
		             VALUES (
		            PAYMENTS_SEQ.NEXTVAL, ?, ?,?, ?,?
		        )
		        """;
	 Timestamp ts = Timestamp.valueOf(LocalDateTime.now());

		    KeyHolder keyHolder = new GeneratedKeyHolder();

		    jdbcTemplate.update(connection -> {
		        PreparedStatement ps =
		            connection.prepareStatement(sql, new String[] { "ID" });

		        ps.setString(1, payment.getPaymentUUID());
		        ps.setString(2, payment.getORDER_UUID());
		        ps.setBigDecimal(3, payment.getAmount());
		        ps.setString(4, "Iniatied");
		        ps.setTimestamp(5, ts);
		      

		        return ps;
		    }, keyHolder);

		    return keyHolder.getKey().longValue();
	

		
	}

	public void updatePaymentStatus(String payUUID, String status) {
		String sql="update payments set status =? where payment_uuid =?";
		int count=jdbcTemplate.update(sql, status,payUUID);
		System.out.println( "Payment status updated for "+ payUUID +" as "+status +"updated "+(count>0?"executed":"failed"));
		
	}

}
