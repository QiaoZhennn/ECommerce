package com.atguigu.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyUploadFileUtil {
    public static List<String> uploadImage(MultipartFile[] files){
        List<String> listImage=new ArrayList<>();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if(!"".equals(files[i].getOriginalFilename())){
                    //"windows_path是在resources下的uploadPath.properties配置的，用于设置服务器存放图片的路径"
                    String windowsPath = MyProperty.getMyProperty("uploadPath.properties","windows_path");
                    //通过当前时间命名，避免重名。也可以通过随机的UUID完成。
                    String originalFilename = System.currentTimeMillis() + files[i].getOriginalFilename();
                    try {
                        files[i].transferTo(new File(windowsPath + "\\" + originalFilename));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    listImage.add(originalFilename);
                }
            }
        }
        return listImage;
    }
}
