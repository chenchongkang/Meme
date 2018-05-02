package com.chen.meme.server;

import com.chen.meme.repository.EntityuserRepository;
import com.chen.meme.model.Entityuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService {

    @Autowired
    private EntityuserRepository entityuserRepository;

    public Object Register(Entityuser entityuser){
        List<Entityuser> list = entityuserRepository.findByUserName(entityuser.getUserName());
        if(list.size()>0){
            return "用户名已存在";
        }
        else{
            entityuserRepository.save(entityuser);
            return "success";//注册成功
        }
    }
}
