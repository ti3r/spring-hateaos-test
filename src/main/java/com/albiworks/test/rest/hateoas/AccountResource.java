package com.albiworks.test.rest.hateoas;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("accounts")
@RequestMapping("/accounts")
public class AccountResource {

	private static Map<String,Account> accounts = new HashMap<>();
	
	/* Functional Interfaces to manage account operations */
	private BiConsumer<Account, String> substract = (Account a, String qtty)->{
		BigDecimal d = new BigDecimal(qtty);
		a.setBalance(a.getBalance().subtract(d));
	};
	
	private BiConsumer<Account, String> deposit = (Account a, String qtty)->{
		BigDecimal d = new BigDecimal(qtty);
		a.setBalance(a.getBalance().add(d));
	};
	
	/* Request methods */
	@RequestMapping(method=GET)
	public ResponseEntity<AccountList> list(){
		List<Account> accs = accounts.entrySet().stream().map(entry->{return entry.getValue();})
		.collect(Collectors.toList());
		
		AccountList list = new AccountList();
		list.setAccounts(accs);
		list.add(linkTo(methodOn(AccountResource.class).list()).withSelfRel());
		list.add(linkTo(methodOn(AccountResource.class).createAccount("{holder}")).withRel("create"));
		
		return ResponseEntity.ok(list);
	}
	
	
	@RequestMapping(path="/create", method=POST)
	public ResponseEntity<Account> createAccount(@RequestBody String holder){
		Account a = new Account(holder);
		accounts.put(a.getAccNumber(), a);
		a.add(linkTo(methodOn(AccountResource.class,a.getAccNumber()).getAccount(a.getAccNumber())).withSelfRel());
		a.add(linkTo(methodOn(AccountResource.class).list()).withRel("accounts"));
		a.add(linkTo(methodOn(AccountResource.class,a.getAccNumber()).deposit(a.getAccNumber(), "0")).withRel("deposit"));
		a.add(linkTo(methodOn(AccountResource.class,a.getAccNumber()).withdrawal(a.getAccNumber(), "0")).withRel("withdrawal"));
		return ResponseEntity.ok(a);
	}
	
	@RequestMapping(path="/{accNumber}/deposit", method=POST, consumes=TEXT_PLAIN_VALUE)
	public ResponseEntity<Account> deposit(@PathVariable("accNumber")String accNumber, @RequestBody String money){
		return Optional.ofNullable(accounts.get(accNumber)).map(account->{
						deposit.accept(account, money);
						return ResponseEntity.accepted().body(account);
					}
				).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@RequestMapping(path="/{accNumber}/withdrawal", method = POST, consumes=TEXT_PLAIN_VALUE)
	public ResponseEntity<Account> withdrawal(@PathVariable("accNumber")String accNumber, @RequestBody String money){
		return Optional.ofNullable(accounts.get(accNumber)).map(account->{
			substract.accept(account, money);
			return ResponseEntity.accepted().body(account);
		}
	).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	
	@RequestMapping(path="/{accNumber}", method=GET)
	public ResponseEntity<Account> getAccount(@PathVariable("accNumber") String accNumber){
		return Optional.ofNullable(accounts.get(accNumber)).map(account->{
			return ResponseEntity.ok(account);
		}).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
}
