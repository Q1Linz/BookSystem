package com.softeem.servlet;

import com.softeem.bean.Book;
import com.softeem.bean.CartItem;
import com.softeem.service.BookService;
import com.softeem.service.Cart;
import com.softeem.service.impl.BookServiceImpl;
import com.softeem.utils.BaseServlet;
import com.softeem.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends BaseServlet {

    /**
     *加入购物车
     *@param request
     *@param response
     *@throws ServletException
     *@throws IOException
     */
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        BookService bookService = new BookServiceImpl();
        //获取请求的id
        int id = WebUtils.parseInt(request.getParameter("id"),0);
        try {
            //通过主键id获得图书对象
            Book book = bookService.queryBookById(id);
            CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
            //从session会话作用域中取出cart，如果为null则表示购物车无商品信息，否则有。
            Cart cart = (Cart)session.getAttribute("cart");
            if(cart == null){
                cart = new Cart();
                session.setAttribute("cart",cart);
            }
            cart.addItem(cartItem);
            session.setAttribute("cartName",book.getName());
            session.setAttribute("totalCount",cart.getTotalCount());
            //重定向回商品原来的地址
            response.sendRedirect(request.getHeader("Referer"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart!=null){
            cart.deleteItem(id);
            response.sendRedirect(request.getHeader("Referer"));
        }

    }

    protected void clearItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        if(cart!=null){
            cart.clear();
        }
        response.sendRedirect(request.getHeader("Referer"));
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        Cart cart=(Cart) session.getAttribute("cart");
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        int count=WebUtils.parseInt(req.getParameter("count"),1);
        if (cart!=null){
            cart.updateCount(id,count);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

}