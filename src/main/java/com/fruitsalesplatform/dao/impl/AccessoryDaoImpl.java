package com.fruitsalesplatform.dao.impl;

import com.fruitsalesplatform.dao.AccessoryDao;
import com.fruitsalesplatform.entity.Accessory;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author dpcraft
 * @date 2019/1/3
 * @time 20:11
 */
@Repository
public class AccessoryDaoImpl extends BaseDaoImpl<Accessory> implements AccessoryDao {
    public AccessoryDaoImpl() {
        super.setNs("com.fruitsalesplatform.mapper.AccessoryMapper");
    }
    @Override
    public int count(Map map) {
        return this.getSqlSession().selectOne(this.getNs() + ".count", map);
    }

    @Override
    public int deleteByFruitId(String fruitId) {
       return this.getSqlSession().delete(this.getNs() + ".deleteByFruitId",fruitId);
    }
}
