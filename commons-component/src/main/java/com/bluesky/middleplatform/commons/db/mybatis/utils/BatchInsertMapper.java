package com.bluesky.middleplatform.commons.db.mybatis.utils;

import com.bluesky.middleplatform.commons.db.mybatis.provider.BatchInsertProvider;
import org.apache.ibatis.annotations.InsertProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

/**
 * 批量操作接口
 *
 * @param <T>
 */
@RegisterMapper
public interface BatchInsertMapper<T> {

    /**
     * 批量插入，支持批量插入的数据库可以使用
     *
     * @return
     */
    @InsertProvider(type = BatchInsertProvider.class, method = "dynamicSQL")
    int insertList(List<T> modes);
}
