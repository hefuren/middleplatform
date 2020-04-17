package com.bluesky.middleplatform.commons.db.mybatis.utils;

import tk.mybatis.mapper.common.Mapper;

/**
 * 实现通用批量操作Mapper
 * 特别注意，该接口不能被扫描到，否则会出错
 * @param <T>
 * @author ElwinHe
 */
public interface BatchMapper<T> extends Mapper<T>, BatchInsertMapper<T>, BatchUpdateMapper<T> {

}
