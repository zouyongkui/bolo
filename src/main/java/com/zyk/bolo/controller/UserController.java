package com.zyk.bolo.controller;

import com.zyk.bolo.service.UserService;
import com.zyk.bolo.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/modUsrInfo")
    public Map<String, Object> modUsrInfo(@RequestParam(value = "usrId") String usrId,
                                          @RequestParam(value = "usrName", required = false) String usrName,
                                          @RequestParam(value = "usrFace", required = false) MultipartFile usrFace) {
        String usrFaceUrl = UploadUtils.upload("usr" + usrId, usrFace);
        return userService.modUserInfo(usrId, usrName, usrFaceUrl);
    }
}
