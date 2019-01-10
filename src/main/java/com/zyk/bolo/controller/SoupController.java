package com.zyk.bolo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.zyk.bolo.entity.Tb_Comment;
import com.zyk.bolo.entity.Tb_Soup;
import com.zyk.bolo.service.SoupService;
import com.zyk.bolo.utils.UploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api")
public class SoupController {
    private static Logger logger = LoggerFactory.getLogger(SoupController.class);
    @Autowired
    private SoupService soupService;

    @RequestMapping(value = "/getUserId", method = RequestMethod.GET)
    public Map<String, Object> getUserId(@RequestParam(value = "deviceId", required = false) String deviceId,
                                         @RequestParam(value = "brandName", required = false) String brandName,
                                         @RequestParam(value = "phoneNum", required = false) String phoneNum) {
        return soupService.getUserInfo(deviceId, brandName, phoneNum);
    }

    @PostMapping(value = "/createSoup")
    public Map<String, Object> createSoup(@RequestParam(value = "content") String content,
                                          @RequestParam(value = "pic", required = false) MultipartFile pic) {
        String picUrl = UploadUtils.upload("", pic);
        int code = soupService.updateSoup(content, picUrl);
        Map<String, Object> rsMap = new HashMap<>();
        rsMap.put("code", code);
        return rsMap;
    }


    @RequestMapping(value = "/getSoup", method = RequestMethod.GET)
    public JSONObject getSoup() {
        JSONObject jsonObject = new JSONObject();
        Tb_Soup tbsoup = soupService.getSoup();
        if (tbsoup != null) {
            List<Tb_Comment> commentList = soupService.getSoupComment(tbsoup.getId());
            jsonObject.put("data", commentList);
            jsonObject.put("code", 1);
            jsonObject.put("soup", tbsoup.getContent());
            jsonObject.put("soupid", tbsoup.getId());
            jsonObject.put("picurl", tbsoup.getPic());
        } else {
            jsonObject.put("code", -1);
        }
        return jsonObject;
    }


    @RequestMapping(value = "/getSoupList", method = RequestMethod.GET)
    public Map<String, Object> getSoupList() {
        Map<String, Object> rsMap = new HashMap<>();
        List<Tb_Soup> tbsoupList = soupService.getSoupList();
        rsMap.put("code", 0);
        rsMap.put("soupList", tbsoupList == null ? "暂时没有更新哦" : tbsoupList);
        return rsMap;
    }


    @RequestMapping(value = "/delComment", method = RequestMethod.GET)
    public Map<String, Object> delComment(@RequestParam(value = "id") String id) {
        Map<String, Object> rsMap = new HashMap<>();
        int code = soupService.delComment(id);
        rsMap.put("code", code + "");
        return rsMap;
    }

    @RequestMapping(value = "/updateComment", method = RequestMethod.POST)
    public Map<String, Object> updateComment(@RequestParam(value = "userId") String userId,
                                             @RequestParam(value = "soupId") String soupId,
                                             @RequestParam(value = "content") String content) {
        Map<String, Object> rsMap = new HashMap<>();
        int code = soupService.updateComment(userId, soupId, content);
        rsMap.put("code", code);
        if (code == 0) {
            rsMap.put("msg", "");
        } else if (code == -1) {
            rsMap.put("msg", "评论不能出现作者名称哦！");
        }
        return rsMap;
    }

    /**
     * 获取鸡汤评论
     *
     * @param soupId
     * @return
     */

    @RequestMapping(value = "/getComment", method = RequestMethod.GET)
    public Map<String, Object> getComment(@RequestParam(value = "soupId") String soupId) {
        Map<String, Object> rsMap = new HashMap<>();
        List<Tb_Comment> tbCommentList = soupService.getSoupComment(soupId);
        rsMap.put("code", "200");
        rsMap.put("data", tbCommentList);
        return rsMap;
    }


//    @RequestMapping(value = "/getvisitcount", method = RequestMethod.GET)
//    public Map<String, Object> getVisitCount() {
//        Map<String, Object> rsMap = new HashMap<String, Object>();
////        int visitCount = soupService.getVisitCount();
//        rsMap.put("code", "200");
//        rsMap.put("visitCount", visitCount);
//        return rsMap;
//    }
}
