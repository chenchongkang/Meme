package com.chen.meme;

import com.chen.meme.model.Entitymeme;
import com.chen.meme.model.Entityuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
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
        return  entitymemeRepository.findAll();
    }

     @PostMapping(value = "/addmeme")
     public Entitymeme MemeAdd(@RequestBody(required =false) Entitymeme entitymeme) {
//        Entityuser s=entityuser;
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
         entitymeme.setUpTime(System.currentTimeMillis());
//         System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
//         System.out.println(entitymeme.getMemeName()+" yoooooooooooooooooooo");
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
                              @RequestParam("memeIntro") String memeIntro, @RequestParam("upTime") long upTime,
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
    //删除一个用户
    @DeleteMapping(value = "/entitymemedele/{memeID}")
    public void userDelete(@PathVariable("MemeID")Integer memeID){
        entitymemeRepository.delete(memeID);
    }

}

