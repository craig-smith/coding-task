package com.craig.revolute.service;

import com.craig.revolute.data.model.Account;

import java.util.Optional;

public interface AccountsService {

    Optional<Account> findById(Long id);

    Account save(Account account);

    Account update(Account account);

    Optional<Account> delete(Long id);
}
