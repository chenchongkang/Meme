package com.chen.meme.controller;

import com.chen.meme.EntitymemeRepository;
import com.chen.meme.model.Entitymeme;
import com.chen.meme.model.Entityuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class MemeController {
    @Autowired
    private EntitymemeRepository entitymemeRepository;

    /**
     * 查询表情包列表
     * @return
     */
    @RequestMapping(value = "/entitymemelist",method = RequestMethod.POST)
    public List<Entitymeme> data(){
        System.out.println("yes");
//        Entitymeme entitymeme=new Entitymeme();
//       System.out.println(entitymeme.getUpTime());
        return  entitymemeRepository.findAll();
    }
    @PostMapping(value = "/addmemecover")
      public Entitymeme addmemecover(@RequestBody(required =false) Entitymeme entitymeme) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Timestamp now= new Timestamp(System.currentTimeMillis());
        entitymeme.setUpTime(df.format(now));
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        System.out.println(entitymeme.toString());
        return entitymemeRepository.save(entitymeme);
    }


    /**
     * 添加表情包
     * @param entitymeme
     * @return
     */
     @PostMapping(value = "/addmeme")
     public Entitymeme MemeAdd(@RequestBody(required =false) Entitymeme entitymeme) {

         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
         Timestamp now= new Timestamp(System.currentTimeMillis());
         entitymeme.setUpTime(df.format(now));
         System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
         System.out.println(entitymeme.toString());
         return entitymemeRepository.save(entitymeme);
     }

//    /**
//     * 添加一个表情包
//     * @param memeName
//     * @param downloads
//     * @param memeIntro
//     * @param upTime
//     * @param classis
//     * @return
//     */
//    @PostMapping(value = "/addmeme")
//    public Entitymeme UserAdd(@RequestParam("memeName")String memeName, @RequestParam("downloads")Integer downloads,
//                              @RequestParam("memeIntro") String memeIntro, @RequestParam("upTime") java.sql.Date upTime,
//                              @RequestParam("classis") String classis,@RequestParam("author")String author,
//                              @RequestParam("src") String src,@RequestParam("cover") String cover){
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
//        System.out.println("yes2");
//        Entitymeme entitymeme=new Entitymeme();
//        entitymeme.setMemeName(memeName);
//        entitymeme.setDownloads(downloads);
//        entitymeme.setUpTime(upTime);
//        entitymeme.setMemeIntro(memeIntro);
//        entitymeme.setClassis(classis);
//        entitymeme.setAuthor(author);
//        entitymeme.setSrc(src);
//        entitymeme.setCover(cover);
//        return entitymemeRepository.save(entitymeme);
//
//    }



    //根据ID查询表情包
    @GetMapping(value = "/entitymeme/{memeID}")
    public  Entitymeme userFindOne(@PathVariable("memeID") Integer memeid){
        return entitymemeRepository.findOne(memeid);
    }

    //更新表情包
    @PutMapping(value = "/entitymeme/{memeID}")
    public Entitymeme UserUpdata(@RequestParam("memeName")String memeName, @RequestParam("downloads")Integer downloads,
                              @RequestParam("memeIntro") String memeIntro, @RequestParam("upTime") String upTime,
                              @RequestParam("classis") String classis,@RequestParam("author")String author,@RequestParam("cover") String cover){
        System.out.println("yes2");
        Entitymeme entitymeme=new Entitymeme();
        entitymeme.setMemeName(memeName);
        entitymeme.setDownloads(downloads);
        entitymeme.setUpTime(upTime);
        entitymeme.setMemeIntro(memeIntro);
        entitymeme.setClassis(classis);
        entitymeme.setAuthor(author);
        entitymeme.setCover(cover);

        return entitymemeRepository.save(entitymeme);

    }
    //删除表情包
    @DeleteMapping(value = "/entitymemedele/{memeID}")
    public void userDelete(@PathVariable("MemeID")Integer memeID){
         entitymemeRepository.delete(memeID);
    }

}

