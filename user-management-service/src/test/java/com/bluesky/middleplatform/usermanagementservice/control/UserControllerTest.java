package com.bluesky.middleplatform.usermanagementservice.control;

import com.alibaba.fastjson.JSON;
import com.bluesky.middleplatform.commons.component.utils.ComponentFactory;
import com.bluesky.middleplatform.commons.utils.BaseContext;
import com.bluesky.middleplatform.usermanagementservice.BaseTestCase;
import com.bluesky.middleplatform.usermanagementservice.model.User;
import com.bluesky.middleplatform.usermanagementservice.service.ProfileManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest extends BaseTestCase {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void newUser() {
        ApplicationContext ctx = BaseContext.getApplicationContext();
        ProfileManager manager = (ProfileManager) ComponentFactory.getManager("ProfileManager");
        UserController controller = ctx.getBean("UserController", UserController.class);
        User user = ctx.getBean("User", User.class);
        user.setId(new Integer(1000));
        user.setName("elwin");
        user.setTenantId(new Integer(1000));

        manager.newUser(user);
//        String userJson = JSON.toJSONString(user);
//
//        controller.newUser(userJson);
        System.out.println("============= hello ruirui !");
    }

//
//    @Test
//    void userList() {
//    }
//
//    @Test
//    void testNewUser() {
//    }
//
//    @Test
//    void testUserList() {
//    }

}