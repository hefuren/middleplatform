package com.bluesky.middleplatform.usermanagementservice.dao.impl;

import com.bluesky.middleplatform.commons.db.mybatis.dao.impl.BaseSimpleDataDAOImpl;
import com.bluesky.middleplatform.commons.hierarchy.Hierarchy;
import com.bluesky.middleplatform.commons.hierarchy.Hierarchyable;
import com.bluesky.middleplatform.usermanagementservice.dao.DepartmentDAO;
import com.bluesky.middleplatform.usermanagementservice.mapper.DepartmentMapper;
import com.bluesky.middleplatform.usermanagementservice.model.Department;
import com.bluesky.middleplatform.usermanagementservice.model.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository(value = "DepartmentDAOImpl")
public class DepartmentDAOImpl extends BaseSimpleDataDAOImpl<Department> implements DepartmentDAO<Department> {

    /**
     * 初始化通用的MapperType
     */
    @Override
    public void initMapperType() {
        mapperType = DepartmentMapper.class;
    }

    @Override
    public void saveDepartment(Hierarchy hierarchy) throws Exception {
        log.debug("saving Department instance");
        try{
            Mapper<Department> mapper = this.getMapper(sqlSession, mapperType);
            //查询数据库获取该公司的所有组织结构
            List<Department> departments = this.getTenantModes(Department.ROOT_NODE);
            Set<Integer> depidSet = new HashSet<Integer>();
            for(Department dep : departments){
                depidSet.add(new Integer(dep.getId()));
            }
            //获取当前Deartment hierarchy中的对象
            Hierarchyable rootNode = hierarchy.getRootNode();
            List<Hierarchyable> modes = hierarchy.getAllChildren(rootNode);
            modes.add(rootNode);
            //查找被删部门的ID
            Set<Integer> delDepidSet = new HashSet<Integer>();
            for(Hierarchyable mode : modes){
                boolean flag = depidSet.contains(new Integer(mode.getId()));
                if(!flag){
                    delDepidSet.add(new Integer(mode.getId()));
                }
            }
            //删除指定被删除的部门
            Example example = new Example(Department.class);
            example.createCriteria().andEqualTo("companyID", new Integer(Department.ROOT_NODE));
            example.createCriteria().andIn("id", delDepidSet);
            mapper.deleteByExample(example);

            //新增和更新当前 hierarchy 中的department 对象
            for(Hierarchyable mode : modes){
                Department dept = (Department)mode;
                if(dept.isNew()){
                    mapper.insert(dept);
                } else {
                    mapper.updateByPrimaryKey(dept);
                }
            }
        }catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }


    }

    @Override
    public Hierarchy getDepartmentTree(int tenantId) throws Exception {
        Hierarchy hierarchy = new Hierarchy(Department.SeqComparator);
        Department rootNode = getMode(Department.ROOT_NODE);
        hierarchy.setRootNode(rootNode);

        List<Department> subDepts = this.getTenantModes(tenantId);
        for(Department subDept : subDepts){
            hierarchy.addObject(subDept);
        }
        return hierarchy;
    }

    @Override
    public void deleteDepartment(int depmentId) throws Exception {
        log.debug("delete Department instance");
        try {
            Mapper<Department> mapper = this.getMapper(this.sqlSession, this.mapperType);
            Example example = new Example(entityClass);
            example.createCriteria().andEqualTo("id", depmentId);
            mapper.deleteByExample(example);
            log.debug("delete Department successful");
        } catch (RuntimeException re) {
            log.error("delete Department failed", re);
            throw re;
        }
    }
}
