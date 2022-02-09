package com.truper.spring.aop.practica24.bank.web.account.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.truper.spring.aop.practica24.bank.app.model.Account;
import com.truper.spring.aop.practica24.bank.service.account.api.IAccountService;
import com.truper.spring.aop.practica24.bank.web.account.api.IAccountWebView;

@Controller
public class AccountWebView implements IAccountWebView {

	@Autowired
	private IAccountService accountService;

	@Override
	public void showAccountsFromCustomerId(Long customerId) {

		List<Account> accountList = accountService.findCustomerAccounts(customerId);

		accountList.stream().forEach(System.out::println);
	}

	@Override
	public void processFormUpdateBalance(Account account, Long amount) {

		accountService.updateAccountBalance(account, amount);
	}

	@Override
	public void processFormUpdateDescription(Account account) {

		accountService.updateAccountDescription(account);
	}

}
