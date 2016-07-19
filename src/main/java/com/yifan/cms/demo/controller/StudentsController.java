package com.yifan.cms.demo.controller;

import com.yifan.cms.demo.entity.Student;
import com.yifan.cms.demo.service.JsonService;
import com.yifan.cms.demo.service.StudentsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by yifan
 * Description:
 * Author yifan(https://github.com/HadesJK)
 * Data 2016/5/18 21:59
 */
@Controller
@RequestMapping("/students")
public class StudentsController {
    @Resource
    private StudentsService studentsService;

    @Resource
    private JsonService jsonService;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("students", studentsService.getAllMembers());
        return "students/list";
    }

    @RequestMapping("/userAdd")
    public String userAddIndex() {
        return "students/userAdd";
    }

    @RequestMapping("/addUser")
    public ModelAndView addUser(String name, String password, String number, String clazz, Integer age, String sex, String phone) {
        studentsService.addUser(name, password, number, clazz, age, sex, phone);
        return new ModelAndView("redirect:/students/list");
    }

    //返回json
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @ResponseBody
    public Student showUser(@PathVariable String username) {
        System.out.println("query username is " + username);
        return jsonService.queryStu();
    }

}
