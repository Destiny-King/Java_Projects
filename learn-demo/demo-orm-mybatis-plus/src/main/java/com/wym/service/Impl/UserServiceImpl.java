package com.wym.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wym.entity.User;
import com.wym.mapper.UserMapper;
import com.wym.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
