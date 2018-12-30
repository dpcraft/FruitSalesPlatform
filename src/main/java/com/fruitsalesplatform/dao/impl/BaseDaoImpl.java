package com.fruitsalesplatform.dao.impl;

import com.fruitsalesplatform.dao.BaseDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author dpcraft
 * @date 2018-12-30
 * @time 13:37
 */
public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {

    @Override
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    /**
     * 命名空间
     */
    private String ns;

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }

    @Override
    public List<T> find(Map map) {
        List<T> oList = this.getSqlSession().selectList(ns + ".find", map);
        return oList;
    }

    @Override
    public T get(Serializable id) {
        return this.getSqlSession().selectOne(ns + ".get", id);
    }

    @Override
    public void insert(T entity) {
        this.getSqlSession().insert(ns + ".insert", entity);
    }

    @Override
    public void update(T entity) {
        this.getSqlSession().update(ns + ".update", entity);
    }

    @Override
    public void deleteById(Serializable id) {
        this.getSqlSession().delete(ns + ".deleteById", id);
    }

    @Override
    public void delete(Serializable[] ids) {
        this.getSqlSession().delete(ns + ".delete", ids);
    }
}
