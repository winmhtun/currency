package com.luke.xchange.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.luke.xchange.dto.CurrencyRequestDto;
import com.luke.xchange.dto.CurrencyResponseDto;
import com.luke.xchange.dto.ExchangeRequestDto;
import com.luke.xchange.dto.ExchangeResponseDto;
import com.luke.xchange.exception.AppException;
import com.luke.xchange.exception.AppException.AppExceptionError;
import com.luke.xchange.exception.BadRequestException;
import com.luke.xchange.exception.BaseCustomException;
import com.luke.xchange.exception.BadRequestException.BadRequestExceptionError;
import com.luke.xchange.exception.ResourceNotFoundException;
import com.luke.xchange.exception.ResourceNotFoundException.ResourceNotFoundExceptionError;
import com.luke.xchange.model.ExchangeCurrency;
import com.luke.xchange.repository.ExchangeRepository;

@Service
@Transactional
public class ExchangeService {
	
	@Autowired
	private ExchangeRepository exchangeRepository;
	
	@Autowired
	private TransactionService transactionService;
	
	public List<CurrencyResponseDto> getAllCurrencies() {
		List<ExchangeCurrency> exchangeCurrencies = exchangeRepository.findAll();
		if (!CollectionUtils.isEmpty(exchangeCurrencies)) {
			List<CurrencyResponseDto> results = new ArrayList<CurrencyResponseDto>();
			for (ExchangeCurrency exchangeCurrency: exchangeCurrencies) {
				results.add(convertExchangeCurrencyToCurrencyResponseDto(exchangeCurrency));
			}
			return results;
		}
		return null;
	}
	
	public ExchangeResponseDto exchangeCurrency(String name, ExchangeRequestDto requestDto) throws BaseCustomException {
		ExchangeCurrency exchangeCurrency = exchangeRepository.findByName(name);
		
		if (exchangeCurrency == null) {
			throw new ResourceNotFoundException(ResourceNotFoundExceptionError.CURRENCY_NOT_FOUND_ERROR);
		}
		
		BigDecimal exchangedAmount = exchange(requestDto.getAmount(), exchangeCurrency.getExchangeRate());
		
		if (exchangedAmount.doubleValue() > 0) {
			transactionService.saveTransaction(exchangeCurrency.getId(), exchangedAmount);
		}
		
		return createExchangeResponseDto(requestDto.getSourceCurrency() + " to " + exchangeCurrency.getName(), exchangedAmount.doubleValue());
	}
	
	public ExchangeResponseDto createExchangeResponseDto(String name, Double amount) {
		ExchangeResponseDto dto = new ExchangeResponseDto();
		dto.setAmount(amount);
		dto.setExchangeName(name);
		return dto;
	}
	
	private BigDecimal exchange(Double amount, Double rate) throws BaseCustomException {
		if (amount == null || rate == null) {
			throw new BadRequestException(BadRequestExceptionError.EMPTY_VALUE_ERROR);
		}
		
		if (amount <= 0) {
			throw new BadRequestException(BadRequestExceptionError.INVALID_VALUE_ERROR);
		}
		
		BigDecimal result = new BigDecimal(amount).multiply(new BigDecimal(rate));
		return toFive(result);
	}
	
	private BigDecimal toFive(BigDecimal result){
	    return new BigDecimal("2").multiply(result).setScale(1, RoundingMode.HALF_UP).divide(new BigDecimal("2"));
	}
	
	public CurrencyResponseDto convertExchangeCurrencyToCurrencyResponseDto(ExchangeCurrency exchangeCurrency) {
		CurrencyResponseDto dto = new CurrencyResponseDto();
		dto.setName(exchangeCurrency.getName());
		dto.setRate(exchangeCurrency.getExchangeRate());
		return dto;
	}
	
	public ExchangeCurrency saveCurrency(CurrencyRequestDto currencyRequestDto) throws BaseCustomException{
		try {
			ExchangeCurrency exchangeCurrency = new ExchangeCurrency();
			exchangeCurrency.setName(currencyRequestDto.getName());
			exchangeCurrency.setExchangeRate(currencyRequestDto.getExchangeRateBigDecimal());
			return exchangeRepository.save(exchangeCurrency);
		} catch (ConstraintViolationException | DataIntegrityViolationException ex) {
			throw new BadRequestException(BadRequestExceptionError.INVALID_VALUE_ERROR);
		} catch (Exception ex) {
			throw new AppException(AppExceptionError.INTERNAL_SERVER_ERROR);
		}
		
	}
}
