package com.fruitsalesplatform.controller;

import com.fruitsalesplatform.entity.User;
import com.fruitsalesplatform.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author dpcraft
 * @date 2018-12-30
 * @time 15:30
 */
@Controller
public class UserController extends BaseController {
    @Resource
    UserService userService;
    @RequestMapping("/user/toLogin.action")
    public String toLogin() {
        return "/login.jsp";
    }

    @RequestMapping("/user/login.action")
    public String login(User user, Model model, HttpServletRequest request) {
        Map<String,String> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("password", user.getPassword());
        List<User> userList = userService.find(map);
        if(userList != null && userList.size() > 0) {
            request.getSession().setAttribute("user",userList.get(0));
            model.addAttribute("user",userList.get(0));
            return "/home.jsp";
        }
        model.addAttribute("errorMsg", "登录失败！账号或密码错误");
        return "/login.jsp";
    }

    @RequestMapping("/user/registerPage.action")
    public String toRegister() {
        return "/register.jsp";
    }

    @RequestMapping("/user/register.action")
    public String register(User user, Model model) {
        Map<String, String> map = new HashMap<>();
        map.put("username", user.getUsername());
        List<User> userList = userService.find(map);

        if(userList != null && userList.size() > 0) {
            model.addAttribute("errorMsg", "注册失败，用户名已被占用");
            return "/register.jsp";
        }
        user.setUserId(UUID.randomUUID().toString());
        userService.insert(user);
        model.addAttribute("noticeMsg", "注册成功！请登录");
        return "/login.jsp";
    }

}
