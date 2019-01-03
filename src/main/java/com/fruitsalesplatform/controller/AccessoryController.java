package com.fruitsalesplatform.controller;

import com.fruitsalesplatform.entity.Accessory;
import com.fruitsalesplatform.service.AccessoryService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author dpcraft
 * @date 2019/1/3
 * @time 20:22
 */
@Controller
@RequestMapping("/accessory")
public class AccessoryController extends BaseController {
    @Autowired
    AccessoryService accessoryService;
    @RequestMapping("/list.action")
    public String list(Model model, Accessory accessory){
        Map<String,Object> map = new HashMap<>();
        map.put("fruitId", accessory.getFruitId());
        List<Accessory> accessoryList = accessoryService.find(map);
        model.addAttribute("fruitId", accessory.getFruitId());
        model.addAttribute("list",accessoryList.size()< 1 ?  null: accessoryList);
        model.addAttribute("sumPrice", sumPrice(accessoryList));
        return "/accessory/accessoryHome.jsp";

    }

    @RequestMapping("/add.action")
    public String add(Model model, Accessory accessory) {
        accessory.setAccessoryId(UUID.randomUUID().toString());
        accessory.setFruitId(accessory.getFruitId());
        accessory.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        accessoryService.insert(accessory);
        return list(model, accessory);
    }

    @RequestMapping("/delete.action")
    public String delete(Model model, Accessory accessory) {
        LoggerFactory.getLogger("").debug("-------------------------" + accessory.getAccessoryId());
        accessoryService.deleteById(accessory.getAccessoryId());
        return list(model,accessory);
    }
    @RequestMapping("/deleteList.action")
    public String deleteList(Model model, String[] arrays, Accessory accessory) {
        accessoryService.delete(arrays);
        return list(model,accessory);
    }

    private double sumPrice(List<Accessory> accessoryList) {
        double sum = 0.0;
        for(Accessory accessory : accessoryList) {
            sum += accessory.getPrice();
        }
        return sum;
    }

}
