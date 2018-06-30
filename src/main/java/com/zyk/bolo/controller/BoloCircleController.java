package com.zyk.bolo.controller;

import com.alibaba.fastjson.JSONObject;
import com.zyk.bolo.service.BoloCircleService;
import com.zyk.bolo.utils.UploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("api/boloCircle")
public class BoloCircleController {
    private static Logger logger = LoggerFactory.getLogger(BoloCircleController.class);

    @Autowired
    BoloCircleService boloCircleService;

    @PostMapping(value = "postCircle")
    public Map<String, Object> postCircle(@RequestParam(value = "usrId") String usrId,
                                          @RequestParam(value = "content") String content,
                                          @RequestParam(value = "image", required = false) MultipartFile image) {
        String picUrl = UploadUtils.upload("/" + usrId, image);
        return boloCircleService.postCircle(usrId, content, picUrl);
    }

    @GetMapping(value = "getCircleList")
    public Map<String, Object> getCircleList(@RequestParam(value = "page") int page,
                                             @RequestParam(value = "size") int size) {
        return boloCircleService.getCircleList(page, size);
    }
}
