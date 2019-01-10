package com.zyk.bolo.service;

import com.zyk.bolo.Constant;
import com.zyk.bolo.entity.Tb_User;
import com.zyk.bolo.repository.UserRepository;
import com.zyk.bolo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Map<String, Object> modUserInfo(String usrId, String usrName, String usrFaceUrl) {
        HashMap<String, Object> map = new HashMap<>();

        if (!userRepository.existsById(usrId)) {
            return ResultUtils.fail(null, Constant.MSG_INVALID_ID);
        }
        Tb_User tbUser = userRepository.getOne(usrId);
        map.put("code", 0);
        if (!StringUtils.isEmpty(usrName)) {
            tbUser.setUsrName(usrName);
            map.put("usrName", usrName);
        } else {
            map.put("usrName", tbUser.getUsrName());
        }
        if (!StringUtils.isEmpty(usrFaceUrl)) {
            tbUser.setFace_url(usrFaceUrl);
            map.put("usrFace", usrFaceUrl);
        } else {
            map.put("usrFace", tbUser.getFace_url());
        }
        userRepository.save(tbUser);
        return map;
    }
}
