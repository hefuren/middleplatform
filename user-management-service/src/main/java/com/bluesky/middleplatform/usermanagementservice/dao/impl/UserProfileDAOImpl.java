package com.bluesky.middleplatform.usermanagementservice.dao.impl;

import com.bluesky.middleplatform.commons.db.mybatis.dao.impl.BaseSimpleDataDAOImpl;
import com.bluesky.middleplatform.usermanagementservice.dao.UserProfileDAO;
import com.bluesky.middleplatform.usermanagementservice.mapper.UserMapper;
import com.bluesky.middleplatform.usermanagementservice.model.UserProfile;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository(value = "UserProfileDAOImpl")
public class UserProfileDAOImpl extends BaseSimpleDataDAOImpl<UserProfile> implements UserProfileDAO<UserProfile> {

    /**
     * 初始化通用的MapperType
     */
    @Override
    public void initMapperType() {
        mapperType = UserMapper.class;

    }

    /**
     * 删除用户（标记删除用户）
     * @param userProfile 需要删除的用户
     */
    @Override
    public void deleteMode(UserProfile userProfile)  {
        log.debug("delete " + className + " instance");
        try {
            Mapper<UserProfile> mapper = this.getMapper(sqlSession, mapperType);
            userProfile.setStatus(UserProfile.STATUS_DELETED);
            mapper.updateByPrimaryKey(userProfile);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
}
