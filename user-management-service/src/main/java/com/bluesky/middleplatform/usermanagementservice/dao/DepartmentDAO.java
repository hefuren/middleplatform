package com.bluesky.middleplatform.usermanagementservice.dao;

import com.bluesky.middleplatform.commons.db.mybatis.dao.BaseSimpleDataDAO;
import com.bluesky.middleplatform.commons.hierarchy.Hierarchy;
import com.bluesky.middleplatform.usermanagementservice.model.User;

public interface DepartmentDAO<T> extends BaseSimpleDataDAO<T> {

    /**
     * 保存组织结构(删除原有的，再新增)
     * @param hierarchy
     * @throws Exception
     */
    public void saveDepartment(Hierarchy hierarchy)  throws Exception;

    /**
     * 通过租户/公司ID获取系统部门组织结构
     * @param tenantId
     * @return
     * @throws Exception
     */
    public Hierarchy getDepartmentTree(int tenantId) throws Exception;

    /**
     * 根据部门ID删除对象
     * @param depmentId
     */
    public void deleteDepartment(int depmentId) throws Exception;
}
