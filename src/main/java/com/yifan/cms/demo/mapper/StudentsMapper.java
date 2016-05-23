package com.yifan.cms.demo.mapper;

import com.yifan.cms.demo.entity.Student;

import java.util.List;

/**
 * Created by yifan
 * Description:
 * Author yifan(https://github.com/HadesJK)
 * Data 2016/5/18 20:58
 */
public interface StudentsMapper {
    void insert(Student student);
    void updateById(Student student);
    Student selectById(int id);
    Student selectByNameAndPassword(String name, String password);
    List<Student> selectAll();
}
