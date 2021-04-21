package org.example.Servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//用注解的方法把Servlet配置到web应用中
@WebServlet("/articleLove")

public class AritcleLoveServlet extends AbstractBaseServlet{


    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return null;
    }

}
