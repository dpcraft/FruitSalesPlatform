package com.fruitsalesplatform.service.impl;

import com.fruitsalesplatform.dao.AccessoryDao;
import com.fruitsalesplatform.entity.Accessory;
import com.fruitsalesplatform.service.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author dpcraft
 * @date 2019/1/3
 * @time 20:19
 */
@Service
public class AccessoryServiceImpl implements AccessoryService {
    @Autowired
    AccessoryDao accessoryDao;
    @Override
    public Accessory get(Serializable id) {
        return accessoryDao.get(id);
    }

    @Override
    public List<Accessory> find(Map map) {
        return accessoryDao.find(map);
    }

    @Override
    public void insert(Accessory accessory) {
        accessoryDao.insert(accessory);

    }

    @Override
    public void update(Accessory accessory) {
        accessoryDao.update(accessory);

    }

    @Override
    public void deleteById(String id) {
        accessoryDao.deleteById(id);

    }

    @Override
    public void delete(Serializable[] ids) {
        accessoryDao.delete(ids);

    }

    @Override
    public int deleteByFruitId(String fruitId) {
        return accessoryDao.deleteByFruitId(fruitId);

    }
}
