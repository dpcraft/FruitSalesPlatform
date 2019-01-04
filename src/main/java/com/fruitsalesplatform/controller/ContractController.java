package com.fruitsalesplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.fruitsalesplatform.entity.Commodities;
import com.fruitsalesplatform.entity.Contract;
import com.fruitsalesplatform.entity.ContractVo;
import com.fruitsalesplatform.entity.Retailer;
import com.fruitsalesplatform.service.AccessoryService;
import com.fruitsalesplatform.service.CommoditiesService;
import com.fruitsalesplatform.service.ContractService;
import com.fruitsalesplatform.service.RetailerService;
import com.mysql.jdbc.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author dpcraft
 * @date 2019-01-04
 * @time 14:31
 */
@Controller
@RequestMapping("/contract")
public class ContractController extends BaseController {
    @Resource
    ContractService contractService;
    @Resource
    RetailerService retailerService;
    @Resource
    CommoditiesService commoditiesService;
    @Resource
    AccessoryService accessoryService;

    @RequestMapping("/list.action")
    public String list(Model model, ContractVo contractVo,String startTime, String endTime) {
        Map<String,Object> map = this.contractToMap(contractVo);
        if(startTime != null && startTime != "") {
            map.put("startTime",startTime);
        }
        if(endTime != null && endTime != "") {
            map.put("endTime", endTime);
        }
        List<ContractVo> contractList = contractService.findContractList(map);
        model.addAttribute("list", contractList.size() < 1 ? null : contractList);
        model.addAttribute("currentPage",contractVo.getCurrentPage());
        model.addAttribute("startPage",contractVo.getStartPage());
        int countNumber = contractService.count(map);
        model.addAttribute("countNumber", countNumber);
        int pageSize = contractVo.getPageSize();
        model.addAttribute("pageSize",pageSize);
        int sumPageNumber = countNumber % pageSize == 0 ? (countNumber/pageSize) : ((countNumber/pageSize) + 1);
        model.addAttribute("sumPageNumber", sumPageNumber);
        return "/contract/contractHome.jsp";
    }

    @RequestMapping("/toAddPage.action")
    public String toAddPage(Model model) {
        return "/contract/addContract.jsp";
    }

    @RequestMapping("/getAllRetailer.action")
    public @ResponseBody List<Retailer> getAllRetailer(@RequestBody String json) {
        Map<String,Object> param = new HashMap<>();
        param.put("status", 1);
        if(!StringUtils.isNullOrEmpty(json)){
            String name = JSONObject.parseObject(json).getString("name");
            if(!StringUtils.isNullOrEmpty(name)){
                param.put("name", "%" + name + "%");
            }
        }
        List<Retailer> retailerList = retailerService.find(param);
        return retailerList;
    }

    @RequestMapping("/getAllCommodities.action")
    public @ResponseBody List<Commodities> getAllCommodities(@RequestBody String json) {
        Map<String, Object> param = new HashMap<>();
        if(!StringUtils.isNullOrEmpty(json)){
            String name = JSONObject.parseObject(json).getString("name");
            if(!StringUtils.isNullOrEmpty(name)){
                param.put("name", "%" + name + "%");
            }
        }
        List<Commodities> commoditiesList = commoditiesService.find(param);
        return commoditiesList;

    }

    @RequestMapping("/getCommoditiesAndAccessory.action")
    public @ResponseBody List<Map<String,Object>> getCommoditiesAndAccessory(String[] arrays){
        List<Map<String,Object>> cList = new ArrayList<>();
        Map<String,Object> cMap = null;
        for (int i = 0; i < arrays.length; i++) {
            cMap = new HashMap<>();
            String fruitId = arrays[i];
            cMap.put("commodities", commoditiesService.get(fruitId));
            Map<String,String> param = new HashMap<>();
            param.put("fruitId",fruitId);
            cMap.put("accessory", accessoryService.find(param));
            cList.add(cMap);
        }
        return cList;
    }

    @RequestMapping("/add.action")
    public String add(Model model, Contract contract, String retailerId,
                      String[] commoditiesIdArrays, String[] priceArrays){
        contract.setRetailer(retailerService.get(retailerId));
        String barCode = getCode();
        contract.setBarCode(barCode);
        contract.setContractId(UUID.randomUUID().toString());
        contract.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        contractService.insert(contract, commoditiesIdArrays, priceArrays);
        model.addAttribute("resultMessage", "添加成功！ 合同编号为 " + barCode);
        return "/contract/addContract.jsp";

    }
    private Map<String,Object> contractToMap(ContractVo contractVo) {
        Map<String,Object> map = new HashMap<>();
        map.put("barCode", checkStringIsEmpty(contractVo.getBarCode()));
        map.put("retailerName", checkStringIsEmpty(contractVo.getRetailerName()));
        map.put("type", contractVo.getType() == -1 ? null : contractVo.getType());
        map.put("startPage", contractVo.getStartPage());
        map.put("pageSize",contractVo.getPageSize());
        return map;
    }
    private String getCode(){
        String codeHead  = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String barCode = "";
        String maxBarCode = contractService.getMaxBarCode();
        if(!StringUtils.isNullOrEmpty(maxBarCode)) {
            if (maxBarCode.substring(0, 8).equals(codeHead)) {
                maxBarCode = maxBarCode.substring(8);
            } else {
                maxBarCode = "0";
            }
        }else{
            maxBarCode = "0";
        }
        int maxNumber = Integer.parseInt(maxBarCode);
        int newNumber = maxNumber + 1;
        if(newNumber <= 9){
            barCode = codeHead + "000" + newNumber;
        }else if(newNumber > 10 && newNumber <= 99){
            barCode = codeHead + "00" + newNumber;
        }else if(newNumber >= 100 && newNumber <= 999){
            barCode = codeHead + "0" + newNumber;
        }else{
            barCode = codeHead + newNumber;
        }
        return barCode;
    }


}
