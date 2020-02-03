package com.luke.xchange.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CurrencyRequestDto {
	private String name;
	private Double exchangeRate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	
	@JsonIgnore
	public BigDecimal getExchangeRateBigDecimal() {
		if (this.exchangeRate != null) {
			return new BigDecimal(this.exchangeRate);
		}
		return null;
	}
}
