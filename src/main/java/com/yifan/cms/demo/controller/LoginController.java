package com.yifan.cms.demo.controller;

import com.yifan.cms.demo.entity.Student;
import com.yifan.cms.demo.service.StudentsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by yifan
 * Description:
 * Author yifan(https://github.com/HadesJK)
 * Data 2016/5/23 13:48
 */
@Controller
public class LoginController {
    @Resource
    private StudentsService studentsService;

    @RequestMapping("/login")
    public String loginIndex() {
        return "login/loginIndex";
    }

    @RequestMapping("/loginProcess")
    public ModelAndView loginProcess(String username, String password) {
        Student stu = studentsService.validateAccount(username, password);
        if (stu != null) {
            //TODO: 加入到session中
            return new ModelAndView("redirect:/index");
        }
        return new ModelAndView("redirect:/login");
    }
}
