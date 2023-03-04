package com.softeem.servlet;

import com.softeem.bean.User;
import com.softeem.service.UserService;
import com.softeem.service.impl.UserServiceImpl;
import com.softeem.utils.BaseServlet;
import com.softeem.utils.CookieUtils;
import com.softeem.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends BaseServlet {

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        WebUtils.copyParamToBean(parameterMap,user);

        UserService userService = new UserServiceImpl();
        try {
            User myuser = userService.login(user);
            if(myuser!=null){

                Cookie nameCookie = new Cookie("username",myuser.getUsername());
                Cookie passCookie = new Cookie("password",myuser.getPassword());
                nameCookie.setMaxAge(60*60);
                passCookie.setMaxAge(60*60);
                response.addCookie(nameCookie);
                response.addCookie(passCookie);

                HttpSession session = request.getSession();//会话作用域
                session.setAttribute("user",userService.login(user));//把用户的个人信息保存到session会话作业域
                request.setAttribute("msg","登录成功！");
                if(request.getParameter("qiurl") != null && request.getParameter("qiurl") != "" ){
                    request.getRequestDispatcher(request.getParameter("qiurl")).forward(request,response);
                }else {
                    request.getRequestDispatcher("/pages/user/success.jsp").forward(request,response);
                }
            }else {
                request.setAttribute("msg","账号名或密码不正确");
                request.getRequestDispatcher("pages/user/login.jsp").forward(request,response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 获取Session 中的验证码
        String token = (String) session.getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session 中的验证码
        session.removeAttribute(KAPTCHA_SESSION_KEY);

        String code = request.getParameter("code"); //验证码
        System.out.println("用户提交的验证码 = " + code);
        System.out.println("session中的验证码 =" + token);

        User user = new User();
        Map<String, String[]> parameterMap = request.getParameterMap();
        WebUtils.copyParamToBean(parameterMap,user);

        request.setAttribute("user",user);
        UserService userService = new UserServiceImpl();
        if(code.equals(token)){  //忽略大小写验证 验证码是否正确
            try {
                if(!userService.existsUsername(user.getUsername())){  //用户不存在，可以注册
                    userService.registerUser(user);      //业务层的注册方法，可以将数据存储到数据库
                    HttpSession session2 = request.getSession();
                    session2.setAttribute("user",user);
                    request.setAttribute("msg","注册成功！");
                    request.getRequestDispatcher("/pages/user/success.jsp").forward(request, response);
                }else{
                    request.setAttribute("msg","用户名已存在");
                    request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            request.setAttribute("msg","验证码不正确");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        session.invalidate();
        Cookie username = CookieUtils.findCookie("username", request.getCookies());
        Cookie password = CookieUtils.findCookie("password", request.getCookies());
        if(username!=null){
            username.setMaxAge(0);
            response.addCookie(username);
        }
        if(password!=null){
            password.setMaxAge(0);
            response.addCookie(password);
        }
        response.sendRedirect("index.jsp");
    }

}
