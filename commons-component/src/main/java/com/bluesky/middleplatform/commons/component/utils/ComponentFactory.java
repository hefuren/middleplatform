package com.bluesky.middleplatform.commons.component.utils;

import com.bluesky.middleplatform.commons.utils.BaseContext;
import org.springframework.context.ApplicationContext;

public class ComponentFactory {

    /**
     * 获取各个模块业务操作接口
     *
     * @param className 附注：各个模块Manager接口与ManagerService必须在同一个包内，并且遵从统一的命名规则XxxManager,XxxManagerService
     * @return
     */
    public static Object getManager(String className) {
        ApplicationContext context = BaseContext.getApplicationContext();
        Object manager = null;
        if (className != null && !"".equals(className)) {
            try {
                String managerServiceName = className + "Service";
                manager = context.getBean(managerServiceName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return manager;

    }
}
