package com.kh.helloworld.controller;

import jdk.internal.instrumentation.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;



@Controller
public class uploadController {

    @PostMapping("onfiles2")
    @ResponseBody
    public String onfiles2(MultipartFile img[]) throws IOException {

        File file  =new File("D:\\fileupload");
        if(!file.exists()) {
            file.mkdir();//在磁盘创建该文件
        }
        for(int i=0;i<img.length;i++)
        {
            if(!img[i].isEmpty())//文件不空
            {
                File imgfile =new File("D:/fileupload/"+img[i].getOriginalFilename());
                imgfile.createNewFile();
                img[i].transferTo(imgfile);
                Logger logger = null;
                logger.info(img[i].getOriginalFilename());
            }
        }
        return "success";
    }


}
