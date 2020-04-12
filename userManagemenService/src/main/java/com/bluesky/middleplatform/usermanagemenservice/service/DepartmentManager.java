package com.bluesky.middleplatform.usermanagemenservice.service;

import com.bluesky.middleplatform.commons.hierarchy.Hierarchy;
import com.bluesky.middleplatform.usermanagemenservice.model.Department;

public interface DepartmentManager {

    /**
     * 保存部门
     * 附注：以hierarchy结构保存部门树，hiberchy中department对象需要标识“新建”或“更新”
     *
     * @param hierarchy
     */
    public void saveDepartment(Hierarchy hierarchy);

    /**
     * 新增部门
     *
     * @param mode
     */
    public void newDepartment(Department mode);

    /**
     * 更新部门
     *
     * @param department
     * @
     */
    public void updateDepartment(Department department);

    /**
     * 删除部门对象
     *
     * @param depmentId
     */
    public void deleteDepartment(int depmentId);

    /**
     * 通过ID集批量删除部门对象
     *
     * @param ids
     */
    public void deleteDepartments(int[] ids);

    /**
     * 获取部门
     *
     * @param depmentId
     * @return
     * @
     */
    public Department getDepartment(int depmentId);

    /**
     * 通过租户ID获取系统部门组织结构
     *
     * @return
     * @
     */
    public Hierarchy getDepartmentTree(int tenantId);
}
