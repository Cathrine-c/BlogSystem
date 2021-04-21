package org.example.Servlet;

import org.example.model.User;
import org.example.Servlet.AbstractBaseServlet;
import org.example.dao.loginDAO;
import org.example.exception.AppException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends AbstractBaseServlet {

    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String username = req.getParameter("username");
        String password = req.getParameter("password");


        User user = loginDAO.query(username);

        if (user == null) {

            throw new AppException("LOGoo2","用户不存在");

        }

        if (!user.getPassword().equals(password)){

            throw new AppException("LOG003","用户密码或用户名错误");
        }


        //登录成功，页面跳转进去，这里用到jsp引用后端代码达到动态的html
        //登录成功，创建session
        HttpSession session = req.getSession();
        session.setAttribute("user",user);

        return null;
//        if ("abc".equals(username)){//模拟用户密码校验
//            return null;
//        }else {
//            throw new AppException("LOG001","用户名密码错误");
//        }

    }

}
