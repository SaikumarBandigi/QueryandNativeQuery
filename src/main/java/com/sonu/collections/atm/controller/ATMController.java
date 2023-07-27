package com.sonu.collections.atm.controller;

import com.sonu.collections.atm.AccountNotFoundException;
import com.sonu.collections.atm.Custom;
import com.sonu.collections.atm.CustomerAuthenticationException;
import com.sonu.collections.atm.InsufficientBalanceException;
import com.sonu.collections.atm.dao.AccountRepository;
import com.sonu.collections.atm.dao.CustomerRepository;
import com.sonu.collections.atm.model.Account;
import com.sonu.collections.atm.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/atm")
public class ATMController {

    @Autowired
    private final AccountRepository accountRepository;

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    public ATMController(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }


    /*
   POST=> localhost:8080/api/atm/postcust
    postman->body->raw->json
    _________
    {
    "name":"naveen",
    "pin":"1234",
    "account":
    {
       "accountNumber":"1515001",
       "balance":"24000"
    }
}
then this query makes sure to save the objects both in Customer and Account table...
     */
    @PostMapping("/postcust")
    public Customer postCust(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }


   // below query is optional since we are saving account object through Customer object itself
//    @PostMapping("/postacct")
//    public Account postAcct(@RequestBody Account account) {
//        return accountRepository.save(account);
//    }


    @PostMapping("/authenticate")
    public boolean authenticateCustomer(@RequestParam String customerId, @RequestParam String pin) {
        Customer customer = customerRepository.findByPin(pin);
        return customer != null && customer.getId().toString().equals(customerId);
    }


    @GetMapping("/balance/{customerId}/{accountNumber}/{pin}")
    public double checkBalance(@PathVariable String customerId, @PathVariable String accountNumber, @PathVariable String pin) {
        if (authenticateCustomer(customerId, pin)) {
            // Proceed with balance check
            Account account = accountRepository.findByAccountNumber(accountNumber);
            if (account != null) {
                return account.getBalance();
            } else {
                throw new AccountNotFoundException("Account not found");
                /* since we are throwing AccountNotFoundException("Account not found") expliacity means either we use try-catch or @ExceptionHandler
          if we use @ExceptionHandler then catch the AccountNotFoundException there and send some Response in ResponseEntity<> format
            * */
            }
        } else {
            throw new CustomerAuthenticationException("Authentication failed");
        }
    }

    @PostMapping("/deposit/{customerId}/{accountNumber}")
    public double deposit(@PathVariable String customerId, @PathVariable String accountNumber, @RequestParam double amount, @RequestParam String pin) {
        if (authenticateCustomer(customerId, pin)) {
            // Proceed with deposit
            Account account = accountRepository.findByAccountNumber(accountNumber);

            if (account != null) {
                double currentBalance = account.getBalance();
                account.setBalance(currentBalance + amount);
                accountRepository.save(account);
                return account.getBalance();
            } else {
                throw new AccountNotFoundException("Account not found");
            }
        } else {
            throw new CustomerAuthenticationException("Authentication failed");
        }
    }


    @PostMapping("/withdraw/{customerId}/{accountNumber}")
    public double withdraw(@PathVariable String customerId, @PathVariable String accountNumber, @RequestParam double amount, @RequestParam String pin) {
        if (authenticateCustomer(customerId, pin)) {
            // Proceed with withdraw
            Account account = accountRepository.findByAccountNumber(accountNumber);

            if (account != null) {
                double currentBalance = account.getBalance();
                if (currentBalance >= amount) {
                    account.setBalance(currentBalance - amount);
                    accountRepository.save(account);
                    return account.getBalance();
                } else {
                    throw new InsufficientBalanceException("Insufficient balance");
                }
            } else {
                throw new AccountNotFoundException("Invalid Account Number: " + accountNumber);
            }
        } else {
            throw new CustomerAuthenticationException("Authentication failed");
        }
    }


    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Custom> handleAccountNotFoundException(AccountNotFoundException ex) {
        // Customize the error message and response status here
        // ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        Custom customerror = new Custom(ex.getMessage(), new Date());
        return new ResponseEntity<>(customerror, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(CustomerAuthenticationException.class)
    public ResponseEntity<String> stringResponseEntity(CustomerAuthenticationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<String> stringResponseEntity(InsufficientBalanceException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
