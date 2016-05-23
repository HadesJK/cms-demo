package com.yifan.cms.demo.service;

import com.yifan.cms.demo.entity.Student;
import com.yifan.cms.demo.mapper.StudentsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yifan
 * Description:
 * Author yifan(https://github.com/HadesJK)
 * Data 2016/5/23 14:16
 */
@Service
public class StudentsService {
    @Resource
    private StudentsMapper studentsMapper;

    public List<Student> getAllMembers() {
        return studentsMapper.selectAll();
    }

    public Student validateAccount(String name, String password) {
        if (name != null && name.trim().length() == 0) {
            name = "";
        }
        if (password != null && password.trim().length() == 0) {
            password = "";
        }
        return studentsMapper.selectByNameAndPassword(name, password);
    }

    public void addUser(String name, String password, String number, String clazz, Integer age, String sex, String phone) {
        Student stu = new Student(name, password, number, clazz, age, sex, phone);
        studentsMapper.insert(stu);
    }
}
