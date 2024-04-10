package com.jsp.bankingapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.bankingapp.dto.AccountDto;
import com.jsp.bankingapp.entity.Account;
import com.jsp.bankingapp.service.AccountService;

import lombok.Delegate;

@RestController
@RequestMapping("/api/account")
public class accountController {
	
	private AccountService accountService;

	public accountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		return new ResponseEntity<AccountDto>(accountService.createAccount(accountDto),HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<AccountDto> getAccount(@RequestParam long id){
		return new ResponseEntity<AccountDto>(accountService.getAccount(id),HttpStatus.FOUND);
	}
	@PutMapping("/deposit")
	public ResponseEntity<AccountDto> deposit(@RequestParam long id,@RequestBody Map<String, Double> request){
		Double amount=request.get("amount");
		AccountDto accountDto=accountService.deposit(id, amount);
		return  ResponseEntity.ok(accountDto);
	}
	@PutMapping("/withdrwa")
	public ResponseEntity<AccountDto> withdrwa(@RequestParam long id,@RequestBody Map<String, Double> request){
		Double amount=request.get("amount");
		AccountDto accountDto=accountService.withdraw(id, amount);
		return  ResponseEntity.ok(accountDto);
	}
	@GetMapping("/getall")
	public ResponseEntity<List<AccountDto>> getAllAccount(){
		List<AccountDto> accountDtos=accountService.getAllAccount();
		return ResponseEntity.ok(accountDtos);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam long id){
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account Deleted Successfully!");
	}
	

}
