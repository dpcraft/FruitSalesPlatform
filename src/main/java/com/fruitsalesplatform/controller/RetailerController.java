package com.fruitsalesplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dpcraft
 * @date 2018-12-30
 * @time 22:02
 */
@Controller
@RequestMapping("/retailer")
public class RetailerController extends BaseController {
    @RequestMapping("/list.action")
    public String list() {
        return "/home.jsp";
    }
}
