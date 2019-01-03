package com.fruitsalesplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.fruitsalesplatform.entity.Commodities;
import com.fruitsalesplatform.service.AccessoryService;
import com.fruitsalesplatform.service.CommoditiesService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author dpcraft
 * @date 2019/1/3
 * @time 14:20
 */
@Controller
@RequestMapping("/commodities")
public class CommoditiesController extends BaseController {
    @Resource
    CommoditiesService commoditiesService;
    @Resource
    AccessoryService accessoryService;
    @RequestMapping("/list.action")
    public String list(Model model, Commodities commodities, @RequestParam(defaultValue = "0.0") double startPrice,
                       @RequestParam(defaultValue = "0.0") double endPrice, String startTime, String endTime) {
        Map<String, Object> map = this.commoditiesToMap(commodities);
        if(startTime != null && !startTime.equals("")) {
            map.put("startTime",startTime);
        }
        if(endTime != null && !endTime.equals("")) {
            map.put("endTime", endTime);
        }
        if(startPrice > 0.0){
            map.put("startPrice", startPrice);
        }
        if(endPrice > 0.0) {
            map.put("endPrice", endPrice);
        }
        List<Commodities> commoditiesList = commoditiesService.find(map);
        model.addAttribute("commodities", commodities);
        model.addAttribute("startPrice",startPrice);
        model.addAttribute("endPrice", endPrice);
        model.addAttribute("startTime", startTime);
        model.addAttribute("endTime", endTime);
        model.addAttribute("list", commoditiesList.size() < 1 ? null : commoditiesList);
        model.addAttribute("currentPage", commodities.getCurrentPage());
        model.addAttribute("startPage", commodities.getStartPage());
        int countNumber = commoditiesService.count(map);
        model.addAttribute("countNumber", countNumber);
        int pageSize = commodities.getPageSize();
        model.addAttribute("pageSize", pageSize);
        int sumPageNumber = countNumber % pageSize == 0 ? (countNumber/pageSize) : ((countNumber/pageSize) + 1);
        model.addAttribute("sumPageNumber", sumPageNumber);
        return "/commodities/commoditiesHome.jsp";
    }

    @RequestMapping("/editCommodities.action")
    public @ResponseBody Commodities editCommodities(@RequestBody String json) {
        String id =JSONObject.parseObject(json).getString("id");
        return commoditiesService.get(id);
    }

    @RequestMapping("/edit.action")
    public String edit(Model model, Commodities commodities){
        commoditiesService.update(commodities);
        Commodities queryCommodities = new Commodities();
        queryCommodities.setStartPage(commodities.getStartPage());
        queryCommodities.setCurrentPage(commodities.getCurrentPage());
        queryCommodities.setPageSize(commodities.getPageSize());
        return list(model,queryCommodities,0.0,0.0,null,null);
    }

    @RequestMapping("/add.action")
    public String add(Model model, Commodities commodities) {
        commodities.setFruitId(UUID.randomUUID().toString());
        commodities.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        commoditiesService.insert(commodities);
        return list(model, new Commodities(), 0.0,0.0,null,null);

    }

    @RequestMapping("/delete.action")
    public String delete(Model model, Commodities commodities) {
        commoditiesService.deleteById(commodities.getFruitId());
        int result = accessoryService.deleteByFruitId(commodities.getFruitId());
        LoggerFactory.getLogger("").debug("delete fruitId= " + commodities.getFruitId() + "'s accessories number: " + result);
        Commodities queryCommodities = new Commodities();
        queryCommodities.setStartPage(commodities.getStartPage());
        queryCommodities.setCurrentPage(commodities.getCurrentPage());
        queryCommodities.setPageSize(commodities.getPageSize());
        return list(model,queryCommodities,0.0,0.0,null,null);
    }

    private Map<String, Object> commoditiesToMap(Commodities commodities) {
        Map<String, Object> map = new HashMap<>();
        map.put("name",checkStringIsEmpty(commodities.getName()));
        map.put("locality", checkStringIsEmpty(commodities.getLocality()));
        map.put("createTime", trim(commodities.getCreateTime()));
        map.put("startPage",commodities.getStartPage());
        map.put("pageSize",commodities.getPageSize());
        return map;

    }


}
