package com.fruitsalesplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.fruitsalesplatform.entity.Retailer;
import com.fruitsalesplatform.service.RetailerService;
import org.slf4j.LoggerFactory;
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
 * @date 2018-12-30
 * @time 22:02
 */
@Controller
@RequestMapping("/retailer")
public class RetailerController extends BaseController {
    @Resource
    RetailerService retailerService;
    @RequestMapping("/list.action")
    public String list(Model model, Retailer retailer, String startTime, String endTime) {
        Map<String, Object> map = this.retailerToMap(retailer);
        if(startTime!=null && !startTime.equals("")){
            map.put("startTime", startTime);
        }
        if(endTime != null && !endTime.equals("")){
            map.put("endTime",endTime);
        }
        List<Retailer> retailerList = retailerService.find(map);
        model.addAttribute("currentPage", retailer.getCurrentPage());
        model.addAttribute("startPage", retailer.getStartPage());
        int countNumber = retailerService.count(map);
        model.addAttribute("countNumber",countNumber);
        int pageSize = retailer.getPageSize();
        model.addAttribute("pageSize",pageSize);
        int sumPageNumber = countNumber % pageSize == 0 ? (countNumber/pageSize) : ((countNumber/pageSize) + 1);
        model.addAttribute("sumPageNumber", sumPageNumber);
        model.addAttribute("list", retailerList);
        return "/retailer/retailerHome.jsp";
    }

    @RequestMapping("/editRetailer.action")
    public @ResponseBody Retailer editRetailer(@RequestBody String json) {
        System.out.println(json);
        String id = JSONObject.parseObject(json).getString("id");
        return retailerService.get(id);
    }
    @RequestMapping("/edit.action")
    public String edit(Model model, Retailer retailer) {
        retailerService.update(retailer);
        Retailer queryRetailer = new Retailer();
        queryRetailer.setStartPage(retailer.getStartPage());
        queryRetailer.setCurrentPage(retailer.getCurrentPage());
        queryRetailer.setPageSize(retailer.getPageSize());
        queryRetailer.setStatus(-1);
        return list(model, queryRetailer, null, null);
    }

    @RequestMapping("delete.action")
    public String delete(Model model, Retailer retailer) {
        retailerService.deleteById(retailer.getRetailerId());
        Retailer queryRetailer = new Retailer();
        queryRetailer.setStartPage(retailer.getStartPage());
        queryRetailer.setCurrentPage(retailer.getCurrentPage());
        queryRetailer.setPageSize(retailer.getPageSize());
        queryRetailer.setStatus(-1);
        return list(model,queryRetailer,null, null);
    }


    @RequestMapping("/add.action")
    public String add(Model model, Retailer retailer) {
        retailer.setRetailerId(UUID.randomUUID().toString());
        retailer.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        retailerService.insert(retailer);
        Retailer queryRetailer = new Retailer();
        queryRetailer.setStatus(-1);
        return list(model, queryRetailer, null, null);
    }
    private Map<String, Object> retailerToMap(Retailer retailer) {
        Map<String, Object> map = new HashMap<>();
        map.put("name",checkStringIsEmpty(retailer.getName()));
        map.put("telphone", trim(retailer.getTelphone()));
        map.put("address", checkStringIsEmpty(retailer.getAddress()));
        map.put("status", retailer.getStatus() == -1 ? null:retailer.getStatus());
        map.put("createTime", checkStringIsEmpty(retailer.getCreateTime()));
        map.put("startPage", retailer.getStartPage());
        map.put("pageSize", retailer.getPageSize());
        return map;
    }
    private String checkStringIsEmpty(String param) {
        return param == null ? null : (param.equals("") ? null: "%" + param + "%");
    }
    private String trim(String str) {
        if(str == null || str == ""){
            return null;
        }
        return  str.trim();
    }

}
