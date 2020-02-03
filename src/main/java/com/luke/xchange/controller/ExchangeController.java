package com.luke.xchange.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luke.xchange.dto.ApiResponseDto;
import com.luke.xchange.dto.CurrencyRequestDto;
import com.luke.xchange.dto.CurrencyResponseDto;
import com.luke.xchange.dto.ExchangeRequestDto;
import com.luke.xchange.dto.ExchangeResponseDto;
import com.luke.xchange.exception.BadRequestException;
import com.luke.xchange.exception.BadRequestException.BadRequestExceptionError;
import com.luke.xchange.exception.BaseCustomException;
import com.luke.xchange.model.ExchangeCurrency;
import com.luke.xchange.service.ExchangeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/currencies")
@Api(value="Currency Exchange System")
public class ExchangeController {
	
	@Autowired
	private ExchangeService exchangeService;
	
	@ApiOperation(value = "Create currency", response = ApiResponseDto.class)
	@PostMapping("")
	public ResponseEntity<?> createCurrency(@RequestBody CurrencyRequestDto requestDto) throws BaseCustomException {
		try {
			ExchangeCurrency exchangeCurrency = exchangeService.saveCurrency(requestDto);
			CurrencyResponseDto responseDto = exchangeService.convertExchangeCurrencyToCurrencyResponseDto(exchangeCurrency);
			return ResponseEntity.ok(new ApiResponseDto(true, "currency created successfully", responseDto));
		} catch (Exception ex) {
			if (ex instanceof JpaSystemException) {
				throw new BadRequestException(BadRequestExceptionError.INVALID_VALUE_ERROR);
			}
			throw ex;
		}
	}
	
	@ApiOperation(value = "View a list of available currencies", response = ApiResponseDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Resource Not Found"),
	})
	@GetMapping("")
	public ResponseEntity<?> getCurrencies() {
		List<CurrencyResponseDto> results = exchangeService.getAllCurrencies();
		return ResponseEntity.ok(new ApiResponseDto(true, "", results));
	}
	
	@ApiOperation(value = "Perform Exchange", response = ApiResponseDto.class)
	@PostMapping("/{name}/exchange")
	public ResponseEntity<?> exchangeCurrency(@PathVariable String name, @RequestBody ExchangeRequestDto requestDto) throws BaseCustomException {
		ExchangeResponseDto result = exchangeService.exchangeCurrency(name, requestDto);
		return ResponseEntity.ok(new ApiResponseDto(true, "", result));
	}
}
