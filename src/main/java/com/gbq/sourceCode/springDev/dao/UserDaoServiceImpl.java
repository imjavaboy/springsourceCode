package com.gbq.sourceCode.springDev.dao;


import com.gbq.sourceCode.springDev.pojo.User;

import java.util.Collections;
import java.util.List;

/**
 * @author 郭本琪
 * @description
 * @date 2022/8/22
 * @Copyright 总有一天，会见到成功
 */

public class UserDaoServiceImpl {
    public UserDaoServiceImpl() {
    }

    public List<User> findUserList(){
        return Collections.singletonList(new User("gbq",18));
    }
}
