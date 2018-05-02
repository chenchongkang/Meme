package com.chen.meme.server;

import com.chen.meme.model.Entitymeme;
import com.chen.meme.model.Entityuser;
import com.chen.meme.repository.EntitymemeRepository;
import com.chen.meme.model.Entitypicture;
import com.chen.meme.repository.EntitypictureRepository;
import com.chen.meme.repository.EntityuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class MemeService {
    private final static String PATH = "F:\\MYMEME\\";
    @Autowired
    private EntitypictureRepository entitypictureRepository;
    @Autowired
    private EntityuserRepository entityuserRepository;
    @Autowired
    private EntitymemeRepository entitymemeRepository;


    public boolean publishFile(Entitypicture entitypicture, MultipartFile file,String src) {
        if (entitypicture == null || file == null||file.isEmpty()) {
            return false;//参数不正确
        }
        String fileName = PATH + src;
        String path = file.getOriginalFilename();
        File folder = new File(fileName);
        if(!folder.exists()){
            folder.mkdir();
        }
        if(!folder.isDirectory()){
            return false;//父目录src不是文件夹
        }
        fileName = fileName + File.separator + path;
        // 获取文件的后缀名
        // String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(fileName);
//        if(dest.exists()){
//            return false;//文件已存在
//        }
        try {
            file.transferTo(dest);
        } catch (IllegalStateException | IOException e) {
            return false;//文件上传失败
        }
        entitypicture.setPath(path);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        entitypicture.setUpTime(df.format(System.currentTimeMillis()));
        entitypictureRepository.save(entitypicture);
        return true;
    }
//    添加多张图片
    @Transactional
    public boolean publishFiles(Entitypicture entitypicture, MultipartFile[] files,String src) {
        if (entitypicture == null || files == null|| files.length<=0) {
            return false;//参数不正确
        }
        String fileName = PATH + src;
        File folder = new File(fileName);
        if(!folder.exists()){
            folder.mkdir();
        }
        if(!folder.isDirectory()){
            return false;//父目录src不是文件夹
        }
        for (MultipartFile file :files){
            String path = file.getOriginalFilename();
            String rfileName = fileName + File.separator + path;
            // 获取文件的后缀名
            // String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 解决中文问题，liunx下中文路径，图片显示问题
            // fileName = UUID.randomUUID() + suffixName;
            File dest = new File(rfileName);
//            if(dest.exists()){
//                return false;//文件已存在
//            }
            try {
                file.transferTo(dest);
            } catch (IllegalStateException | IOException e) {
                return false;//文件上传失败
            }
            entitypicture.setPath(path);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            entitypicture.setUpTime(df.format(System.currentTimeMillis()));
            entitypictureRepository.save(entitypicture);
        }
        return true;
    }

    /**
     * 用户头像上传
     * @param entityuser
     * @param file
     * @param src
     * @return
     */
    public boolean avatarFile(Entityuser entityuser, MultipartFile file, String src) {
        if (entityuser == null || file == null||file.isEmpty()) {
            return false;//参数不正确
        }
        String fileName = PATH + src;
        String path = file.getOriginalFilename();
        File folder = new File(fileName);
        if(!folder.exists()){
            folder.mkdir();
        }
        if(!folder.isDirectory()){
            return false;//父目录src不是文件夹
        }
        fileName = fileName + File.separator + path;
        // 获取文件的后缀名
        // String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(fileName);
//        if(dest.exists()){
//            return false;//文件已存在
//        }
        try {
            file.transferTo(dest);
        } catch (IllegalStateException | IOException e) {
            return false;//文件上传失败
        }
        entityuser.setAvatar(path);
        entityuserRepository.save(entityuser);
        return true;
    }

    /**
     * 上传表情包封面图片
     * @param entitymeme
     * @param file
     * @param src
     * @return
     */
    public boolean coverFile(Entitymeme entitymeme, MultipartFile file, String src) {
        if (entitymeme == null || file == null||file.isEmpty()) {
            return false;//参数不正确
        }
        String fileName = PATH + src;
        String path = file.getOriginalFilename();
        File folder = new File(fileName);
        if(!folder.exists()){
            folder.mkdir();
        }
        if(!folder.isDirectory()){
            return false;//父目录src不是文件夹
        }
        fileName = fileName + File.separator + path;
        // 获取文件的后缀名
        // String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(fileName);
//        if(dest.exists()){
//            return false;//文件已存在
//        }
        try {
            file.transferTo(dest);
        } catch (IllegalStateException | IOException e) {
            return false;//文件上传失败
        }
        entitymeme.setCover(path);
        entitymemeRepository.save(entitymeme);
        return true;
    }
}
