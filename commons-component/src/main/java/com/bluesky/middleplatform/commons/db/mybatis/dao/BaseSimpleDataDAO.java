package com.bluesky.middleplatform.commons.db.mybatis.dao;


import com.bluesky.middleplatform.commons.db.PageInfo;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 单表操作通用 MyBatis DAO
 * (继承TK Mybatis通用mapper方法)
 *
 * @param <T>
 * @author ElwinHe
 */
public interface BaseSimpleDataDAO<T> {

    /**
     * 通过ID获取对象
     *
     * @param id
     * @return
     */
    public T getMode(int id);

    /**
     * 通过ID数组获取对象
     *
     * @param ids
     * @return
     */
    public List<T> getModes(int[] ids);

    /**
     * 新增对象
     *
     * @param t
     */
    public void newMode(T t);

    /**
     * 批量保存对象
     *
     * @param modes
     */
    public void batchNewModes(List<T> modes);

    /**
     * 更新对象
     *
     * @param t
     */
    public void updateMode(T t);

    /**
     * 批量更新对象
     *
     * @param modes
     */
    public void batchUpdateModes(List<T> modes);

    /**
     * 新增或修改保存
     * List<T> 包括了新增、修改和删除的数据，T对象需要集成BatchObject
     *
     * @param modes
     */
    public void saveModes(List<T> modes);

    /**
     * 删除对象
     *
     * @param t
     */
    public void deleteMode(T t);

    /**
     * 根据example删除对象
     *
     * @param example
     */
    public void deleteModesByExample(Example example);

    /**
     * 根据ID删除对象
     *
     * @param ids
     */
    public void batchDeleteModes(int[] ids);

    /**
     * 通过用户获取当前租户（公司）所有对象
     *
     * @param tenantID
     * @return
     */
    public List<T> getTenantModes(int tenantID);

    /**
     * 根据对象属性获取对象
     *
     * @param propertyName
     * @param value
     * @return
     */
    public T getModeByProperty(String propertyName, Object value);

    /**
     * 根据对象属性获取对象List
     *
     * @param propertyName
     * @param value
     * @return
     */
    public List<T> getModesByProperty(String propertyName, Object value);

    /**
     * 根据对象比较获取对象
     *
     * @param t
     * @return
     */
    public List<T> getModesByExample(T t);

    /**
     * 分页查询
     *
     * @param pageInfo
     * @return
     */
    public PageInfo getByPageInfo(PageInfo pageInfo);
}
