package com.zyk.bolo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zyk.bolo.entity.Tb_Community;
import com.zyk.bolo.entity.Tb_Contribution;
import com.zyk.bolo.entity.Tb_Floor;
import com.zyk.bolo.entity.Tb_User;
import com.zyk.bolo.repository.CommunityRepository;
import com.zyk.bolo.repository.ContributionRepository;
import com.zyk.bolo.repository.FloorRepository;
import com.zyk.bolo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommunityService {
    @Autowired
    CommunityRepository communityRepository;
    @Autowired
    FloorRepository floorRepository;
    @Autowired
    ContributionRepository contributionRepository;
    @Autowired
    UserRepository userRepository;

    public int updateCommunity(String userId, String title, String content, String picUrl) {
        Tb_Community community = new Tb_Community();
        community.setUserid(userId);
        community.setTitle(title);
        community.setContent(content);
        community.setCreatetime(new Date());
        community.setVisitcount(0);
        community.setPicUrl(picUrl);
        community.setIsonline((byte) 1);
        community.setIstop((byte) 0);
        community.setReplytime(new Date());
        communityRepository.save(community);
        return 0;
    }

    public JSONObject getCommunityList() {
        JSONObject object = new JSONObject();
        List<Tb_Community> communityList = communityRepository.findAll(new Sort(Sort.Direction.DESC, "createtime"));
        JSONArray ja = new JSONArray();
        for (Tb_Community community : communityList) {
            Tb_User user = userRepository.getOne(community.getUserid());
            JSONObject jo = new JSONObject();
            jo.put("id", community.getId());
            jo.put("createtime", community.getCreatetime());
            jo.put("usrId", community.getUserid());
            jo.put("replytime", community.getReplytime());
            jo.put("content", community.getContent());
            jo.put("picUrl", community.getPicUrl());
            jo.put("usrFace", user.getFace_url());
            ja.add(jo);
        }
        object.put("code", 1);
        object.put("msg", "");
        object.put("communityList", ja);
        return object;
    }

    public int updateFloor(String userId, String communityId, String content) {
        Tb_Floor floor = new Tb_Floor();
        floor.setUserid(userId);
        floor.setCommunityid(communityId);
        floor.setFlcontent(content);
        floor.setCreatetime(new Date());
        floor.setIsonline((byte) 1);
        floorRepository.save(floor);
        return 0;
    }

    public List<Tb_Floor> getFloors(String communityId) {
        Tb_Floor floor = new Tb_Floor();
        floor.setCommunityid(communityId);
        Example<Tb_Floor> ex = Example.of(floor);
        return floorRepository.findAll(ex, new Sort(Sort.Direction.DESC, "createtime"));
    }

    public Tb_Community getCommunity(String communityId) {
        Tb_Community community = new Tb_Community();
        community.setId(communityId);
        Example<Tb_Community> ex = Example.of(community);
        if (!communityRepository.findAll(ex).isEmpty()) {
            Tb_Community targetCommunity = communityRepository.findAll(ex).get(0);
            int visitCount = targetCommunity.getVisitcount();
            visitCount++;

            targetCommunity.setId(communityId);
            targetCommunity.setVisitcount(visitCount);
            communityRepository.save(targetCommunity);
            return targetCommunity;
        }
        return null;

    }


    public int updateContribution(String userId, String content) {
        Tb_Contribution contribution = new Tb_Contribution();
        contribution.setContent(content);
        contribution.setUserid(userId);
        contribution.setCreatetime(new Date());
        contributionRepository.save(contribution);
        return 0;
    }

    public List<Tb_Contribution> getContributionList() {
        return contributionRepository.findAll(new Sort(Sort.Direction.DESC, "createtime"));
    }
}
