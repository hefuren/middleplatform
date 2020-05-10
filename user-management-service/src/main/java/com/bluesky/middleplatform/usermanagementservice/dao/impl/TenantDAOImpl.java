package com.bluesky.middleplatform.usermanagementservice.dao.impl;

import com.bluesky.middleplatform.commons.db.mybatis.dao.impl.BaseSimpleDataDAOImpl;
import com.bluesky.middleplatform.usermanagementservice.dao.TenantDAO;
import com.bluesky.middleplatform.usermanagementservice.mapper.TenantMapper;
import com.bluesky.middleplatform.usermanagementservice.model.Tenant;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository(value = "TenantDAOImpl")
public class TenantDAOImpl extends BaseSimpleDataDAOImpl<Tenant> implements TenantDAO<Tenant> {

    /**
     * 初始化通用的MapperType
     */
    @Override
    public void initMapperType() {
        mapperType = TenantMapper.class;
    }

    @Override
    public void activateTenant(Tenant mode) {
        log.debug("Active Tenant/Company begining...");
        try {
            Mapper<Tenant> mapper = this.getMapper(sqlSession, mapperType);
            mode.setStatus(Tenant.STATUS_ACTIVATED);
            mapper.updateByPrimaryKey(mode);
            log.debug("Active Tenant/Company successful");
        } catch (RuntimeException re) {
            log.error("Active Tenant/Company failed", re);
            throw re;
        }

    }

    @Override
    public void expireTenant(Tenant mode) {
        log.debug("expire Tenant/Company begining...");
        try {
            Mapper<Tenant> mapper = this.getMapper(sqlSession, mapperType);
            mode.setStatus(Tenant.STATUS_UNACTIVATED);
            mapper.updateByPrimaryKey(mode);
            log.debug("expire Tenant/CCompany successful");
        } catch (RuntimeException re) {
            log.error("expire Tenant/Company failed", re);
            throw re;
        }

    }

    /**
     * 删除公司时，需要删除公司/租户的所有数据
     */
    @Override
    public void deleteMode(Tenant mode){
        // TODO Auto-generated method stub

    }
}
