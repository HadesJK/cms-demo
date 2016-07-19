package com.yifan.cms.demo.service;

import com.yifan.cms.demo.entity.Student;
import org.springframework.stereotype.Service;

/**
 * Created by yifan on 16/7/19.
 */
@Service
public class JsonService {
    public Student queryStu() {
        Student stu = new Student("Hades", "123456", "07110113110", "IEEE", 18, "male", "1595800");
        return  stu;
    }
}
