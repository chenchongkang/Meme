package com.chen.meme;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemeController {
@RequestMapping(value = "/memetest",method = RequestMethod.GET)
    public String say(){

    return "Hello Meme";
}
}
