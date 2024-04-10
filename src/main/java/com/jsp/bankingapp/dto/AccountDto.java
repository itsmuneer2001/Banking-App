package com.jsp.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class AccountDto {
	private long id;
	private String accountHolderName;
	private double balance;
	

}
