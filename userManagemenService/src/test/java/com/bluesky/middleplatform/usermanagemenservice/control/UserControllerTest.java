package com.bluesky.middleplatform.usermanagemenservice.control;

import com.alibaba.fastjson.JSON;
import com.bluesky.middleplatform.commons.utils.BaseContext;
import com.bluesky.middleplatform.usermanagemenservice.BaseTestCase;
import com.bluesky.middleplatform.usermanagemenservice.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

public class UserControllerTest extends BaseTestCase {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void newUser() {

        System.out.println("================ test start ===============");

        ApplicationContext ctx = BaseContext.getApplicationContext();
        UserController controller = ctx.getBean("UserController", UserController.class);
        User user = ctx.getBean("User", User.class);
        user.setId(new Integer(1000));
        user.setName("elwin");

        String userJson = JSON.toJSONString(user);

        controller.newUser(userJson);


    }

    @Test
    public void userList() {
    }
}