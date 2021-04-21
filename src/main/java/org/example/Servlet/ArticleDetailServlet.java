package org.example.Servlet;

import org.example.dao.ArticleDAO;
import org.example.model.Article;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/articleDetail")
public class ArticleDetailServlet extends AbstractBaseServlet{

    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //getParameter是通过请求获取，不用像getAttribute那样要通过setAttribute来获取，它获取的类型是String
        String id = req.getParameter("id");
       // Article a = ArticleDAO.query(Integer.parseInt(id))
        Article a = ArticleDAO.query(Integer.parseInt(id));

        return a;

    }
}
