package com.bluesky.middleplatform.usermanagementservice.control;

import com.alibaba.fastjson.JSON;
import com.bluesky.middleplatform.commons.utils.BaseContext;
import com.bluesky.middleplatform.usermanagementservice.BaseTestCase;
import com.bluesky.middleplatform.usermanagementservice.model.User;
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
        UserController controller = ctx.getBean("UserController", UserController.class);
        User user = ctx.getBean("User", User.class);
        user.setId(new Integer(1000));
        user.setName("elwin");

        String userJson = JSON.toJSONString(user);

        controller.newUser(userJson);
        System.out.println("============= hello ruirui !");
    }

    @Test
    void userList() {
    }
}