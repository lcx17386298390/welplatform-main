package com.hgnuacm.wx.service.impl;

import com.hgnuacm.wx.domain.Student;
import com.hgnuacm.wx.domain.User;
import com.hgnuacm.wx.mapper.StudentMapper;
import com.hgnuacm.wx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> queryByIdCard(String username) {
        return studentMapper.queryByIdCard(username);
    }
}
