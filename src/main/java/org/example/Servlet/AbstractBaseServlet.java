package org.example.Servlet;

import org.example.Util.JSONUtil;
import org.example.exception.AppException;
import org.example.model.JSONResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



//定义模板模式方法，让后面的类直接继承使用，根据需要扩展
public abstract class AbstractBaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doPost(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            //设置请求体的编码格式
            req.setCharacterEncoding("UTF-8");
            //设置响应体的编码
            resp.setCharacterEncoding("UTF-8");
            //设置响应体的数据类型
            resp.setContentType("application/json");

            //Session会话管理，除登录和注册接口，其他都需要登录后访问
            //获取服务路径
            req.getServletPath();


            JSONResponse json = new JSONResponse();

        try {
            Object data = process(req,resp);

            //子类的process执行完没有抛异常，表示业务执行成功
            json.setSuccess(true);
            json.setData(data);

        }catch (Exception e){
            //处理异常
            e.printStackTrace();
            String code = "UNKNOWN";
            String s = "未知错误";
            if (e instanceof AppException) {
                code =  ((AppException) e).getCode();//强转为自定义异常
                s=e.getMessage();
            }

            json.setCode(code);
            json.setMessage(s);

        }

        PrintWriter pw = resp.getWriter();
        pw.println(JSONUtil.serialize(json));//打印序列化的json
        pw.flush();
        pw.close();

        //TODO
    }


    protected abstract Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
