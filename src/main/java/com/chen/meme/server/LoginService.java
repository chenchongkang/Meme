package com.chen.meme.server;

import com.chen.meme.EntitymemeRepository;
import com.chen.meme.EntityuserRepository;
import com.chen.meme.model.Entitymeme;
import com.chen.meme.model.Entityuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
