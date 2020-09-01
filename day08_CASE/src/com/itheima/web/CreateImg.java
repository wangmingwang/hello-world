package com.itheima.web;

import cn.dsna.util.images.ValidateCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/createImg")
public class CreateImg extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ValidateCode codeNode = new ValidateCode(150 , 40 , 4  , 20);
        String authcode = codeNode.getCode();
        HttpSession session = req.getSession();
        session.setAttribute("authcode",authcode);
        System.out.println("验证码:"+authcode);
        codeNode.write(resp.getOutputStream());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doPost(req, resp);
    }
}
