package com.zyk.bolo.service;

import com.zyk.bolo.entity.Tb_User;
import com.zyk.bolo.repository.UserRepository;
import org.eclipse.jetty.websocket.common.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public int upLoadUsrFace(String usrId, String usrFaceUrl) {
        if (userRepository.existsById(usrId)) {
            Tb_User tbUser = userRepository.findDistinctById(usrId);
            tbUser.setFace_url(usrFaceUrl);
            userRepository.save(tbUser);
        } else {
            return -1;
        }
        return 0;
    }

    public int modUsrInfo(String usrId, Tb_User usr) {
        if (userRepository.existsById(usrId)) {
            Tb_User tbUser = userRepository.findDistinctById(usrId);
            if (StringUtils.isEmpty(usr.getUsrName())) {
                tbUser.setUsrName(usr.getUsrName());
            }
            userRepository.save(tbUser);
        } else {
            return -1;
        }
        return 0;
    }
}
