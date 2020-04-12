package com.bluesky.middleplatform.usermanagemenservice.dao;

import com.bluesky.middleplatform.commons.db.mybatis.dao.BaseSimpleDataDAO;
import com.bluesky.middleplatform.usermanagemenservice.model.User;

public interface UserDAO<T> extends BaseSimpleDataDAO<T> {

    public T getAdminUser();

    /**
     * 通过用户名获取
     *
     * @param username 用戶名
     * @param tenantId 租戶ID
     * @return
     */
    public User getUser(String username, int tenantId);
}
