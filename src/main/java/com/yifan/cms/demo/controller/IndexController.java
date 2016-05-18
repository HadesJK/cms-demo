package com.yifan.cms.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yifan
 * Description:
 * Author yifan(https://github.com/HadesJK)
 * Data 2016/5/18 15:23
 */
@Controller
public class IndexController {
    @RequestMapping("/*")
    public String root() {
        return "index/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index/index";
    }
}
