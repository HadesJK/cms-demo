package com.yifan.cms.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yifan
 * Description:
 * Author yifan(https://github.com/HadesJK)
 * Data 2016/5/18 21:59
 */
@Controller
public class StudentsController {
    @RequestMapping("list")
    public String list() {
        return "students/list";
    }
}
