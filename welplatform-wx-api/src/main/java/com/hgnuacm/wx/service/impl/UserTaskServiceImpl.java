package com.hgnuacm.wx.service.impl;

import com.hgnuacm.wx.mapper.PointMapper;
import com.hgnuacm.wx.service.UserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTaskServiceImpl implements UserTaskService {
    @Autowired
    private PointMapper pointMapper;

}
