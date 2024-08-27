package com.hgnuacm.wx.service;

import com.hgnuacm.wx.domain.Student;
import com.hgnuacm.wx.domain.User;

import java.util.List;

public interface StudentService {

    public List<Student> queryByIdCard(String idCard);

}
