package com.zyk.bolo.service;

import com.zyk.bolo.entity.Tb_CircleComment;
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

    public Map<String, Object> postComment(String usrId, String replyId, String circleId, String content) {
        Tb_CircleComment circleComment = new Tb_CircleComment();
        circleComment.setCreatetime(new Date());
        circleComment.setContent(content);
        circleComment.setUsrid(usrId);
        circleComment.setReplyid(replyId);
        circleComment.setCircleid(circleId);
        commentRepository.save(circleComment);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "suc");
        map.put("data", "");
        return map;

    }
}
