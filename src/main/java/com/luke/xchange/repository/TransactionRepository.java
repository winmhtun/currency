package com.luke.xchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luke.xchange.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
