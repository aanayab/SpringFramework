package com.truper.spring.aop.practica24.bank.service.account.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truper.spring.aop.practica24.bank.aop.StopWatchProfiler;
import com.truper.spring.aop.practica24.bank.app.model.Account;
import com.truper.spring.aop.practica24.bank.dao.account.api.IAccountDAO;
import com.truper.spring.aop.practica24.bank.service.account.api.IAccountService;

import lombok.SneakyThrows;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private IAccountDAO accountDAO;

	@Override
	public void updateAccountBalance(Account account, Long amount) {

		accountDAO.updateBalance(account, amount);
	}

	@Override
	@StopWatchProfiler
	@SneakyThrows
	public List<Account> findCustomerAccounts(Long customerId) {

		Thread.sleep(600);
		return accountDAO.findByCustomerId(customerId);
	}

	@Override
	public void updateAccountDescription(Account account) {

		accountDAO.updateDescription(account);
	}

}
