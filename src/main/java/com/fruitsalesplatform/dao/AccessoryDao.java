package com.fruitsalesplatform.dao;

import com.fruitsalesplatform.entity.Accessory;

import java.util.Map;

/**
 * @author dpcraft
 * @date 2019/1/3
 * @time 20:10
 */
public interface AccessoryDao extends BaseDao<Accessory> {
    int count(Map map);
    int deleteByFruitId(String fruitId);
}
