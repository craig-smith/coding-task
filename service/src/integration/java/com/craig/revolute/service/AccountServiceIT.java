package com.craig.revolute.service;

import com.craig.revolute.AccountDatabaseApplication;
import com.craig.revolute.data.model.Account;
import com.craig.revolute.data.model.Currency;
import com.craig.revolute.data.model.dto.AccountDTO;
import com.craig.revolute.data.model.dto.AddressDTO;
import com.craig.revolute.data.model.dto.OwnerDTO;
import com.craig.revolute.repository.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(AccountDatabaseApplication.class)
public class AccountServiceIT {

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Before
    public void setUp() {
        accountRepository.deleteAll();
    }

    @Test
    public void shouldSave() {
        //given
        Account defaultData = getDefaultData();

        //when
        Account account = accountsService.save(defaultData);

        //then
        assertThat(account).isNotNull();
        assertThat(defaultData).isEqualToComparingFieldByField(account);

        Account accountFromRepo = accountRepository.getOne(defaultData.getId());
        assertThat(accountFromRepo).isNotNull();
    }

    @Test
    public void shouldFindById() {
        //given
        Account defaultData = getDefaultData();

        //when
        Optional<Account> account = accountsService.findById(defaultData.getId());

        //then
        assertThat(account.isPresent()).isTrue();
        assertThat(defaultData).isEqualToComparingFieldByField(account.get());
    }

    @Test
    public void shouldUpdate() {
        //given
        Account defaultData = getDefaultData();

        //when
        AccountDTO.Builder builder = getDefaultDTO();
        builder.money(Currency.EUR, 12000D);
        Account account = accountsService.update(objectMapper.convertValue(builder.build(), Account.class));

        //then
        assertThat(account.getMonies().size()).isEqualTo(2);
        Account accountFromDB = accountRepository.getOne(defaultData.getId());
        assertThat(accountFromDB.getMonies().size()).isEqualTo(2);

    }

    @Test
    public void shouldDelete() {
        //given
        Account defaultData = getDefaultData();

        //when
        Optional<Account> account = accountsService.delete(defaultData.getId());

        //then
        assertThat(account.isPresent()).isTrue();

        Optional<Account> accountFromRepo = accountRepository.findById(defaultData.getId());
        assertThat(accountFromRepo.isPresent()).isFalse();

    }

    private AccountDTO.Builder getDefaultDTO() {
        Map<Currency, Double> monies = new HashMap<>();
        monies.put(Currency.USD, 10000.0);
        AccountDTO.Builder accountDTO = new AccountDTO.Builder()
                .id(1L)
                .owner(new OwnerDTO.Builder()
                        .id(1L)
                        .firstName("firstname")
                        .lastName("lastName")
                        .phoneNumber("1234")
                        .address(new AddressDTO.Builder()
                                .id(1L)
                                .city("city")
                                .country("country")
                                .street("street")
                                .build())
                        .build())
                .monies(monies);

        return accountDTO;
    }

    private Account getDefaultData() {
        Account account = objectMapper.convertValue(getDefaultDTO().build(), Account.class);
        return accountRepository.save(account);
    }
}
