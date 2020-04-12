package com.bluesky.middleplatform.usermanagemenservice.dao.impl;

import com.bluesky.middleplatform.commons.db.mybatis.dao.impl.BaseSimpleDataDAOImpl;
import com.bluesky.middleplatform.usermanagemenservice.dao.UserDAO;
import com.bluesky.middleplatform.usermanagemenservice.mapper.UserMapper;
import com.bluesky.middleplatform.usermanagemenservice.model.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository(value = "UserDAOImpl")
public class UserDAOImpl extends BaseSimpleDataDAOImpl<User> implements UserDAO<User> {

    /**
     * 初始化通用的MapperType
     */
    @Override
    public void initMapperType() {
        mapperType = UserMapper.class;

    }

    @Override
    public User getAdminUser() {
        log.debug("getting " + className + " instance with ids. ");
        try {
            User adminUser = null;
            //系统管理员ID
            Object value = new Integer(User.SYS_ADMIN_ID);
            Mapper<User> mapper = this.getMapper(sqlSession, mapperType);
            adminUser = mapper.selectByPrimaryKey(value);
            return adminUser;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    @Override
    public User getUser(String username, int tenantId) {
        log.debug("finding all " + className + " instances");
        try {
            Example example = new Example(entityClass);
            example.createCriteria().andEqualTo("name", username);
            example.createCriteria().andEqualTo("tenantId", tenantId);

            Mapper<User> mapper = this.getMapper(sqlSession, mapperType);
            List<User> modes = mapper.selectByExample(example);
            User mode = null;
            if (modes.size() > 0 && modes.size() == 1) {
                mode = modes.get(0);
            }
            return mode;
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 删除用户（标记删除用户）
     *
     * @param model 需要删除的用户
     */
    @Override
    public void deleteMode(User model) {
        log.debug("delete User instance");
        try {
            model.setStatus(new Integer(User.STATUS_DELETED));
            Mapper<User> mapper = this.getMapper(sqlSession, mapperType);
            mapper.updateByPrimaryKey(model);
            log.debug("delete User successful");
        } catch (RuntimeException re) {
            log.error("delete User failed", re);
            throw re;
        }
    }
}
