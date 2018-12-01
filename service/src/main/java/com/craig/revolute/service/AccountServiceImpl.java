package com.craig.revolute.service;

import com.craig.revolute.data.model.Account;
import com.craig.revolute.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountsService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> findById(Long id) {

        return accountRepository.findById(id);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account update(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> delete(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        account.ifPresent(accountRepository::delete);
        return account;
    }
}
