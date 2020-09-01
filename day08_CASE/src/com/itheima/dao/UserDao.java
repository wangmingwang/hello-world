package com.itheima.dao;

import com.itheima.bean.User;
import com.itheima.utils.C3p0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {


    QueryRunner queryRunner = new QueryRunner();

    /**
     * 添加一个用户
     * @param user
     * @return
     */
    public boolean addUser(User user) throws SQLException {
        String sql = "insert into user values(null,?,?,?,?,?,?)";
        int update = queryRunner.update(C3p0Util.getConn(),sql, user.getUsername(),
                user.getPassword(),
                user.getNickname(),
                user.getAddress(),
                user.getEmail(),
                user.getSex());
        return update!=0;
    }

    /**
     * 根据用户名和密码查找用户
     * @param username
     * @return
     */
    public User findUser(String username,String password) throws SQLException {
        String sql = "select * from user where username=? and password = ?";
        if(password==null){
             sql = "select * from user where username=?";
            return queryRunner.query(C3p0Util.getConn(),sql, new BeanHandler<>(User.class), username);
        }
        return queryRunner.query(C3p0Util.getConn(),sql, new BeanHandler<>(User.class), username,password);
    }


}
