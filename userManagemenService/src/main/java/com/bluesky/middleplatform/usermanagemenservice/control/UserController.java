package com.bluesky.middleplatform.usermanagemenservice.control;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bluesky.middleplatform.commons.component.utils.ComponentFactory;
import com.bluesky.middleplatform.commons.utils.BaseController;
import com.bluesky.middleplatform.usermanagemenservice.model.User;
import com.bluesky.middleplatform.usermanagemenservice.service.ProfileManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * User控制器
 * RESTful 接口实现
 *
 * @author ElwinHe
 */


@RestController
@RequestMapping(value = "/UserAction.html")
public class UserController extends BaseController {

//    @Override
//    public String setForwardPath(){
//        this.forwardPath = "profile/";
//        return forwardPath;
//    }

    /**
     * @param userJson (User对象JSON字符串)
     * @return userJson
     */
    @PostMapping(value = "/newUser")
    public String newUser(String userJson) {
        ProfileManager manager = (ProfileManager) ComponentFactory.getManager("ProfileManager");
        User user = JSON.parseObject(userJson, User.class);

        manager.newUser(user);

        return userJson;
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @GetMapping(value = "/userList")
    public String userList(String paramJson) {

        JSONObject jsonObject = JSONObject.parseObject(paramJson);


        return null;
    }


}
