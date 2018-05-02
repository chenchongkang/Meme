package com.chen.meme.server;

import com.chen.meme.repository.EntityuserRepository;
import com.chen.meme.model.Entityuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    private EntityuserRepository entityuserRepository;

    public String Login(Entityuser entityuser){
        List<Entityuser> entityusers = entityuserRepository.findByUserNameAndPassword(entityuser.getUserName(),entityuser.getPassword());
        if(entityusers.size()<=0) {
            return "用户名密码错误";
        }else{
            return "success";//登录成功
        }
    }

}
