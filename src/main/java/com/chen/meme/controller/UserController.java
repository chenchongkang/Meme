package com.chen.meme.controller;

import com.chen.meme.repository.EntityuserRepository;
import com.chen.meme.model.Entityuser;
import com.chen.meme.server.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class UserController {
    private final int ERR=0;
    @Autowired
    MemeService memeService;

    @Autowired
    private EntityuserRepository entityuserRepository;
    /**
     * 查询用户列表
     * @return
     */
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public List<Entityuser> data() {
        return entityuserRepository.findAll();
    }
    /**
     * 添加一个用户
     * @return
     */
    @PostMapping(value = "/entityuser")
    public Object UserAdd(@RequestParam(value = "file") MultipartFile file,HttpServletRequest request) {
        Entityuser entityuser=new Entityuser();
        List<Entityuser> list = entityuserRepository.findByUserName(entityuser.getUserName());
        if(list.size()>0){
            return ERR;
        }
        else{
            entityuser.setUserName(request.getParameter("user_name"));
            entityuser.setPassword(request.getParameter("password1"));
            entityuser.setAddress(request.getParameter("user-email"));
            entityuser.setPhonenumber(request.getParameter("user-phone"));
            entityuser.setQq(request.getParameter("user-QQ"));
            entityuser.setIntroduction(request.getParameter("user-intro"));
            String src="avatar";
            return memeService.avatarFile(entityuser,file,src);
        }
    }

    //根据ID查询一个用户
    @PostMapping(value = "/entityuserfindid/{userID}")
    public  Entityuser userFindOne(@PathVariable("userID") Integer userid){
        return entityuserRepository.findOne(userid);
    }

    //根据用户名查询用户
    @PostMapping(value="/username/{userName}")
    public Entityuser entityusersByUserName(@PathVariable("userName")String userName){
        return entityuserRepository.findAllByUserName(userName);
    }

    //查询用户名和密码
    @GetMapping(value = "/usernameandpassword/{userName}/{password}")
    public List<Entityuser>entityusersByUserNameAndPassword(@PathVariable("userName")String userName,
                                                            @PathVariable("password") String password){
        return entityuserRepository.findByUserNameAndPassword(userName,password);
    }

    /**
     * 根据用户昵等模糊称查询用户
     * @param username
     * @return
     */
    @GetMapping(value = "/entityuser/{username}")
    public  List<Entityuser> userListByName(@PathVariable("username") String username){
        String key = "%" + username + "%";
        return entityuserRepository.findByUserNameLikeOrAddressLikeOrPhonenumberLikeOrQqLike(key,key,key,key);
    }

    /**
     * 更新用户并修改头像
     * @param userID
     * @param file
     * @param request
     * @return
     */
    @PostMapping(value = "/entityuserupdate/{userID}")
    public Object userUpdate(@PathVariable("userID")Integer userID,
                             @RequestParam(value = "file",required =false) MultipartFile file,HttpServletRequest request){
       Entityuser entityuser=new Entityuser();
        List<Entityuser> list = entityuserRepository.findByUserName(request.getParameter("user_name"));
        boolean flag = false;
        if(list.size()>0){
            flag = true;
            for( Entityuser user : list){
                if(user.getUserID() == userID){
                    flag = false;
                }
            }
        }
        if(flag){
            return ERR;
        }else {
            entityuser.setUserID(userID);
            entityuser.setUserName(request.getParameter("user_name"));
            entityuser.setPassword(request.getParameter("password1"));
            entityuser.setAddress(request.getParameter("user-email"));
            entityuser.setPhonenumber(request.getParameter("user-phone"));
            entityuser.setQq(request.getParameter("user-QQ"));
            entityuser.setIntroduction(request.getParameter("user-intro"));
            if (file==null){
                entityuser.setAvatar(request.getParameter("user_avatar"));
                return  entityuserRepository.save(entityuser);
            }
            String src="avatar";
            return memeService.avatarFile(entityuser,file,src);
        }
    }

    /**
     * 手机端更新用户并修改头像
     * @param userID
     * @param file
     * @param entityuser
     * @return
     */
    @PostMapping(value = "/entityuserupdates/{userID}")
    public Object userUpdates(@PathVariable("userID")Integer userID,
                             @RequestParam(value = "file",required =false) MultipartFile file,
                             @RequestBody(required =false) Entityuser entityuser){
        List<Entityuser> list = entityuserRepository.findByUserName(entityuser.getUserName());
        boolean flag = false;
        if(list.size()>0){
            flag = true;
            for( Entityuser user : list){
                if(user.getUserID() == userID){
                    flag = false;
                }
            }
        }
        if(flag){
            return ERR;
        }else {
            entityuser.setUserID(userID);
            if (file==null){
                entityuserRepository.save(entityuser);
                return "保存成功";
            }
            String src="avatar";
            memeService.avatarFile(entityuser,file,src);
            return "保存成功";
        }
    }

//    @PostMapping(value = "/entityuserupdate/{userID}")
//    public Object userUpdate(@PathVariable("userID")Integer userID,
//                             @RequestBody(required =false) Entityuser entityuser){
//        System.out.println("test"+userID);
//        List<Entityuser> list = entityuserRepository.findByUserName(entityuser.getUserName());
//        boolean flag = false;
//        if(list.size()>0){
//            flag = true;
//           for( Entityuser user : list){
//               if(user.getUserID() == userID){
//                   flag = false;
//               }
//           }
//        }
//        if(flag){
//            return ERR;
//        }else {
//            entityuser.setUserID(userID);
//            return entityuserRepository.save(entityuser);
//        }
//    }


    //删除一个用户
    @PostMapping(value = "/entityuser/dele/{userID}")
    public String  userDelete(@PathVariable("userID")Integer userID){
       entityuserRepository.delete(userID);
       return "0";
}

    /**
     * 获取用户头像
     **/
    @RequestMapping(value="/getuseravatar/{userID}",method=RequestMethod.GET)
    @ResponseBody
    public void getLocalImage(@PathVariable("userID") Integer userID,HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("************************* ");
        List<Entityuser> list =  entityuserRepository.findByUserID(userID);
        Entityuser entityuser = list.get(0);
        String path = entityuser.getAvatar();
        //获取缓存文件最后的修改时间（只有缓存过的文件在请求时存在该参数）
        String lastUpdateTime = request.getHeader("if-modified-since");
        //如果存在参数，告知浏览器使用缓存数据
        if(lastUpdateTime!=null){
            //如果需要判断过期时间，则将文件的最后修改时间与当前时间作比较进行判断
            //status:304,使用缓存数据
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            return;
        }
        //读取本地图片输入流
        FileInputStream inputStream = new FileInputStream("F:\\MYMEME\\avatar\\"+path);
        int i = inputStream.available();
        //byte数组用于存放图片字节数据
        byte[] buff = new byte[i];
        inputStream.read(buff);
        //记得关闭输入流
        inputStream.close();
        //设置发送到客户端的响应内容类型
        response.setHeader("Content-type", "image/jpg");
        OutputStream out = response.getOutputStream();
        out.write(buff);
        //关闭响应输出流
        out.close();
    }

}
