package com.fruitsalesplatform.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dpcraft
 * @date 2018-12-30
 * @time 13:48
 */
public class BaseController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    protected String checkStringIsEmpty(String param) {
        return param == null ? null : (param.equals("") ? null: "%" + param + "%");
    }
    protected String trim(String str) {
        if(str == null || str == ""){
            return null;
        }
        return  str.trim();
    }
}
