package com.itheima.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/*
 *  @创建时间:  2020/7/23 11:56
 *  @描述：    c3p0连接池的工具类
 *  @思路：
 *      1. 首先得先有连接池,连接池做几个？ 一个。  ComboPooledDataSource ds = new ComboPooledDataSource();
 *      2. 提供一个方法供别人获取连接
 *      3. 提供关闭释放的方法
 */
public class C3p0Util {

    static ThreadLocal<Connection> threadLocal= new ThreadLocal<>();
    static ComboPooledDataSource ds;
    static {
         ds = new ComboPooledDataSource();
    }

    public static DataSource getDataSource(){
        return ds;
    }

    //提供获取连接
    public static Connection getConn(){
        try {
            //每次获取前先查询当前线程是否有了连接, 存在就直接返回 否则 先在连接池中获取一个在存入当前线程中
            Connection connection = threadLocal.get();
            if(connection==null){
                  connection = ds.getConnection();
                 threadLocal.set(connection);
                 return connection;
             }
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

    }

}
