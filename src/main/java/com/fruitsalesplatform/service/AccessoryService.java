package com.fruitsalesplatform.service;

import com.fruitsalesplatform.entity.Accessory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author dpcraft
 * @date 2019/1/3
 * @time 20:15
 */
public interface AccessoryService {
    Accessory get(Serializable id);
    List<Accessory> find(Map map);
    void insert(Accessory accessory);
    void update(Accessory accessory);
    void deleteById(String id);
    void delete(Serializable[] ids);
    int deleteByFruitId(String fruitId);

}
