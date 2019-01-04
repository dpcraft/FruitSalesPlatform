package com.fruitsalesplatform.service.impl;

import com.fruitsalesplatform.dao.ContractDao;
import com.fruitsalesplatform.entity.Contract;
import com.fruitsalesplatform.entity.ContractVo;
import com.fruitsalesplatform.entity.MiddleTab;
import com.fruitsalesplatform.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author dpcraft
 * @date 2019-01-04
 * @time 14:27
 */
@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    ContractDao contractDao;
    @Override
    public List<ContractVo> findContractList(Map map) {
        return contractDao.findContractList(map);
    }

    @Override
    public void insert(Contract contract, String[] commoditiesIdArrays, String[] priceArrays) {
        contractDao.insert(contract);
        for (int i = 0; i < commoditiesIdArrays.length; i++) {
            MiddleTab middleTab = new MiddleTab();
            middleTab.setMiddleId(UUID.randomUUID().toString());
            middleTab.setContractId(contract.getContractId());
            middleTab.setFruitId(commoditiesIdArrays[i]);
            int number = Integer.parseInt(priceArrays[i].equals("") ? "0" : priceArrays[i]);
            middleTab.setNumber(number);
            this.insertMiddleTab(middleTab);

        }
    }

    @Override
    public Contract get(Serializable id) {
        return contractDao.get(id);
    }

    @Override
    public void insertMiddleTab(MiddleTab middleTab) {
        contractDao.insertMiddleTab(middleTab);

    }

    @Override
    public void update(Contract contract) {
        contractDao.update(contract);

    }

    @Override
    public void deleteById(Serializable contractId) {
        contractDao.deleteById(contractId);

    }

    @Override
    public void deleteMiddleTab(Serializable contractId) {
        contractDao.deleteMiddleTab(contractId);

    }

    @Override
    public int count(Map map) {
        return contractDao.count(map);
    }

    @Override
    public String getMaxBarCode() {
        return contractDao.getMaxBarCode();
    }
}
