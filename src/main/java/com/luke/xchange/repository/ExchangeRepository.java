package com.luke.xchange.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luke.xchange.model.ExchangeCurrency;

@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeCurrency, Integer>{
	ExchangeCurrency findByName(String name);
}
