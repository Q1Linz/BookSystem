package com.softeem.test;

import com.softeem.utils.BaseServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CookieServlet", value = "/CookieServlet")
public class CookieServlet extends BaseServlet {
    protected void createCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 创建Cookie 对象
        Cookie cookie = new Cookie("username", "1234");
        //2 通知客户端保存Cookie
        resp.addCookie(cookie);
        //1 创建Cookie 对象
        Cookie cookie1 = new Cookie("password", "value5");
        //2 通知客户端保存Cookie
        resp.addCookie(cookie1);
        resp.getWriter().write("Cookie 创建成功");
    }

    protected void test(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if ("test".equals(username) && "123".equals(password)) {
//登录成功
            Cookie cookie = new Cookie("username", username);
            Cookie cookie2 = new Cookie("password", password);
            cookie.setMaxAge(60 * 60 * 24 * 7);//当前Cookie 一周内有效resp.addCookie(cookie);
            resp.addCookie(cookie);
            resp.addCookie(cookie2);
            resp.getWriter().write("登录 成功");
        } else {
            //登录失败
            resp.getWriter().write("登录 失败");
        }
    }
}
