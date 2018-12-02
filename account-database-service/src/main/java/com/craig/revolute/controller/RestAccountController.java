package com.craig.revolute.controller;

import com.craig.revolute.data.model.Account;
import com.craig.revolute.data.model.dto.AccountDTO;
import com.craig.revolute.service.AccountsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static com.craig.revolute.controller.Paths.ACCOUNT;

@RestController
@RequestMapping(ACCOUNT)
public class RestAccountController {

    private final AccountsService accountsService;

    private final ObjectMapper mapper;

    public RestAccountController(AccountsService accountsService, ObjectMapper mapper) {
        this.accountsService = accountsService;
        this.mapper = mapper;
    }

    @ApiOperation(
            value = "Gets an existing account",
            response = AccountDTO.class
    )
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable Long id) {
        Optional<Account> account = accountsService.findById(id);
        return account.map(account1 -> ResponseEntity.ok(mapper.convertValue(account1, AccountDTO.class)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(
            value = "Creates an account",
            response = AccountDTO.class
    )
    @PostMapping(produces = "application/json")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid AccountDTO accountDTO) {
        Account accountToSave = mapper.convertValue(accountDTO, Account.class);
        Account savedAccount = accountsService.save(accountToSave);
        return ResponseEntity.ok(mapper.convertValue(savedAccount, AccountDTO.class));
    }

    @ApiOperation(
            value = "Delete an existing account",
            response = AccountDTO.class
    )
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<AccountDTO> deleteAccount(@PathVariable Long id) {
        Optional<Account> deletedAccount = accountsService.delete(id);
        return deletedAccount.map(account -> ResponseEntity.ok(mapper.convertValue(account, AccountDTO.class)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @ApiOperation(
            value = "Update Existing Account",
            response = AccountDTO.class
    )
    @PutMapping(produces = "application/json")
    public ResponseEntity<AccountDTO> updateAccount(@RequestBody @Valid AccountDTO accountDTO) {
        Account updatedAccount = accountsService.update(mapper.convertValue(accountDTO, Account.class));
        if (updatedAccount != null) {
            return ResponseEntity.ok(mapper.convertValue(updatedAccount, AccountDTO.class));
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
