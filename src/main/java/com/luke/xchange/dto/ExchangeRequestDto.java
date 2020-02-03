package com.luke.xchange.dto;

public class ExchangeRequestDto {
	private String sourceCurrency;
	private String destinationCurrency;
	private Double amount;
	public String getSourceCurrency() {
		return sourceCurrency;
	}
	public void setSourceCurrency(String sourceCurrency) {
		this.sourceCurrency = sourceCurrency;
	}
	public String getDestinationCurrency() {
		return destinationCurrency;
	}
	public void setDestinationCurrency(String destinationCurrency) {
		this.destinationCurrency = destinationCurrency;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
