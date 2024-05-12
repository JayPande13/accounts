package com.jay.accounts.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts/")
public class AccountsController {

    @GetMapping("hello")
    public String sayHello(){
        return "Hello world";
    }
}
