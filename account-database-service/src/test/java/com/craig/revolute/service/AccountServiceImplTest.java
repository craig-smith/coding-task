package com.craig.revolute.service;

import com.craig.revolute.data.model.Account;
import com.craig.revolute.data.model.Address;
import com.craig.revolute.data.model.Owner;
import com.craig.revolute.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    private AccountsService objectUnderTest;

    @Before
    public void setUp() {
        objectUnderTest = new AccountServiceImpl(accountRepository);

    }

    @Test
    public void shouldFindById() {
        //given
        when(accountRepository.findById(1L)).thenReturn(Optional.of(getDefaultData()));

        //when
        Optional<Account> account = objectUnderTest.findById(1L);

        //then
        verify(accountRepository, times(1)).findById(1L);
        assertThat(account.isPresent()).isTrue();
    }

    @Test
    public void shouldSave() {
        //given
        Account defaultData = getDefaultData();
        when(accountRepository.save(defaultData)).thenReturn(defaultData);

        //when
        Account account = objectUnderTest.save(defaultData);

        //then
        verify(accountRepository, times(1)).save(defaultData);
        assertThat(account).isNotNull();
    }

    @Test
    public void shouldUpdate() {
        //given
        Account defaultData = getDefaultData();
        when(accountRepository.save(defaultData)).thenReturn(defaultData);

        //when
        Account account = objectUnderTest.update(defaultData);

        //then
        verify(accountRepository, times(1)).save(defaultData);
        assertThat(account).isNotNull();
    }

    @Test
    public void shouldDelete() {
        //given
        Account defaultData = getDefaultData();
        when(accountRepository.findById(1L)).thenReturn(Optional.of(defaultData));

        //when
        Optional<Account> account = objectUnderTest.delete(1L);

        //then
        verify(accountRepository, times(1)).findById(1L);
        verify(accountRepository, times(1)).delete(defaultData);
        assertThat(account.isPresent()).isTrue();
    }

    private Account getDefaultData() {
        Address address = new Address(1L, "country", "city", "street");
        Owner owner = new Owner(1L, "firstName", "lastName", "1234", address);
        Account account = new Account(1L, owner, Collections.emptyMap());
        return account;
    }

}