package org.example.filter;

import org.example.Util.JSONUtil;
import org.example.model.JSONResponse;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 使用过滤器对请求进行预处理
 * 配置用户统一会话管理的过滤器：匹配所有请求路径
 * 服务端资源：/login不用校验，其他都要校验，如果不通过，返回401，返回响应内容随便
 * 前端资源：/js/校验Session，不通过重定向到登录页面，
 * 其他如：/js/,/static/,/view/全部不校验
 */


@WebFilter("/*")
//因为Servlet是3.1.0，所以需要实现Filter接口
public class LoginFilter implements Filter {

    //初始化过滤器
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     *
     * 每次http请求匹配到过滤器路径时，会执行过滤器的doFilter
     * 如果往下执行，是调用filterChan.doFilter(request，response)
     * 否则自行处理
     */

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String servletPath = req.getServletPath();//获取当前请求的服务路径

        //不需要登录允许访问的先处理，就是把一些不需要登录的一些路径过滤掉，不做拦截处理
        if (servletPath.startsWith("/js/")||servletPath.startsWith("/static/")
                ||servletPath.startsWith("/view/")||servletPath.equals("/login")){
            filterChain.doFilter(request, response);
        }else {
            //获取Session对象
            HttpSession session = req.getSession(false);

            //验证用户是否登录，如果没有登录需要根据前端或后端做不同处理
            if (session == null||session.getAttribute("user")==null) {
                //前端重定向到登录页面
                if (servletPath.startsWith("/jsp/")){

                    resp.sendRedirect(basePath(req)+"/view/login.html");

                }else {

                    //后端返回401状态码
                    resp.setStatus(401);
                    resp.setCharacterEncoding("UTF-8");
                    resp.setContentType("application/json");
                    //返回统一的json数据格式
                    JSONResponse json = new JSONResponse();
                    
                    json.setCode("LOG000");
                    json.setMessage("没有登录,不允许访问");
                    PrintWriter pw = resp.getWriter();
                    pw.println(JSONUtil.serialize(json));
                    pw.flush();
                    pw.close();

                }
            }else {
                //敏感资源需要登录才能进行访问，但如果已经登录，允许继续执行
                filterChain.doFilter(request,response);

            }
        }
    }


    /**
     * 根据http请求动态获取访问主机访问路径(服务路径之前的)
     */

    public static String basePath(HttpServletRequest req){
        String schema = req.getScheme();//http
        String host = req.getServerName();//主机ip或者域名
        int port = req.getServerPort();//服务器端口号
        String contextPath = req.getContextPath();//应用上下文路径
        return schema+"://"+host+":"+port+contextPath;

    }

    @Override
    public void destroy() {

    }

}
