package com.chen.meme.controller;


import com.chen.meme.FastDFSClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/file")
public class FileUpController {
     private static final org.slf4j.Logger fLog = LoggerFactory.getLogger(FileUpController.class);

    @Resource
    private FastDFSClient fastDFSClient;

    /**
     *
     * @param multipartFile
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/up",method = RequestMethod.POST)
    public Map<String,String> fileUp(MultipartHttpServletRequest multipartFile, HttpServletRequest request){

        Map<String,String> result = new HashMap<String,String>();
        String param = request.getParameter("param");//参数名称
        if(StringUtils.isEmpty(param)){
            result.put("result","false");
            result.put("msg","请添加参数");
        }
        InputStream is = null;

        String fileName = multipartFile.getFile(param).getOriginalFilename();
        try {
            long size = multipartFile.getFile(param).getSize();
            is = multipartFile.getFile(param).getInputStream();
            String path = fastDFSClient.uploadFileStream(is,size,fileName);
            result.put("result","true");
            //图片地址
            result.put("srckey",path);
        }catch (IOException e){
            result.put("result","false");
            fLog.error("file:"+fileName,e.fillInStackTrace());
        }finally {
            if (is !=null){
                try {
                    is.close();
                }catch (IOException io){
                    fLog.error(io.getMessage());
                }
            }
        }

        return result;
    }

}

