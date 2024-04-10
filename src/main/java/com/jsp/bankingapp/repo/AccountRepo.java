package com.jsp.bankingapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.bankingapp.entity.Account;

public interface AccountRepo extends JpaRepository<Account, Long>{

}
