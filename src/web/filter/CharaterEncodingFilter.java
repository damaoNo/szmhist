package web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "CharaterEncodingFilter",urlPatterns = "/*")
public class CharaterEncodingFilter implements Filter {
    @Override
    public void destroy() {
        System.out.println("销毁Filter");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //Filter有初始化参数，可以把字符集放在初始化参数中，FilterConfig接口中的方法
        req.setCharacterEncoding("utf8");
        resp.setCharacterEncoding("utf8");
        //过滤链
        chain.doFilter(req,resp);

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("初始化fileter-请求编码");
    }

}
