package com.luke.xchange.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	
	@Id @GeneratedValue
	private Integer id;
	
	private Integer exchangeCurrencyId;
	
	@Column(precision = 15, scale = 2)
	private Double exchangedAmount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExchangeCurrencyId() {
		return exchangeCurrencyId;
	}

	public void setExchangeCurrencyId(Integer exchangeCurrencyId) {
		this.exchangeCurrencyId = exchangeCurrencyId;
	}

	public Double getExchangedAmount() {
		return exchangedAmount;
	}

	public void setExchangedAmount(Double exchangedAmount) {
		this.exchangedAmount = exchangedAmount;
	}
	
}
