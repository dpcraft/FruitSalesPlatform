package com.fruitsalesplatform.dao;

import com.fruitsalesplatform.entity.Contract;
import com.fruitsalesplatform.entity.ContractVo;
import com.fruitsalesplatform.entity.MiddleTab;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author dpcraft
 * @date 2019-01-04
 * @time 14:09
 */
public interface ContractDao extends BaseDao<Contract>{
    int count(Map map);
    List<ContractVo> findContractList(Map map);
    void insertMiddleTab(MiddleTab middleTab);
    int deleteMiddleTab(Serializable contractId);
    String getMaxBarCode();

}
