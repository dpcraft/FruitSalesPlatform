package com.fruitsalesplatform.dao;

import com.fruitsalesplatform.entity.Commodities;

import java.util.Map;

/**
 * @author dpcraft
 * @date 2019/1/3
 * @time 14:56
 */
public interface CommoditiesDao extends BaseDao<Commodities> {
    int count(Map map);
}
