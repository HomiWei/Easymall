package com.weishuyu.web;

import com.weishuyu.domain.User;
import com.weishuyu.exception.MsgException;
import com.weishuyu.service.UserService;
import com.weishuyu.utils.isNull;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          //乱码处理
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取表单数据
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String password2=request.getParameter("password2");
        String nickname=request.getParameter("nickname");
        String email=request.getParameter("email");
        String valistr=request.getParameter("valistr");
        //再次校验表单数据的正确性
        if (isNull.Null(username)){
            request.setAttribute("msg","用户名不能为空");
            request.getRequestDispatcher("/regis.jsp").forward(request,response);
            return;
        };
        if (isNull.Null(password)){
            request.setAttribute("msg","密码不能为空");
            request.getRequestDispatcher("/regis.jsp").forward(request,response);
            return;
        }
        if (isNull.Null(password2)){
            request.setAttribute("msg","确认密码不能为空");
            request.getRequestDispatcher("/regis.jsp").forward(request,response);
            return;
        }
        if (isNull.Null(nickname)){
            request.setAttribute("msg","昵称不能为空");
            request.getRequestDispatcher("/regis.jsp").forward(request,response);
            return;
        }
        if (isNull.Null(email)){
            request.setAttribute("msg","邮箱不能为空");
            request.getRequestDispatcher("/regis.jsp").forward(request,response);
            return;
        }
        if (isNull.Null(valistr)){
            request.setAttribute("msg","验证码不能为空");
            request.getRequestDispatcher("/regis.jsp").forward(request,response);
            return;
        }
        //把数据递交给server层
           //封装user数据
        User user=new User(0,username,password,nickname,email);
           //递交server层处理
        UserService userService=new UserService();
        try {
            userService.registUser(user);
        }catch (MsgException e){
            request.setAttribute("msg",e.getMessage());
            request.getRequestDispatcher("/regis.jsp").forward(request,response);
            return;//为什么要returen
        }
        //跳转到主界面
        response.getWriter().write("<h1 align='center'><font color='red'>恭喜注册成功，4秒后跳转<h1>");
        response.setHeader("refresh","3;http://www.easymall2.com");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
