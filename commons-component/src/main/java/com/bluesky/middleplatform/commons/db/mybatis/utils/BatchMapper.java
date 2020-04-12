package com.bluesky.middleplatform.commons.db.mybatis.utils;

import tk.mybatis.mapper.common.Mapper;

/**
 * 实现通用批量操作Mapper
 *
 * @param <T>
 * @author ElwinHe
 */
public interface BatchMapper<T> extends Mapper<T>, BatchInsertMapper<T>, BatchUpdateMapper<T> {

}
