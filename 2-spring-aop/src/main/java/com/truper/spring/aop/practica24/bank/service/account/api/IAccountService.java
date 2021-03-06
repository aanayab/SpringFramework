package com.truper.spring.aop.practica24.bank.service.account.api;

import java.util.List;

import com.truper.spring.aop.practica24.bank.app.model.Account;

public interface IAccountService {

	void updateAccountBalance(Account account, Long amount);

	List<Account> findCustomerAccounts(Long customerId);

	void updateAccountDescription(Account account);

}
