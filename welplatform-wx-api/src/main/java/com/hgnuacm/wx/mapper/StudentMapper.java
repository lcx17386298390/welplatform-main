package com.hgnuacm.wx.mapper;

import com.hgnuacm.wx.domain.Student;
import com.hgnuacm.wx.domain.User;

import java.util.List;

public interface StudentMapper {
    public List<Student> queryByIdCard(String idCard);
}
