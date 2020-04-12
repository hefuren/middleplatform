package com.bluesky.middleplatform.usermanagementservice.mapper;

import com.bluesky.middleplatform.commons.db.mybatis.utils.BatchMapper;
import com.bluesky.middleplatform.usermanagementservice.model.User;

/**
 * UserMapper
 * 实现批量操作接口
 *
 * @author ElwinHe
 */
public interface UserMapper extends BatchMapper<User> {
}
