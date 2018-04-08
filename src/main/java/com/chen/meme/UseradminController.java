package com.chen.meme;

import com.chen.meme.model.Entityuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/hello")
public class UseradminController {

    /**
     * 获取数据库信息
     */
    @Autowired
    private EntityuserRepository entityuserRepository;
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public List<Entityuser> data() {
        System.out.println("yes");
        return entityuserRepository.findAll();
    }

    /**
     * 添加一个用户
     * @param userName
     * @param password
     * @param address
     * @param phonenumber
     * @param avatar
     * @param qq
     * @param introduction
     * @return
     */
    @PostMapping(value = "/adduser")
    public Entityuser UserAdd(@RequestParam("username")String userName, @RequestParam("password")String password,
                              @RequestParam("address") String address, @RequestParam("phonenumber")String phonenumber,
                              @RequestParam("avatar") String avatar, @RequestParam("qq")String qq,
                              @RequestParam("introduction") String introduction){
        System.out.println("yes1");
        Entityuser entityuser=new Entityuser();
        entityuser.setUserName(userName);
        entityuser.setPassword(password);
        entityuser.setAddress(address);
        entityuser.setPhonenumber(phonenumber);
        entityuser.setAvatar(avatar);
        entityuser.setQq(qq);
        entityuser.setIntroduction(introduction);
        return entityuserRepository.save(entityuser);

    }
//    @RequestMapping(value = "/useradd", method = RequestMethod.GET)
//    public String index(ModelMap modelMap) {
//        modelMap.put("msg", "SpringBoot Ajax 示例");
//
//        return "useradd";
//    }
//
//    @RequestMapping(value = "/useradd", method = RequestMethod.GET)
//    @ResponseBody
//    public String useradd() {
//
//        return "useradd";
//    }

    }


