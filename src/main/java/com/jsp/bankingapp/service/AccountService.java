package com.jsp.bankingapp.service;

import java.util.List;

import com.jsp.bankingapp.dto.AccountDto;


public interface AccountService {
	AccountDto createAccount(AccountDto accountDto);
	AccountDto getAccount(long id);
	AccountDto deposit(long id,double amount);
	AccountDto withdraw(long id,double amount);
	List<AccountDto> getAllAccount();
	void deleteAccount(long id);
}
