package com.fruitsalesplatform.dao.impl;

import com.fruitsalesplatform.dao.ContractDao;
import com.fruitsalesplatform.entity.Contract;
import com.fruitsalesplatform.entity.ContractVo;
import com.fruitsalesplatform.entity.MiddleTab;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author dpcraft
 * @date 2019-01-04
 * @time 14:12
 */
@Repository
public class ContractDaoImpl extends BaseDaoImpl<Contract> implements ContractDao {
    public ContractDaoImpl() {
        super.setNs("com.fruitsalesplatform.mapper.ContractMapper");
    }

    @Override
    public int count(Map map){
        return this.getSqlSession().selectOne(this.getNs() + ".count",map);
    }

    @Override
    public List<ContractVo> findContractList(Map map) {
        return this.getSqlSession().selectList(this.getNs() + ".findContractList",map);
    }

    @Override
    public void insertMiddleTab(MiddleTab middleTab) {
        this.getSqlSession().selectList(this.getNs() + ".insertMiddleTab", middleTab);

    }

    @Override
    public int deleteMiddleTab(Serializable contractId) {
        return this.getSqlSession().delete(this.getNs() + ".deleteMiddleTab", contractId);
    }

    @Override
    public String getMaxBarCode() {
        return this.getSqlSession().selectOne(this.getNs() + ".getMaxBarCode");
    }
}
