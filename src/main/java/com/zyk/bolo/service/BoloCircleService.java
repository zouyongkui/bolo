package com.zyk.bolo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zyk.bolo.entity.Tb_BoloC;
import com.zyk.bolo.entity.Tb_CircleComment;
import com.zyk.bolo.entity.Tb_User;
import com.zyk.bolo.repository.BoloCircleRepository;
import com.zyk.bolo.repository.CircleCommentRepository;
import com.zyk.bolo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoloCircleService {
    @Autowired
    BoloCircleRepository circleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CircleCommentRepository commentRepository;

    public Map<String, Object> postCircle(String usrId, String content, String picUrl) {
        Tb_BoloC boloCircle = new Tb_BoloC();
        boloCircle.setCreatetime(new Date());
        boloCircle.setUpdatetime(new Date());
        boloCircle.setUsrid(usrId);
        boloCircle.setContent(content);
        if (!StringUtils.isEmpty(picUrl))
            boloCircle.setPic(picUrl);
        circleRepository.save(boloCircle);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "suc");
        map.put("data", "");
        return map;
    }

    public Map<String, Object> getCircleList(int page, int size) {
        List<Tb_BoloC> circleList = circleRepository.findAllByIdIsNotNullOrderByUpdatetimeDesc(PageRequest.of(page, size));
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "suc");
        JSONArray ja = new JSONArray();
        for (Tb_BoloC boloC : circleList) {
            JSONObject jo = new JSONObject();
            jo.put("usrId", boloC.getUsrid());
            if (userRepository.existsById(boloC.getUsrid())) {
                Tb_User user = userRepository.getOne(boloC.getUsrid());
                jo.put("usrFace", user.getFace_url());
                jo.put("usrName", user.getUsrName());
            } else {
                jo.put("usrFace", "");
                jo.put("usrName", "");
            }
            jo.put("id", boloC.getId());
            jo.put("createTime", boloC.getCreatetime().getTime());
            jo.put("updateTime", boloC.getUpdatetime().getTime());
            jo.put("content", boloC.getContent());
            jo.put("picUrl", boloC.getPic());
            JSONArray commentJa = new JSONArray();
            List<Tb_CircleComment> commentList = commentRepository.findByBolocidEqualsOrderByCreatetimeAsc(boloC.getId());
            if (commentList != null && commentList.size() > 0) {
                for (Tb_CircleComment comment : commentList) {
                    JSONObject commentJo = new JSONObject();
                    if (StringUtils.isEmpty(comment.getReplyid())) {
                        commentJo.put("replyId", "");
                    } else {
                        if (userRepository.existsById(comment.getReplyid())) {
                            Tb_User replyUser = userRepository.getOne(comment.getReplyid());
                            commentJo.put("replyId", replyUser.getUsrName());
                        } else {
                            commentJo.put("replyId", "");
                        }
                    }
                    commentJo.put("content", comment.getContent());
                    commentJo.put("createTime", comment.getCreatetime().getTime());
                    if (userRepository.existsById(comment.getUsrid())) {
                        Tb_User coUser = userRepository.getOne(comment.getUsrid());
                        commentJo.put("usrName", coUser.getUsrName());
                    } else {
                        commentJo.put("usrName", "");
                    }
                    commentJa.add(commentJo);
                }
            }
            jo.put("comment", commentJa);
            ja.add(jo);
        }
        map.put("data", ja);
        return map;
    }
}
