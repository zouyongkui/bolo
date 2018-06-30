package com.zyk.bolo.controller;


import com.zyk.bolo.service.CircleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/circleComment")
public class CircleCommentController {
    @Autowired
    CircleCommentService commentService;

    @PostMapping(value = "postComment")
    public Map<String, Object> postComment(@RequestParam(value = "usrId") String usrId,
                                           @RequestParam(value = "replyId", required = false) String replyId,
                                           @RequestParam(value = "circleId") String circleId,
                                           @RequestParam(value = "content") String content) {

        return commentService.postComment(usrId, replyId, circleId, content);
    }

}
