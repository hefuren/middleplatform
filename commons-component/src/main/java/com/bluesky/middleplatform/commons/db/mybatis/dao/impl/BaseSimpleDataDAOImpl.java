package com.bluesky.middleplatform.commons.db.mybatis.dao.impl;

import com.bluesky.middleplatform.commons.db.PageInfo;
import com.bluesky.middleplatform.commons.db.mybatis.dao.BaseSimpleDataDAO;
import com.bluesky.middleplatform.commons.db.mybatis.utils.BatchMapper;
import com.bluesky.middleplatform.commons.object.BatchObject;
import com.bluesky.middleplatform.commons.utils.TypeUtils;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;


/**
 * 通用MyBatis DAO实现层，主要用于单表操作
 *
 * @param <T> 平台考虑SaaS模式，在对数据库操作时，需要判断用户权限，仅允许操作所在公司/租户的数据，
 * @author ElwinHe
 */

@Repository(value = "BaseSimpleDataDAOImpl")
public abstract class BaseSimpleDataDAOImpl<T> extends SqlSessionDaoSupport implements BaseSimpleDataDAO<T> {

    public static final Logger log = LoggerFactory.getLogger(BaseSimpleDataDAOImpl.class);

    /**
     * 实体类对象
     */
    protected Class<T> entityClass;

    /**
     * 类名
     */
    protected String className;

    /**
     * 当前具体的Mapper对象接口
     * 如：UserMapper.class,RoleMapper.class等
     */
    protected Class mapperType;

    protected SqlSession sqlSession;

    @Override
    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        super.setSqlSessionTemplate(sqlSessionTemplate);
        sqlSession = this.getSqlSession();
    }

    public BaseSimpleDataDAOImpl() {
        entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        className = entityClass.getName();
    }

    /**
     * 初始化当前MapperType
     */
    @PostConstruct
    public abstract void initMapperType();


    @Override
    public T getMode(int id) {
        log.debug("getting " + className + " instance with id: " + id);
        try {
            Mapper<T> mapper = this.getMapper(sqlSession, mapperType);
            T instance = mapper.selectByPrimaryKey(new Integer(id));
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    @Override
    public List<T> getModes(int[] ids) {
        log.debug("getting " + className + " instance with ids. ");
        try {
            Example example = new Example(entityClass);
            Set idSet = new HashSet<>();
            for (int id : ids) {
                idSet.add(new Integer(id));
            }
            example.createCriteria().andIn("id", idSet);

            Mapper<T> mapper = this.getMapper(sqlSession, mapperType);
            List<T> modes = mapper.selectByExample(example);
            return modes;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    @Override
    public void newMode(T t) {
        log.debug("saving " + className + " instance");
        try {
            Mapper<T> mapper = this.getMapper(sqlSession, mapperType);
            mapper.insert(t);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    @Override
    public void batchNewModes(List<T> modes) {
        log.debug("saving " + className + " instance");
        try {
            BatchMapper<T> mapper = this.getBatchMapper(sqlSession, mapperType);
            if (modes != null && modes.size() > 0) {
                mapper.insertList(modes);
            }
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    @Override
    public void updateMode(T t) {
        log.debug("updating " + className + " instance");
        try {
            Mapper<T> mapper = this.getMapper(sqlSession, mapperType);
            mapper.updateByPrimaryKey(t);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }

    }

    @Override
    public void batchUpdateModes(List<T> modes) {
        log.debug("batch updating " + className + " instance");
        try {
            Mapper<T> mapper = this.getMapper(sqlSession, mapperType);
//			BatchMapper<T> mapper = this.getBatchMapper(sqlSession, mapperType);
//			mapper.updateList(modes);
            for (T t : modes) {
                mapper.updateByPrimaryKey(t);
            }
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("batch update failed", re);
            throw re;
        }

    }

    @Override
    public void saveModes(List<T> modes) {
        log.debug("batch save " + className + " instance");
        try {
            List<T> newList = new ArrayList<T>();
            List<T> updateList = new ArrayList<T>();
            Set<Integer> delSet = new HashSet<Integer>();

            for (T t : modes) {
                BatchObject obj = (BatchObject) t;
                if (obj.isNew()) {
                    newList.add(t);
                } else if (obj.isDeleted()) {
                    delSet.add(new Integer(obj.getId()));
                } else if (obj.isModified()) {
                    updateList.add(t);
                }
            }
            //批量新增、修改和删除
            batchNewModes(newList);
            batchUpdateModes(updateList);
            int[] ids = new int[delSet.size()];
            int count = 0;
            for (Integer v : delSet) {
                ids[count++] = TypeUtils.nullToInt(v);
            }
            batchDeleteModes(ids);
            log.debug("batch save successful");
        } catch (RuntimeException re) {
            log.error("batch save failed", re);
            throw re;
        }

    }

    @Override
    public void deleteMode(T t) {
        log.debug("deleting " + className + " instance");
        try {
            Mapper<T> mapper = this.getMapper(sqlSession, mapperType);
            mapper.delete(t);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }

    }

    @Override
    public void deleteModesByExample(Example example) {
        log.debug("deleting " + className + " instance");
        try {
            Mapper<T> mapper = this.getMapper(sqlSession, mapperType);
            mapper.deleteByExample(example);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }

    }

    @Override
    public void batchDeleteModes(int[] ids) {
        log.debug("deleting " + className + " instance");
        try {
            if (ids != null && ids.length > 0) {
                Mapper<T> mapper = this.getMapper(sqlSession, mapperType);
                List<Object> idList = TypeUtils.arrayToList(ids);
                Example example = new Example(entityClass);
                example.createCriteria().andIn("id", idList);
                mapper.deleteByExample(example);
//				for(int id : ids){
//					mapper.deleteByPrimaryKey(new Integer(id));
//				}
                log.debug("delete successful");
            }
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }

    }

    @Override
    public List<T> getTenantModes(int tenantID) {
        log.debug("finding all " + className + " instances");
        try {
            Example example = new Example(entityClass);
            example.createCriteria().andEqualTo("companyID", new Integer(tenantID));

            Mapper<T> mapper = this.getMapper(sqlSession, mapperType);
            List<T> modes = mapper.selectByExample(example);
            return modes;
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    @Override
    public T getModeByProperty(String propertyName, Object value) {
        log.debug("finding all " + className + " instances");
        try {
            Example example = new Example(entityClass);
            example.createCriteria().andEqualTo(propertyName, value);

            Mapper<T> mapper = this.getMapper(sqlSession, mapperType);
            List<T> modes = mapper.selectByExample(example);
            T mode = null;
            if (modes.size() > 0 && modes.size() == 1) {
                mode = modes.get(0);
            }
            return mode;
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    @Override
    public List<T> getModesByProperty(String propertyName, Object value) {
        log.debug("finding all " + className + " instances");
        try {
            Example example = new Example(entityClass);
            example.createCriteria().andEqualTo(propertyName, value);

            Mapper<T> mapper = this.getMapper(sqlSession, mapperType);
            List<T> modes = mapper.selectByExample(example);
            return modes;
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    @Override
    public List<T> getModesByExample(T t) {
        log.debug("finding all " + className + " instances");
        try {
            Mapper<T> mapper = this.getMapper(sqlSession, mapperType);
            List<T> modes = mapper.select(t);
            return modes;
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    @Override
    public PageInfo getByPageInfo(PageInfo pageInfo) {
        log.debug("finding all " + className + " instances");
        try {
            Map<String, Object> conditions = (Map<String, Object>) pageInfo.getConditions();
            List items = new ArrayList();
            String[] paramNames = null;

            Example example = pageInfo.getExample();
            if (example == null) {
                example = new Example(entityClass);
            }

            if (conditions != null && conditions.size() > 0) {
                // 带条件查询
                paramNames = new String[conditions.size()];
                conditions.keySet().toArray(paramNames);
                for (String paramName : paramNames) {
                    example.createCriteria().andEqualTo(paramName, conditions.get(paramName));
                }
            }

            Mapper<T> mapper = this.getMapper(sqlSession, mapperType);
            if (pageInfo.isPaged()) {
                // 分页查询
                int totalRows = mapper.selectCountByExample(example);
                pageInfo.setTotalRows(totalRows);
                int pageCounts = (totalRows % pageInfo.getPageSize() > 0) ? totalRows / pageInfo.getPageSize() + 1 : totalRows / pageInfo.getPageSize();
                pageInfo.setPageCount(pageCounts);
                int currentPage = pageInfo.getCurrentPage();
                int pageSize = pageInfo.getPageSize();

                //在需要进行分页的Mybatis方法前调用PageHelper.startPage静态方法，紧跟在这个方法后的第一个Mybatis查询方法会被进行分页。
                PageHelper.startPage(currentPage, pageSize);
                items = mapper.selectByExample(example);

            } else {
                items = mapper.selectByExample(example);
            }
            pageInfo.setItems(items);

            return pageInfo;
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /**
     * 获取当前的XxxMapper对象
     *
     * @param sqlSession 当前SqlSession
     * @param type       Mapper的具体类型，如: UserMapper,RoleMapper,DepartmentMapper,...
     * @return
     */
    protected Mapper<T> getMapper(SqlSession sqlSession, Class type) {
        Mapper<T> mapper = (Mapper<T>) sqlSession.getMapper(type);
        return mapper;
    }

    /**
     * 获取当前的XxxMapper对象
     *
     * @param sqlSession 当前SqlSession
     * @param type       Mapper的具体类型，如: UserMapper,RoleMapper,DepartmentMapper,...
     * @return
     */
    protected BatchMapper<T> getBatchMapper(SqlSession sqlSession, Class type) {
        BatchMapper<T> mapper = (BatchMapper<T>) sqlSession.getMapper(type);
        return mapper;
    }

}
