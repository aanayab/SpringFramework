package com.truper.spring.aop.practica24.bank.dao.account.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.truper.spring.aop.practica24.bank.app.model.Account;
import com.truper.spring.aop.practica24.bank.dao.account.api.IAccountDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class AccountDAO implements IAccountDAO {

	@Override
	public List<Account> findByCustomerId(Long customerId) {
		
		log.info("Finding Accounts ...");

		List<Account> result = new ArrayList<>();
		result.add(Account.builder().accountNumber("000001")
				.accountDescription("Account 1").build());
		return result;
	}

	@Override
	public void updateBalance(Account account, Long amount) {
		
		log.info("Updating Account Balance ...");
	}

	@Override
	public void updateDescription(Account account) {
		
		log.info("Updating Account Description ...");
	}

}
