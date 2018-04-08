package com.zyk.bolo.service;

import java.util.Date;
import java.util.List;

import com.zyk.bolo.entity.Tb_Comment;
import com.zyk.bolo.entity.Tb_Soup;
import com.zyk.bolo.entity.Tb_User;
import com.zyk.bolo.repository.CommentRepository;
import com.zyk.bolo.repository.SoupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SoupService {

    @Autowired
    SoupRepository soupRepository;
    @Autowired
    CommentRepository commentRepository;

    public String getUserId(String deviceId, String brandName, String phoneNum) {
        Tb_User user = new Tb_User();
        user.setDeviceid(deviceId);
        user.setBrandname(brandName);
        user.setPhone(phoneNum);
        return "";

    }

    public int updateSoup(String content, String picUrl) {
        Tb_Soup soup = new Tb_Soup();
        soup.setCreatetime(new Date());
        soup.setContent(content);
        soup.setPic(picUrl);
        soup.setVisitcount(0);
        soupRepository.save(soup);
        return 0;
    }

    public Tb_Soup getSoup() {
        if (soupRepository.findAll().isEmpty()) {
            return null;
        }
        Tb_Soup soup = soupRepository.findAll(new Sort(Sort.Direction.DESC, "createtime")).get(0);
//        int visitCount = soup.getVisitcount();
//        visitCount++;
//        soup.setVisitcount(visitCount);
//        soupRepository.save(soup);
        return soup;
    }

    public List<Tb_Soup> getSoupList() {
        return soupRepository.findAll();
    }

    public int delComment(String commentId) {
        commentRepository.deleteById(commentId);
        return 0;
    }

    public int updateComment(String userId, String soupId, String content) {
        if (content.contains("奎") || content.contains("kui")) {
            return -1;
        }
        Tb_Comment comment = new Tb_Comment();
        comment.setCreatetime(new Date());
        comment.setContent(content);
        comment.setSoupid(soupId);
        comment.setUserid(userId);
        return 0;
    }

    /**
     * 获取某条鸡汤的评论列表
     *
     * @param soupId 鸡汤id
     * @return 鸡汤评论列表
     */
    public List<Tb_Comment> getSoupComment(String soupId) {
        Tb_Comment tb_comment = new Tb_Comment();
        tb_comment.setSoupid(soupId);
        Example<Tb_Comment> ex = Example.of(tb_comment);
        return commentRepository.findAll(ex, new Sort(Sort.Direction.DESC, "createtime"));
    }
}