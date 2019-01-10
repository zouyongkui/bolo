package com.zyk.bolo.service;

import com.zyk.bolo.entity.Tb_BoloC;
import com.zyk.bolo.entity.Tb_CircleComment;
import com.zyk.bolo.repository.BoloCircleRepository;
import com.zyk.bolo.repository.CircleCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CircleCommentService {
    @Autowired
    CircleCommentRepository commentRepository;
    @Autowired
    BoloCircleRepository boloCircleRepository;

    public Map<String, Object> postComment(String usrId, String replyId, String circleId, String content) {

        Map<String, Object> map = new HashMap<>();
        if (boloCircleRepository.existsById(circleId)) {

            Tb_CircleComment circleComment = new Tb_CircleComment();
            circleComment.setCreatetime(new Date());
            circleComment.setContent(content);
            circleComment.setUsrid(usrId);
            circleComment.setReplyid(replyId);
            circleComment.setBolocid(circleId);
            commentRepository.save(circleComment);

            Tb_BoloC boloC = boloCircleRepository.getOne(circleId);
            boloC.setUpdatetime(new Date());
            boloCircleRepository.save(boloC);
            map.put("code", 0);
            map.put("msg", "suc");

        } else {

            map.put("code", -1);
            map.put("msg", "error");

        }
        return map;

    }
}
