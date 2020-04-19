package com.bluesky.middleplatform.usermanagementservice.dao;

import com.bluesky.middleplatform.commons.db.mybatis.dao.BaseSimpleDataDAO;
import com.bluesky.middleplatform.usermanagementservice.model.Tenant;

public interface TenantDAO<T> extends BaseSimpleDataDAO<T> {

    /**
     * 激活租户/公司对象
     * @param mode
     */
    public void  activateCompany(Tenant mode);

    /**
     * 将租户/公司对象设置为未激活
     * @param mode
     */
    public void expireCompany(Tenant mode);
}
