package com.luke.xchange;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.PersistenceException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.luke.xchange.model.ExchangeCurrency;
import com.luke.xchange.repository.ExchangeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class RepositoryTest {
	@Autowired
	TestEntityManager entityManager;

	@Autowired
	ExchangeRepository exchangeRepository;

	@Test
	public void should_save_currency() {
		ExchangeCurrency currency = new ExchangeCurrency();
		currency.setName("SGD");
		currency = entityManager.persistAndFlush(currency);
		assertThat(exchangeRepository.findByName("SGD")).isEqualTo(currency);
	}

	@Test(expected = PersistenceException.class)
	public void should_not_save_duplicate_currency() {
		ExchangeCurrency currency = new ExchangeCurrency();
		currency.setName("SGD");
		currency = entityManager.persistAndFlush(currency);

		ExchangeCurrency duplicate = new ExchangeCurrency();
		duplicate.setName("SGD");
		currency = entityManager.persistAndFlush(duplicate);
	}
}
