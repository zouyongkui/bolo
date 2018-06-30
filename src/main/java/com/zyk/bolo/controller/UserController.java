package com.zyk.bolo.controller;

import com.alibaba.fastjson.JSONObject;
import com.zyk.bolo.service.UserService;
import com.zyk.bolo.utils.UploadUtils;
import org.eclipse.jetty.security.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/modUsrFace")
    public JSONObject modUsrFace(@RequestParam(value = "usrId") String usrId,
                                 @RequestParam(value = "usrFace") MultipartFile usrFace) {
        String usrFaceUrl = UploadUtils.upload("usr" + usrId, usrFace);
        int code = userService.upLoadUsrFace(usrId, usrFaceUrl);
        JSONObject object = new JSONObject();
        object.put("code", code);
        object.put("msg", "suc");
        return object;
    }

    @PostMapping(value = "/modUsrName")
    public JSONObject modUsrName(@RequestParam(value = "usrId") String usrId,
                                 @RequestParam(value = "usrName") String usrName) {
        int code = userService.modUsrName(usrId, usrName);
        JSONObject object = new JSONObject();
        object.put("code", code);
        object.put("msg", "suc");
        return object;
    }

}
