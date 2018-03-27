package com.chen.meme;

import com.chen.meme.model.Entityuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping(value = "/data", method = RequestMethod.GET)
    @ResponseBody
    public List<Entityuser> data() {
        System.out.println("yes");
        return entityuserRepository.findAll();
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


