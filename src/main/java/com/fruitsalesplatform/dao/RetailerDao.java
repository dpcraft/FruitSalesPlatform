package com.fruitsalesplatform.dao;

import com.fruitsalesplatform.entity.Retailer;

import java.util.Map;

/**
 * @author dpcraft
 * @date 2018/12/31
 * @time 13:51
 */
public interface RetailerDao extends BaseDao<Retailer> {
    int count(Map map);
}
