package com.itheima.web;

import com.itheima.bean.LinkMan;
import com.itheima.bean.Page;
import com.itheima.bean.User;
import com.itheima.service.LinkManService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 联系人管理
 */
@WebServlet("/linkManServlet")
public class LinkManServlet extends HttpServlet {

    private LinkManService linkManService = new LinkManService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String methodstr = req.getParameter("method");
/*
        if ("findAll".equals(methodstr)) {
            findAll(req, resp);
        } else if ("add".equals(methodstr)) {
            add(req, resp);
        } else if ("delete".equals(methodstr)) {
            delete(req, resp);
        } else if ("update".equals(methodstr)) {
            update(req, resp);
        } else if ("findById".equals(methodstr)) {
            findById(req, resp);
        } else if ("findAllPage".equals(methodstr)) {
            findAllPage(req, resp);
        }
*/

        //反射解决多次判断寻找方法的问题
        try {
            Method method = LinkManServlet.class.getDeclaredMethod(methodstr, HttpServletRequest.class, HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 分页查询出联系人信息
     *
     * @param req
     * @param resp
     */
    private void findAllPage(HttpServletRequest req, HttpServletResponse resp) {

        try {
            //默认初始 第一页  每页3条
            int currentpage = 1;
            int pagesize = 3;

            String reqcurrentpage = req.getParameter("currentpage");
            if(reqcurrentpage!=null){
                currentpage = Integer.parseInt(reqcurrentpage);
            }

            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");

            Page page = linkManService.findAllPage(user.getUserid(),currentpage,pagesize);

            req.setAttribute("page",page);

            req.getRequestDispatcher("list_page.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据Id查询联系人
     *
     * @param req
     * @param resp
     */
    private void findById(HttpServletRequest req, HttpServletResponse resp) {

        try {
            String id = req.getParameter("id");
            LinkMan linkMan = linkManService.findById(id);

            req.setAttribute("linkMan", linkMan);
            req.getRequestDispatcher("update.jsp").forward(req, resp);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改当前联系人
     *
     * @param req
     * @param resp
     */
    private void update(HttpServletRequest req, HttpServletResponse resp) {

        try {
            //表单数据进行封装
            LinkMan linkMan = new LinkMan();
            Map<String, String[]> map = req.getParameterMap();
            BeanUtils.populate(linkMan, map);
            System.out.println(linkMan);

            System.out.println("id:" + req.getParameter("id"));
            //修改联系人
            boolean result = linkManService.update(linkMan);
            System.out.println("修改结果:" + result);

            HttpSession session = req.getSession();
            Object show = session.getAttribute("show");
            if("1".equals(show)){
                resp.sendRedirect("linkManServlet?method=findAllPage");
            }else{
                resp.sendRedirect("linkManServlet?method=findAll");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除指定联系人
     *
     * @param req
     * @param resp
     */
    private void delete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String id = req.getParameter("id");

            //添加联系人
            boolean result = linkManService.deleteById(id);
            System.out.println("删除结果:" + result);

            HttpSession session = req.getSession();
            Object show = session.getAttribute("show");
            if("1".equals(show)){
                resp.sendRedirect("linkManServlet?method=findAllPage");
            }else{
                resp.sendRedirect("linkManServlet?method=findAll");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加联系人
     *
     * @param req
     * @param resp
     */
    private void add(HttpServletRequest req, HttpServletResponse resp) {
        try {
            //表单数据进行封装
            LinkMan linkMan = new LinkMan();
            Map<String, String[]> map = req.getParameterMap();
            BeanUtils.populate(linkMan, map);

            //获取当前登入用户的id
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            linkMan.setUserid(user.getUserid());

            //添加联系人
            boolean result = linkManService.add(linkMan);
            System.out.println("添加结果:" + result);


            Object show = session.getAttribute("show");
            if("1".equals(show)){
                resp.sendRedirect("linkManServlet?method=findAllPage");
            }else{
                resp.sendRedirect("linkManServlet?method=findAll");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询当前用户下的所有联系人
     *
     * @param req
     * @param resp
     */
    private void findAll(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");

            List<LinkMan> list = linkManService.findAllId(user.getUserid());

            req.setAttribute("list", list);
            req.getRequestDispatcher("list.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
