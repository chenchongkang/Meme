package com.chen.meme.controller;

import com.chen.meme.model.Entityuser;
import com.chen.meme.server.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping(value = "/login")
    public String Login(@RequestBody(required =false) Entityuser entityuser) {
        return loginService.Login(entityuser);
    }

    @PostMapping(value = "/finduserpass")
    public String FindUserPassword(@RequestBody(required = false) Entityuser entityuser){
        return loginService.FindUserPassword(entityuser);
    }
}
