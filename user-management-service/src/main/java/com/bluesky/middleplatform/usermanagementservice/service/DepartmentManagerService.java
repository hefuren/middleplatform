package com.bluesky.middleplatform.usermanagementservice.service;

import com.bluesky.middleplatform.commons.hierarchy.Hierarchy;
import com.bluesky.middleplatform.usermanagementservice.dao.DepartmentDAO;
import com.bluesky.middleplatform.usermanagementservice.model.Department;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 部门管理Service
 *
 * @author ElwinHe
 */
@Service(value = "DepartmentManagerService")
public class DepartmentManagerService implements DepartmentManager {

    @Resource(name="DepartmentDAOImpl")
    private DepartmentDAO departmentDAO;

    @Override
    public void saveDepartment(Hierarchy hierarchy) {
        try {
            this.departmentDAO.saveDepartment(hierarchy);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void newDepartment(Department mode) {
        try {
            this.departmentDAO.newMode(mode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void updateDepartment(Department department) {
        try {
            this.departmentDAO.updateMode(department);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void deleteDepartment(int depmentId) {
        try {
            this.departmentDAO.deleteMode(depmentId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void deleteDepartments(int[] ids) {
        try {
            this.departmentDAO.batchDeleteModes(ids);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public Department getDepartment(int depmentId) {
        try {
            return (Department) this.departmentDAO.getMode(depmentId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Hierarchy getDepartmentTree(int tenantId) {
        try {
            return this.departmentDAO.getDepartmentTree(tenantId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
