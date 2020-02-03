package com.luke.xchange.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@MappedSuperclass
abstract class Currency implements TradingCurrency {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;
	public String name;
}

interface TradingCurrency {
	public BigDecimal getExchangeRate(Currency targetCurrency);
	public BigDecimal getExchangedAmount(Currency targetCurrency);
}

@Entity
@Table(name = "exchangeCurrency", uniqueConstraints = {
		@UniqueConstraint(columnNames = {
				"name"
		})
})
public class ExchangeCurrency extends Currency {

	@Column(precision = 8, scale = 6)
	private BigDecimal exchangeRate;
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public BigDecimal getExchangeRate(Currency targetCurrency) {
		return this.exchangeRate;
	}
	
	public Double getExchangeRate() {
		return this.exchangeRate.doubleValue();
	}
	
	public void setExchangeRate(BigDecimal rate) {
		this.exchangeRate = rate;
	}

	@Override
	public BigDecimal getExchangedAmount(Currency targetCurrency) {
		// TODO Auto-generated method stub
		return null;
	}

}
