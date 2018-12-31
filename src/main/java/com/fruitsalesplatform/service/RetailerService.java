package com.fruitsalesplatform.service;

import com.fruitsalesplatform.entity.Retailer;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author dpcraft
 * @date 2018/12/31
 * @time 14:15
 */
public interface RetailerService {
    Retailer get(Serializable id);
    List<Retailer> find(Map map);
    void insert(Retailer retailer);
    void update(Retailer retailer);
    void deleteById(Serializable id);
    void delete(Serializable[] ids);
    int count(Map map);
}
