package com.chen.meme.controller;

import com.chen.meme.model.Entityuser;
import com.chen.meme.server.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    RegisterService registerService;

    @PostMapping(value = "/register")
    public Object Register(@RequestBody(required =false) Entityuser entityuser) {
        return registerService.Register(entityuser);
    }
}
