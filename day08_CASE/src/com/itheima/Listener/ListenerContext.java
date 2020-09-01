package com.itheima.Listener;

import com.itheima.utils.C3p0Util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ListenerContext implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //调用下C3p0Util的方法为了让连接池能在服务器启动时可以初始化好
        C3p0Util.getConn();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
