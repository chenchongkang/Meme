package com.chen.meme;

import com.chen.meme.model.Entitymeme;
import com.chen.meme.model.Entityuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
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
    @RequestMapping(value = "/entitymemelist",method = RequestMethod.GET)
    public List<Entitymeme> entitymemeList(){
        return  entitymemeRepository.findAll();
    }

    /**
     * 添加一个表情包
     * @param memeName
     * @param downloads
     * @param memeIntro
     * @param upTime
     * @param classis
     * @return
     */
    @PostMapping(value = "/addmeme")
    public Entitymeme UserAdd(@RequestParam("memeName")String memeName, @RequestParam("downloads")Integer downloads,
                              @RequestParam("memeIntro") String memeIntro, @RequestParam("upTime") java.sql.Date upTime,
                              @RequestParam("classis") String classis){
        System.out.println("yes2");
        Entitymeme entitymeme=new Entitymeme();
        entitymeme.setMemeName(memeName);
        entitymeme.setDownloads(downloads);
        entitymeme.setUpTime(upTime);
        entitymeme.setMemeIntro(memeIntro);
        entitymeme.setClassis(classis);


        return entitymemeRepository.save(entitymeme);

    }
    //根据ID查询表情包
    @GetMapping(value = "/entitymeme/{memeID}")
    public  Entitymeme userFindOne(@PathVariable("memeID") Integer memeid){
        return entitymemeRepository.findOne(memeid);
    }

    //更新表情包
    @PutMapping(value = "/entitymeme/{memeID}")
    public Entitymeme UserUpdata(@RequestParam("memeName")String memeName, @RequestParam("downloads")Integer downloads,
                              @RequestParam("memeIntro") String memeIntro, @RequestParam("upTime") java.sql.Date upTime,
                              @RequestParam("classis") String classis){
        System.out.println("yes2");
        Entitymeme entitymeme=new Entitymeme();
        entitymeme.setMemeName(memeName);
        entitymeme.setDownloads(downloads);
        entitymeme.setUpTime(upTime);
        entitymeme.setMemeIntro(memeIntro);
        entitymeme.setClassis(classis);


        return entitymemeRepository.save(entitymeme);

    }
    //删除一个用户
    @DeleteMapping(value = "/entitymemedele/{memeID}")
    public void userDelete(@PathVariable("MemeID")Integer memeID){
        entitymemeRepository.delete(memeID);
    }

}

