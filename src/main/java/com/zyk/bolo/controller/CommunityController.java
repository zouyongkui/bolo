package com.zyk.bolo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zyk.bolo.entity.Tb_Community;
import com.zyk.bolo.entity.Tb_Contribution;
import com.zyk.bolo.entity.Tb_Floor;
import com.zyk.bolo.service.CommunityService;
import com.zyk.bolo.utils.UploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/community")
public class CommunityController {

    private static Logger logger = LoggerFactory.getLogger(CommunityController.class);
    @Autowired
    private CommunityService service;

    /*
     * 发布帖子
     */

    @RequestMapping(value = "/updateCommunity", method = RequestMethod.POST)
    public Map<String, Object> updateCommunity(
            @RequestParam(value = "content") String content,
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "pic") MultipartFile pic) {

        String picUrl = UploadUtils.upload("", pic);
        Map<String, Object> rsMap = new HashMap<>();
        if (StringUtils.isEmpty(content) || StringUtils.isEmpty(title) || StringUtils.isEmpty(content.trim()) || StringUtils.isEmpty(title.trim())) {
            rsMap.put("code", -2);
        } else {
            int code = service.updateCommunity(userId, title, content, picUrl);
            rsMap.put("code", code);
        }
        rsMap.put("msg", "suc");
        return rsMap;
    }

    /*
     * 获取帖子列表
     */

    @RequestMapping(value = "/getCommunityList", method = RequestMethod.GET)
    public Map<String, Object> getCommunityList() {
        Map<String, Object> rsMap = new HashMap<>();
        List<Tb_Community> community = service.getCommunityList();
        rsMap.put("code", 1);
        rsMap.put("msg", "");
        rsMap.put("communityList", community);
        return rsMap;
    }


    /*
     * 添加帖子评论
     */

    @RequestMapping(value = "/updateFloor", method = RequestMethod.POST)
    public Map<String, Object> updateFloor(
            @RequestParam(value = "content") String content,
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "communityId") String communityId) {
        Map<String, Object> rsMap = new HashMap<>();
        int code = service.updateFloor(userId, communityId, content);
        rsMap.put("code", code);
        rsMap.put("msg", "");
        return rsMap;
    }

    /*
     * 获取帖子评论
     */

    @RequestMapping(value = "/getFloors", method = RequestMethod.GET)
    public Map<String, Object> getCommunity(
            @RequestParam(value = "communityId") String communityId) {
        Map<String, Object> rsMap = new HashMap<>();
        // 增加访问次数
        List<Tb_Floor> floors = service.getFloors(communityId);
        Tb_Community community = service.getCommunity(communityId);
        int visitCount = community.getVisitcount();
        rsMap.put("floors", floors);
        rsMap.put("code", 1);
        rsMap.put("msg", "suc");
        rsMap.put("pic", community.getPicUrl());
        rsMap.put("community", community);
        rsMap.put("visitCount", visitCount);
        return rsMap;
    }

    /*
     * 投稿功能
     */

    @RequestMapping(value = "/updateContribution", method = RequestMethod.POST)
    public Map<String, Object> updateContribution(
            @RequestParam(value = "content") String content,
            @RequestParam(value = "userId") String userId) {
        Map<String, Object> rsMap = new HashMap<>();
        int code = service.updateContribution(userId, content);
        rsMap.put("code", code);
        rsMap.put("msg", "");
        return rsMap;
    }
    /*
     * 获取投稿内容
     */


    @RequestMapping(value = "/getContributionList", method = RequestMethod.GET)
    public Map<String, Object> getContributionList() {
        Map<String, Object> rsMap = new HashMap<>();
        List<Tb_Contribution> list = service.getContributionList();
        rsMap.put("code", 1);
        rsMap.put("msg", "");
        rsMap.put("contributionList", list);
        return rsMap;
    }
}
