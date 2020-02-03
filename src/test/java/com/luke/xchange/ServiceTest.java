package com.luke.xchange;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.luke.xchange.dto.CurrencyResponseDto;
import com.luke.xchange.dto.ExchangeRequestDto;
import com.luke.xchange.dto.ExchangeResponseDto;
import com.luke.xchange.exception.BaseCustomException;
import com.luke.xchange.model.ExchangeCurrency;
import com.luke.xchange.repository.ExchangeRepository;
import com.luke.xchange.service.ExchangeService;
import com.luke.xchange.service.TransactionService;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {
	@Mock
	ExchangeRepository exchangeRepository;
	
	@Mock
	TransactionService transactionService;

	@InjectMocks
	ExchangeService exchangeService;

	private ExchangeCurrency sgd;

	@Before
	public void setup() {
		sgd = new ExchangeCurrency();
		sgd.setName("SGD");
		sgd.setId(1);
		sgd.setExchangeRate(BigDecimal.TEN);
	}

	@Test
	public void should_get_all_currencies() {

		List<ExchangeCurrency> currencyList = new ArrayList<>();
		currencyList.add(sgd);

		when(exchangeRepository.findAll()).thenReturn(currencyList);

		List<CurrencyResponseDto> result = exchangeService.getAllCurrencies();
		assertThat(result.stream().map(c -> c.getName()).collect(Collectors.toList()),
				org.hamcrest.Matchers.contains(sgd.getName()));
		assertThat(result.stream().map(c -> c.getRate()).collect(Collectors.toList()),
				org.hamcrest.Matchers.contains(sgd.getExchangeRate().doubleValue()));
	}

	@Test
	public void should_exchange_succeed() {
		ExchangeRequestDto requestDto = new ExchangeRequestDto();
		requestDto.setAmount(11.468);
		when(exchangeRepository.findByName(anyString())).thenReturn(sgd);
		try {
			ExchangeResponseDto responseDto = exchangeService.exchangeCurrency(sgd.getName(), requestDto);
			assertThat(responseDto.getAmount(), equalTo(114.70)); //half up
			
			requestDto.setAmount(11.467);
			responseDto = exchangeService.exchangeCurrency(sgd.getName(), requestDto);
			assertThat(responseDto.getAmount(), equalTo(114.65)); //half up
			
			
		} catch (BaseCustomException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = BaseCustomException.class)
	public void should_exchange_fail() throws BaseCustomException {
		ExchangeRequestDto requestDto = new ExchangeRequestDto();
		requestDto.setAmount(0.0);
		when(exchangeRepository.findByName(anyString())).thenReturn(sgd);
		exchangeService.exchangeCurrency(sgd.getName(), requestDto);
	}
	
}
