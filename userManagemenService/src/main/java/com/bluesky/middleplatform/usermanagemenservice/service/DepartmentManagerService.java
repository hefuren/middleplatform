package com.bluesky.middleplatform.usermanagemenservice.service;

import com.bluesky.middleplatform.commons.hierarchy.Hierarchy;
import com.bluesky.middleplatform.usermanagemenservice.model.Department;
import org.springframework.stereotype.Service;

/**
 * 部门管理Service
 *
 * @author ElwinHe
 */
@Service(value = "DepartmentManagerService")
public class DepartmentManagerService implements DepartmentManager {
    @Override
    public void saveDepartment(Hierarchy hierarchy) {

    }

    @Override
    public void newDepartment(Department mode) {

    }

    @Override
    public void updateDepartment(Department department) {

    }

    @Override
    public void deleteDepartment(int depmentId) {

    }

    @Override
    public void deleteDepartments(int[] ids) {

    }

    @Override
    public Department getDepartment(int depmentId) {
        return null;
    }

    @Override
    public Hierarchy getDepartmentTree(int tenantId) {
        return null;
    }
}
