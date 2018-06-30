package com.zyk.bolo.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传工具类
 */
public class UploadUtils {

    private static Logger logger = LoggerFactory.getLogger(UploadUtils.class);

    private static final String BASE_PATH = "/data/img/soup";

    public static String upload(String path, MultipartFile imageFile) {
        if (imageFile == null || imageFile.getSize() == 0) {
            return null;
        }

        String filename = imageFile.getOriginalFilename();
        String ext = "";
        if (filename.contains(".")) {
            ext = filename.substring(filename.lastIndexOf("."));
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String dirPath = path + "/" + DateUtil.formatDateASYYYYMMDD(new Date()).replaceAll("-", "");
        String nFileName = uuid + ext;
        String nFilePath = BASE_PATH + dirPath + "/";
        File targetFile = new File(nFilePath);
        boolean isExists = true;
        if (!targetFile.exists()) {
            isExists = targetFile.mkdirs();
        }
        if (isExists) {
            try {
                FileOutputStream out = new FileOutputStream(nFilePath + nFileName);
                out.write(imageFile.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return null;
        }
        return dirPath + "/" + nFileName;
    }

    /**
     * 删除旧的路径
     *
     * @param
     */
    public static boolean delete(String path, String targetPath) {
        path = targetPath + path;
        File file = new File(path);
        boolean flag = false;
        if (file.isFile()) {
            flag = file.delete();
        }
        return flag;
    }
}
