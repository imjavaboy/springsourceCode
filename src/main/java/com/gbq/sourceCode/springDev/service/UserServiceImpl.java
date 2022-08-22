package com.gbq.sourceCode.springDev.service;


import com.gbq.sourceCode.springDev.dao.UserDaoServiceImpl;
import com.gbq.sourceCode.springDev.pojo.User;

import java.util.List;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/22
 * @Copyright 总有一天，会见到成功
 */

public class UserServiceImpl {
    private UserDaoServiceImpl userdao;

    public UserServiceImpl() {
    }
    public List<User> findUserList() {
        return userdao.findUserList();
    }

    public UserServiceImpl(UserDaoServiceImpl userdao) {
        this.userdao = userdao;
    }

    public void setUserdao(UserDaoServiceImpl userdao) {
        this.userdao = userdao;
    }
}
