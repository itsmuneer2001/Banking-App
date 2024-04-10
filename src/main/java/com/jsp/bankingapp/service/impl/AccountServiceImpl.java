package com.jsp.bankingapp.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jsp.bankingapp.dto.AccountDto;
import com.jsp.bankingapp.entity.Account;
import com.jsp.bankingapp.mapper.AccountMapper;
import com.jsp.bankingapp.repo.AccountRepo;
import com.jsp.bankingapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepo accountRepo;

	public AccountServiceImpl(AccountRepo accountRepo) {
		super();
		this.accountRepo = accountRepo;
	}

	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account saveAccount = accountRepo.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
	}

	@Override
	public AccountDto getAccount(long id) {
		Account account = accountRepo.findById(id).get();
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(long id, double amount) {
		Account account = accountRepo.findById(id).get();
		double total = account.getBalance() + amount;
		account.setBalance(total);
		Account saveAccount = accountRepo.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
	}

	@Override
	public AccountDto withdraw(long id, double amount) {
		Account account = accountRepo.findById(id).get();
		if (account.getBalance() < amount) {
			throw new RuntimeException("insuffient amount");
		}
		double toatl = account.getBalance() - amount;
		account.setBalance(toatl);
		Account saveAccount = accountRepo.save(account);
		return AccountMapper.mapToAccountDto(saveAccount);
	}
	
	@Override
	public List<AccountDto> getAllAccount() {
		List<Account> accounts = accountRepo.findAll();
		//convert list of Account TO list of AccountDto
		return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
		.collect(Collectors.toList());
		
	}

	@Override
	public void deleteAccount(long id) {
		 accountRepo.deleteById(id);
		
		
	}

}
