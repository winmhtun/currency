package com.luke.xchange.service;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luke.xchange.model.Transaction;
import com.luke.xchange.repository.TransactionRepository;

@Service
@Transactional
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;
	
	public Transaction saveTransaction(Integer exchangeId, BigDecimal exchangedAmount) {
		Transaction transaction = new Transaction();
		transaction.setExchangeCurrencyId(exchangeId);
		transaction.setExchangedAmount(exchangedAmount.doubleValue());
		return transactionRepository.save(transaction);
	}
}
