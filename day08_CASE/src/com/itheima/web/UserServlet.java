package com.itheima.web;

import com.itheima.bean.User;
import com.itheima.service.UserService;

import com.itheima.utils.Md5Util;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {

    //操作用户逻辑关系的类
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");
        if ("register".equals(method)) {
            register(req, resp);
        }else if("login".equals(method)){
            login(req,resp);
        }
    }

    /**
     * 用户登入
     * @param req
     * @param resp
     */
    private void login(HttpServletRequest req, HttpServletResponse resp) {

        try {
            String code = req.getParameter("code");
            HttpSession session = req.getSession();
            String authcode = (String) session.getAttribute("authcode");
            if(!authcode.equalsIgnoreCase(code)){
                req.setAttribute("error","验证码错误");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
                return;
            }

            String username = req.getParameter("username");
            String password = req.getParameter("password");
            //加密后的字符串
            String md5password = Md5Util.encodeByMd5(password);

            //查询是否存在该用户
            User result = userService.findUser(username,md5password);

            System.out.println(result);
            if(result!=null){//登入成功
                String bear = req.getParameter("bear");
                String show = req.getParameter("show");
                if("1".equals(bear)){//如果勾选记住用户名
                    Cookie cookie = new Cookie("username", username);
                    //存放5分钟
                    cookie.setMaxAge(60*5);
                    resp.addCookie(cookie);
                }else {
                    //否则 将之前的去掉
                    Cookie cookie = new Cookie("username","");
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
                session.setAttribute("user",result);
                if("1".equals(show)){
                    //标记后面操作都在在分页中进行
                    session.setAttribute("show","1");
                    resp.sendRedirect("linkManServlet?method=findAllPage");
                }else {
                    resp.sendRedirect("linkManServlet?method=findAll");
                }
            }else{
                req.setAttribute("password",password);
                req.setAttribute("code",code);
                req.setAttribute("error","用户名或者密码错误!");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册用户
     *
     * @param req
     * @param resp
     */
    public void register(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Map<String, String[]> map = req.getParameterMap();
            User user = new User();
            BeanUtils.populate(user, map);

            //把密码进行加密
            String passStr = req.getParameter("password");
            String password = Md5Util.encodeByMd5(passStr);
            user.setPassword(password);

            System.out.println(user);

            boolean result = userService.addUser(user);

            if(result){
                resp.sendRedirect("login.jsp");
            }else{
                req.setAttribute("error","用户名已存在,请重新输入!");
                req.setAttribute("user",user);
                user.setPassword(passStr);
                req.getRequestDispatcher("register.jsp").forward(req,resp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
