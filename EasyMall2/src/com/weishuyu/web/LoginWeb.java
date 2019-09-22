package com.weishuyu.web;

import com.weishuyu.domain.User;
import com.weishuyu.exception.MsgException;
import com.weishuyu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/LoginWeb")
public class LoginWeb extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");
       response.setContentType("text/html;charset=utf-8");
       //获取表单数据
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String remname=request.getParameter("remname");

        //采用cookie技术记住用户名
         if ("true".equals(remname)){
             Cookie cookie=new Cookie("remname", URLEncoder.encode(username,"utf-8"));
             cookie.setMaxAge(60*60*30*24);//30天保存时间在浏览器端
             cookie.setPath(request.getContextPath()+"/");//暂时不晓得啥子意思
             response.addCookie(cookie);
         }else {
             Cookie cookie=new Cookie("remname","");
             cookie.setMaxAge(0);
             cookie.setPath(request.getContextPath()+"/");
             response.addCookie(cookie);
         }
        //递交给service层
        UserService userService=new UserService();
        try {
            User user=userService.loginUser(username,password);
            //采用session技术，可以让user保留在浏览器端
            HttpSession session=request.getSession();
            session.setAttribute("user",user);
        }catch (MsgException e){
            request.setAttribute("msg",e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        response.sendRedirect("http://www.easymall2.com");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request,response);
    }
}
