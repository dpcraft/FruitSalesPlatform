package com.fruitsalesplatform.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author dpcraft
 * @date 2018-12-30
 * @time 13:32
 */
public interface BaseDao<T> {
    T get(Serializable id);
    List<T> find(Map map);
    void insert(T entity);
    void update(T entity);
    void deleteById(Serializable id);
    void delete(Serializable[] ids);

}
