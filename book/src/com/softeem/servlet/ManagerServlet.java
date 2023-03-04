package com.softeem.servlet;

import com.softeem.bean.Manager;
import com.softeem.bean.User;
import com.softeem.service.ManagerService;
import com.softeem.service.UserService;
import com.softeem.service.impl.ManagerServiceImpl;
import com.softeem.service.impl.UserServiceImpl;
import com.softeem.utils.BaseServlet;
import com.softeem.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "ManagerServlet", value = "/ManagerServlet")
public class ManagerServlet extends BaseServlet {
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Manager manager = new Manager();
        WebUtils.copyParamToBean(parameterMap,manager);

        ManagerService managerService = new ManagerServiceImpl();
        try {
            Manager mymanager = managerService.login(manager);
            if(mymanager!=null){

                Cookie nameCookie = new Cookie("mname",mymanager.getMname());
                Cookie passCookie = new Cookie("password",mymanager.getPassword());
                nameCookie.setMaxAge(60*60);
                passCookie.setMaxAge(60*60);
                response.addCookie(nameCookie);
                response.addCookie(passCookie);

                HttpSession session = request.getSession();//会话作用域
                session.setAttribute("manager",managerService.login(manager));//把用户的个人信息保存到session会话作业域
                request.setAttribute("msg","登录成功！");
                request.getRequestDispatcher("pages/manager/manager.jsp").forward(request,response);
            }else {
                request.setAttribute("msg","账号名或密码不正确");
                request.getRequestDispatcher("pages/manager/login.jsp").forward(request,response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
