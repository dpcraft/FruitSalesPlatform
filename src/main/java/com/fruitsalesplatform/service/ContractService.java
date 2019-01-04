package com.fruitsalesplatform.service;

import com.fruitsalesplatform.entity.Contract;
import com.fruitsalesplatform.entity.ContractVo;
import com.fruitsalesplatform.entity.MiddleTab;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author dpcraft
 * @date 2019-01-04
 * @time 14:22
 */
public interface ContractService {
    Contract get(Serializable id);
    List<ContractVo> findContractList(Map map);
    void insert(Contract contract, String[] commoditiesIdArrays, String[] priceArrays);
    void insertMiddleTab(MiddleTab middleTab);
    void update(Contract contract);
    void deleteById(Serializable contractId);
    void deleteMiddleTab(Serializable contractId);
    int count(Map map);
    String getMaxBarCode();
}
