package com.chen.meme.controller;


import com.chen.meme.repository.EntitymemeRepository;
import com.chen.meme.model.Entitymeme;
import com.chen.meme.server.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class MemeController {
    private final int ERR = 0;
    @Autowired
    private EntitymemeRepository entitymemeRepository;

    @Autowired
    MemeService memeService;

    /**
     * 查询表情包列表
     * @return
     */
    @RequestMapping(value = "/entitymemelist", method = RequestMethod.POST)
    public List<Entitymeme> data() {
        return entitymemeRepository.findAll();
    }

    /**
     * 最新表情包
     * @return
     */
    @RequestMapping(value = "/entitylatestmemelist", method = RequestMethod.POST)
    public List<Entitymeme> latestList() {
        Sort sort = new Sort(Sort.Direction.DESC, "memeID");
        List<Entitymeme> list=entitymemeRepository.findAll(sort);
        return list;
    }

        /**
        * 最热表情包
        */
        @RequestMapping(value = "/entityhottestmemelist", method = RequestMethod.POST)
        public List<Entitymeme> hottestList() {
            Sort sort = new Sort(Sort.Direction.DESC, "downloads");
            List<Entitymeme> list=entitymemeRepository.findAll(sort);
            return list;
        }

    /**
     * 图片
     * @param entitymeme
     * @return
     */
    @PostMapping(value = "/addmemecover")
    public Entitymeme addmemecover(@RequestBody(required = false) Entitymeme entitymeme) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Timestamp now = new Timestamp(System.currentTimeMillis());
        entitymeme.setUpTime(df.format(now));
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        System.out.println(entitymeme.toString());
        return entitymemeRepository.save(entitymeme);
    }

    /**
     * 添加表情包
     * @param file
     * @param request
     * @return
     */
    @PostMapping(value = "/addmeme")
    public Object MemeAdd(@RequestParam(value = "file") MultipartFile file,HttpServletRequest request) {
        Entitymeme entitymeme=new Entitymeme();
        List<Entitymeme> list = entitymemeRepository.findAllByMemeSrc(request.getParameter("meme_src"));
        if (list.size() > 0) {
            return ERR;
        } else {
            entitymeme.setMemeName(request.getParameter("meme_name"));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            Timestamp now = new Timestamp(System.currentTimeMillis());
            entitymeme.setUpTime(df.format(now));
            entitymeme.setClassis(request.getParameter("meme_classes"));
            entitymeme.setAuthor(request.getParameter("meme_author"));
            entitymeme.setMemeSrc(request.getParameter("meme_src"));
            entitymeme.setMemeIntro(request.getParameter("meme_intro"));
            String cover="cover";
            return memeService.coverFile(entitymeme,file,cover);
//            return entitymemeRepository.save(entitymeme);
        }
    }


    //根据ID查询表情包
    @PostMapping(value = "/entitymemefindid/{memeID}")
    public Entitymeme memeFindOne(@PathVariable("memeID") Integer memeid) {
        return entitymemeRepository.findOne(memeid);
    }

    /**
     * 根据表情包信息模糊查询表情包
     * @param username
     * @return
     */
    @GetMapping(value = "/entitymemesearch/{memename}")
    public List<Entitymeme> memeListByName(@PathVariable("memename") String username) {
        String key = "%" + username + "%";
        return entitymemeRepository.findByMemeNameLikeOrAuthorLikeOrMemeIntroLike(key, key, key);
    }

    /**
     * 根据表情包分类查询表情包
     *
     * @param classis
     * @return
     */
    @PostMapping(value = "/entitymemeclassis/{classis}")
    public List<Entitymeme> memeListByClassis(@PathVariable("classis") String classis) {
        return entitymemeRepository.findByClassis(classis);
    }

    /**
     * 根据路径查询表情包
     * @param src
     * @return
     */
    @PostMapping(value = "/entitymemesrc/{memeSrc}")
    public Entitymeme memeListBySrc(@PathVariable("memeSrc") String src) {
        return entitymemeRepository.findByMemeSrc(src);
    }

    /**
     * 更新表情包
     * @param memeID
     * @param
     * @return
     */
    @PostMapping(value = "/entitymemeupdate/{memeID}")
    public Object MemeUpdata(@PathVariable("memeID") Integer memeID, @RequestParam(value = "file",required = false) MultipartFile file,HttpServletRequest request) {
        Entitymeme entitymeme=new Entitymeme();
        List<Entitymeme> list = entitymemeRepository.findAllByMemeSrc(request.getParameter("meme_src"));
        boolean flag = false;
        if(list.size()>0){
            flag = true;
            for( Entitymeme meme : list){
                if(meme.getMemeID() == memeID){
                    flag = false;
                }
            }
        }
        if(flag){
            return ERR;
        }else {
            entitymeme.setMemeID(memeID);
            entitymeme.setMemeName(request.getParameter("meme_name"));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            Timestamp now = new Timestamp(System.currentTimeMillis());
            entitymeme.setUpTime(df.format(now));
            entitymeme.setClassis(request.getParameter("meme_classes"));
            entitymeme.setAuthor(request.getParameter("meme_author"));
            entitymeme.setMemeSrc(request.getParameter("meme_src"));
            entitymeme.setMemeIntro(request.getParameter("meme_intro"));
            String a=request.getParameter("meme_download");
            int b = Integer.valueOf(a);
            entitymeme.setDownloads(b);
            if (file==null){
                entitymeme.setCover(request.getParameter("meme_cover"));
                return entitymemeRepository.save(entitymeme);
            }else {
            String cover="cover";
            return memeService.coverFile(entitymeme,file,cover);
            }
        }
    }

    @PostMapping(value = "/entitymemedownload/{memeID}")
    public Object MemeUpdata(@PathVariable("memeID") Integer memeID, @RequestBody(required = false) Entitymeme entitymeme) {
        entitymeme.setMemeID(memeID);
        entitymemeRepository.save(entitymeme);
        return "success" ;
    }

    /**
     * 删除表情包
     * @param memeID
     * @return
     */
    @PostMapping(value = "/entitymemedele/{memeID}")
    public String memeDelete(@PathVariable("memeID") Integer memeID) {
        entitymemeRepository.delete(memeID);
        return "0";//登录成功
    }

    /**
     * 获取表情包封面
     **/
    @RequestMapping(value="/getmemecover/{memeID}",method=RequestMethod.GET)
    @ResponseBody
    public void getLocalImage(@PathVariable("memeID") Integer memeID,HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Entitymeme> list =  entitymemeRepository.findAllByMemeID(memeID);
        Entitymeme entitymeme = list.get(0);
        String path = entitymeme.getCover();
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
        FileInputStream inputStream = new FileInputStream("F:\\MYMEME\\cover\\"+path);
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



