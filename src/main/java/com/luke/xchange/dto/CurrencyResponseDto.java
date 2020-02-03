package com.luke.xchange.dto;

public class CurrencyResponseDto {
	private String name;
	private Double rate;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
}
