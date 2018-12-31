package com.fruitsalesplatform.controller;

import com.fruitsalesplatform.entity.Retailer;
import com.fruitsalesplatform.service.RetailerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        model.addAttribute("list", retailerList);
        return "/retailer/retailerHome.jsp";
    }

    private Map<String, Object> retailerToMap(Retailer retailer) {
        Map<String, Object> map = new HashMap<>();
        map.put("name",checkStringIsEmpty(retailer.getName()));
        map.put("telphone", checkStringIsEmpty(retailer.getTelphone()));
        map.put("address", checkStringIsEmpty(retailer.getAddress()));
        map.put("status", retailer.getStatus() == -1 ? null:retailer.getStatus());
        map.put("createTime", checkStringIsEmpty(retailer.getCreateTime()));
        return map;
    }
    private String checkStringIsEmpty(String param) {
        return param == null ? null : (param.equals("") ? null: "%" + param + "%");
    }

}
