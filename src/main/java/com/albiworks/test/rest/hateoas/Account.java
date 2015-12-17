package com.albiworks.test.rest.hateoas;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Account extends ResourceSupport{

	private String accNumber;
	
	private String holder;
	
	private BigDecimal balance;

	@JsonCreator
	public Account(@JsonProperty("holder") String holder){
		this.accNumber = UUID.randomUUID().toString();
		this.holder = holder;
		this.balance = BigDecimal.ZERO;
	}
	
	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}
