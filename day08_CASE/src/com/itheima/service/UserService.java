package com.itheima.service;

import com.itheima.bean.User;
import com.itheima.dao.UserDao;

import java.sql.SQLException;

/**
 * 用于操作用户逻辑的类
 */
public class UserService {

    private UserDao userdao = new UserDao();

    /**
     * 添加用户
     * @param user
     * @return
     */
    public boolean addUser(User user) throws SQLException {

        User finduser = userdao.findUser(user.getUsername(),null);

        if(finduser!=null){
            return false;
        }

        boolean result = userdao.addUser(user);

        return result;
    }

    /**
     * 查找用户
     * @param username
     * @param password
     * @return
     */
    public User findUser(String username, String password) throws SQLException {

        User user = userdao.findUser(username, password);

        return user;
    }
}
