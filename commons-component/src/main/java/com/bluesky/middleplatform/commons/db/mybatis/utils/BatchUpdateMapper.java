package com.bluesky.middleplatform.commons.db.mybatis.utils;

import tk.mybatis.mapper.annotation.RegisterMapper;

@RegisterMapper
public interface BatchUpdateMapper<T> {

    /**
     * 批量更新对象方法
     * @param list
     * @return
     */
//	 @UpdateProvider(type = BatchUpdateProvider.class, method = "dynamicSQL")
//	 public int updateList(List<T> list);

}
