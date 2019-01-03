package com.fruitsalesplatform.service;

import com.fruitsalesplatform.entity.Commodities;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author dpcraft
 * @date 2019/1/3
 * @time 15:00
 */
public interface CommoditiesService {
    Commodities get(Serializable id);
    List<Commodities> find(Map map);
    void insert(Commodities commodities);
    void update(Commodities commodities);
    void deleteById(Serializable id);
    void delete(Serializable[] ids);
    int count(Map map);
}
