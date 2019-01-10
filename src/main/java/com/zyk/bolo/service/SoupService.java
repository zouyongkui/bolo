package com.zyk.bolo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zyk.bolo.entity.Tb_Comment;
import com.zyk.bolo.entity.Tb_Soup;
import com.zyk.bolo.entity.Tb_User;
import com.zyk.bolo.repository.CommentRepository;
import com.zyk.bolo.repository.SoupRepository;
import com.zyk.bolo.repository.UserRepository;
import com.zyk.bolo.utils.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class SoupService {

    @Autowired
    SoupRepository soupRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;

    public Map<String, Object> getUserInfo(String deviceId, String brandName, String phoneNum) {
        HashMap<String, Object> map = new HashMap<>();
        String usrName, usrFace, usrId;
        if (userRepository.existsByPhoneEquals(phoneNum)) {
            Tb_User user = userRepository.findByPhoneEquals(phoneNum);
            usrId = user.getId();
            usrName = user.getUsrName();
            usrFace = user.getFace_url();
        } else {
            usrId = IDGenerator.getUUID();
            usrName = "佚名";
            usrFace = "";
            Tb_User user = new Tb_User();
            user.setId(usrId);
            user.setDeviceid(deviceId);
            user.setBrandname(brandName);
            user.setPhone(phoneNum);
            user.setCreatetime(new Date());
            user.setUsrName(usrName);
            userRepository.save(user);
        }
        map.put("code", 0);
        map.put("usrId", usrId);
        map.put("usrName", usrName);
        map.put("usrFace", usrFace);
        return map;
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
        //        int visitCount = soup.getVisitcount();
//        visitCount++;
//        soup.setVisitcount(visitCount);
//        soupRepository.save(soup);
        return soupRepository.findAll(new Sort(Sort.Direction.DESC, "createtime")).get(0);
    }

    public List<Tb_Soup> getSoupList() {
        return soupRepository.findAll(new Sort(Sort.Direction.DESC, "createtime"));
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
        commentRepository.save(comment);
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
        return commentRepository.findAll(ex, new Sort(Sort.Direction.ASC, "createtime"));
    }
}