package com.fruitsalesplatform.controller;

import com.fruitsalesplatform.entity.User;
import com.fruitsalesplatform.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author dpcraft
 * @date 2018-12-30
 * @time 10:13
 */
@Controller
public class TestController {
    @Autowired
    private TestService testService;
    Logger logger = LoggerFactory.getLogger("TestController");

    @RequestMapping("/user/findUser.action")
    public String findUser(User user, Model model) {
        logger.debug("进入findUser");
        List<User> userList = testService.findUserByName(user);
        model.addAttribute("userList", userList);
        return "/test/test.jsp";
    }

    @RequestMapping("/test")
    public void test(){
        logger.debug("------------test-------------");
    }
}
