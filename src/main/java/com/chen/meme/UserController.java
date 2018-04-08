package com.chen.meme;

import com.chen.meme.model.Entityuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private EntityuserRepository entityuserRepository;
    /**
     * 查询用户列表
     * @return
     */
    @GetMapping(value = "/entityuser")
    public List<Entityuser> entityuserList(){
    return entityuserRepository.findAll();
    }

    /**
     * 添加一个用户
     * @return
     */
    @PostMapping(value = "/entityuser")
    public Entityuser UserAdd(@RequestBody(required =false) Entityuser entityuser) {
        Entityuser s=entityuser;
//        s = new String(s.getUsername("iso-8859-1"),"gbk");
//        URLDecoder.decode(String.valueOf(entityuser),"UTF-8");
        System.out.println(entityuser.getUserName()+" yoooooooooooooooooooo");
        return entityuserRepository.save(entityuser);

    }
    //根据ID查询一个用户
    @GetMapping(value = "/entityuser/{userID}")
    public  Entityuser userFindOne(@PathVariable("userID") Integer userid){
        return entityuserRepository.findOne(userid);
    }

//    //根据用户昵称查询用户
//    @GetMapping(value = "/entityuser/{username}")
//    public  List<Entityuser> userListByName(@PathVariable("username") String username){
//        return entityuserRepository.findByUserName(username);
//    }
    //更新用户
    @PutMapping(value = "/entityuser/{uesrID}")
    public Entityuser userUpdate(@RequestParam("userName") String userName, @RequestParam("password") String password,
                           @RequestParam("address") String address, @RequestParam("phonenumber")String phonenumber,
                           @RequestParam("avatar") String avatar, @RequestParam("qq")String qq,
                           @RequestParam("introduction") String introduction){

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
    //删除一个用户
    @DeleteMapping(value = "/entityuser/dele/{userID}")
    public void userDelete(@PathVariable("userID")Integer userID){
        entityuserRepository.delete(userID);
}



}
