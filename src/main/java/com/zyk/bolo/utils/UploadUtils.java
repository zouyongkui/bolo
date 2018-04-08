package com.zyk.bolo.utils;

import java.io.File;
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

    public static final String BASE_PATH = "/data/img/bolo";

    public static String upload(MultipartFile imageFile) {

        logger.debug("BASE_PATH=" + BASE_PATH);
        if (imageFile.isEmpty()) {
            return null;
        }
        String filename = imageFile.getOriginalFilename();

        String ext = null;
        if (filename.contains(".")) {
            ext = filename.substring(filename.lastIndexOf("."));
        } else {
            ext = "";
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String nFileName = uuid + ext;
        String dirPath = DateUtil.formatDateASYYYYMMDD(new Date());
        String filepath = BASE_PATH + "/" + dirPath;
        File targetFile = new File(filepath, nFileName);
        logger.debug("上传文件：" + filepath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        } else {
            targetFile.delete();
        }
        try {
            imageFile.transferTo(targetFile);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        String accessUrl = "/" + dirPath + "/" + nFileName;
        logger.debug("上传文件成功 URL：" + accessUrl);
        return accessUrl;
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
