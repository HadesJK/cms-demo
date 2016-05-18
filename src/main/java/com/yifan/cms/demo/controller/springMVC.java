package com.yifan.cms.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jql on 2016/5/17.
 */
@Controller
public class springMVC {

    @RequestMapping("/springMVCVelocity")
    public String response() {
        return "spring/springMVC";
    }
}
