package com.bluesky.middleplatform.usermanagementservice;

import com.bluesky.middleplatform.commons.utils.BaseContext;
import com.bluesky.middleplatform.usermanagementservice.common.BaseContext1;
import com.bluesky.middleplatform.usermanagementservice.common.SpringContextUtil;
import com.bluesky.middleplatform.usermanagementservice.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningApplicationTests {

    @Test
    public void contextLoads() {

//        User user = SpringContextUtil.getBean("User", User.class);
        ApplicationContext ctx = BaseContext1.getApplicationContext();
        User user =  ctx.getBean("User", User.class);

        if(user != null){
            System.out.println("============== getBean is ok !");
        }else {
            System.out.println("========get Bean is wrong");
        }

    }

}
