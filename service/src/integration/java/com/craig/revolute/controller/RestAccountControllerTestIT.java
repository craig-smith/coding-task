package com.craig.revolute.controller;

import com.craig.revolute.data.model.Account;
import com.craig.revolute.data.model.Currency;
import com.craig.revolute.data.model.dto.AccountDTO;
import com.craig.revolute.data.model.dto.AddressDTO;
import com.craig.revolute.data.model.dto.OwnerDTO;
import com.craig.revolute.repository.AccountRepository;
import com.craig.revolute.service.AccountsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.craig.revolute.controller.Paths.ACCOUNT;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestAccountController.class)
public class RestAccountControllerTestIT {


    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AccountsService accountsService;

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mapper.registerModule(new KotlinModule());
    }

    @Test
    public void shouldSaveAccountDTO() throws Exception {
        //given
        AccountDTO accountDTO = getDefaultDTO().build();
        Account account = mapper.convertValue(accountDTO, Account.class);
        when(accountsService.save(account)).thenReturn(account);

        //when
        MvcResult result = mockMvc.perform(post("/" + ACCOUNT)
                .content(mapper.writeValueAsString(accountDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        assertThat(accountDTO).isEqualTo(mapper.readValue(result.getResponse().getContentAsString(), AccountDTO.class));

    }

    @Test
    public void shouldGetAccount() throws Exception {
        //given
        AccountDTO accountDTO = getDefaultDTO().build();
        when(accountsService.findById(1L)).thenReturn(Optional.of(mapper.convertValue(accountDTO, Account.class)));

        //when
        MvcResult result = mockMvc.perform(get("/" + ACCOUNT + "/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //then
        assertThat(accountDTO).isEqualTo(mapper.readValue(result.getResponse().getContentAsString(), AccountDTO.class));
    }

    @Test
    public void shouldDeleteAccount() throws Exception {
        //given
        AccountDTO accountDTO = getDefaultDTO().build();
        when(accountsService.delete(1L)).thenReturn(Optional.of(mapper.convertValue(accountDTO, Account.class)));

        //when
        MvcResult result = mockMvc.perform(delete("/" + ACCOUNT + "/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //then
        assertThat(accountDTO).isEqualTo(mapper.readValue(result.getResponse().getContentAsString(), AccountDTO.class));
    }

    @Test
    public void shouldUpdateAccount() throws Exception {
        //given
        AccountDTO accountDTO = getDefaultDTO().build();
        Account account = mapper.convertValue(accountDTO, Account.class);
        when(accountsService.update(account)).thenReturn(account);

        //when
        MvcResult result = mockMvc.perform(put("/" + ACCOUNT)
                .content(mapper.writeValueAsString(accountDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //then
        assertThat(accountDTO).isEqualTo(mapper.readValue(result.getResponse().getContentAsString(), AccountDTO.class));
    }

    private AccountDTO.Builder getDefaultDTO() {
        Map<Currency, Double> monies = new HashMap<>();
        monies.put(Currency.USD, 10000.0);
        AccountDTO.Builder accountDTO = new AccountDTO.Builder()
                .id(1L)
                .owner(getOwnerBuilder().build())
                .monies(monies);

        return accountDTO;
    }

    @NotNull
    private OwnerDTO.Builder getOwnerBuilder() {
        return new OwnerDTO.Builder()
                .id(1L)
                .firstName("firstname")
                .lastName("lastName")
                .phoneNumber("1234")
                .address(getAddressBuilder().build());

    }

    @NotNull
    private AddressDTO.Builder getAddressBuilder() {
        return new AddressDTO.Builder()
                .id(1L)
                .city("city")
                .country("country")
                .street("street");

    }
}