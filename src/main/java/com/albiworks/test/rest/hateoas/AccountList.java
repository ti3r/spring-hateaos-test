package com.albiworks.test.rest.hateoas;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class AccountList extends ResourceSupport{

	private List<Account> accounts;

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
}
