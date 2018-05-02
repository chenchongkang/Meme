package com.chen.meme.controller;

import com.chen.meme.model.Entitypicture;
import com.chen.meme.server.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PictureController {

    @Autowired
    MemeService memeService;

    /**
     * 添加单张表情包图片
     *
     * @param memeID
     * @param src
     * @param describe
     * @param file
     * @return
     */
    @RequestMapping(value = "dataFile", method = RequestMethod.POST)
    public boolean publishFile(@RequestParam(value = "memeID", required = false) Integer memeID,
                               @RequestParam(value = "src", required = false) String src,
                               @RequestParam(value = "describe") String describe,
                               @RequestParam(value = "file") MultipartFile file) {
        Entitypicture entitypicture = new Entitypicture();
        entitypicture.setMemeDescribe(describe);
        entitypicture.setMemeID(memeID);
        return memeService.publishFile(entitypicture, file, src);
    }

    /**
     * 添加多张表情包图片
     *
     * @param files
     * @return
     */
    @RequestMapping(value = "dataFiles", method = RequestMethod.POST)
    public boolean publishFiles(@RequestParam(value = "fileArray") MultipartFile[] files, HttpServletRequest request) {
        int i = files.length;
        for (int j = 0; j < i; j++) {
            Entitypicture entitypicture = new Entitypicture();
            entitypicture.setMemeID(Integer.valueOf(request.getParameter("meme_id")));
            String memeSrc = request.getParameter("meme_src");
            memeService.publishFile(entitypicture, files[j], memeSrc);
        }
         return true;
}



//    @RequestMapping(value="/getLocalImage/nid/{nid}",method=RequestMethod.GET)
//    public void getLocalImage(@PathVariable("nid") Integer nid, HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        //获取缓存文件最后的修改时间（只有缓存过的文件在请求时存在该参数）
//        String lastUpdateTime = request.getHeader("if-modified-since");
//        //如果存在参数，告知浏览器使用缓存数据
//        if(lastUpdateTime!=null){
//            //如果需要判断过期时间，则将文件的最后修改时间与当前时间作比较进行判断
//            //status:304,使用缓存数据
//            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
//            return;
//        }
//        //读取本地图片输入流
//        FileInputStream inputStream = new FileInputStream(path+"/1.jpg");
//        int i = inputStream.available();
//        //byte数组用于存放图片字节数据
//        byte[] buff = new byte[i];
//        inputStream.read(buff);
//        //记得关闭输入流
//        inputStream.close();
//        //设置发送到客户端的响应内容类型
//        response.setHeader("Content-type", "image/jpg");
//        OutputStream out = response.getOutputStream();
//        out.write(buff);
//        //关闭响应输出流
//        out.close();
//    }

}

