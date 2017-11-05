package com.artisan.common.filter;

import com.github.pagehelper.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理跨域的过滤器
 * @author Leeyom Wang
 * @date 2017年10月19日 14:47
 */
@Component
public class CorsFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(CorsFilter.class);

    private String allowOrigin;
    private String allowMethods;
    private String allowCredentials;
    private String allowHeaders;
    private String exposeHeaders;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowOrigin = filterConfig.getInitParameter("allowOrigin");
        allowMethods = filterConfig.getInitParameter("allowMethods");
        allowCredentials = filterConfig.getInitParameter("allowCredentials");
        allowHeaders = filterConfig.getInitParameter("allowHeaders");
        exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
    }

    /**
     * 通过CORS技术实现AJAX跨域访问, 只要将CORS响应头写入response对象中即可
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        if (StringUtil.isNotEmpty(allowOrigin)) {
            //允许访问的客户端域名，例如：http://web.xxx.com，若为*，则表示从任意域都能访问，即不做任何限制；
            response.setHeader("Access-Control-Allow-Origin", allowOrigin);
        }
        if (StringUtil.isNotEmpty(allowMethods)) {
            //允许访问的请求方式，多个用逗号分割，例如：GET,POST,PUT,DELETE,OPTIONS；
            response.setHeader("Access-Control-Allow-Methods", allowMethods);
        }
        if (StringUtil.isNotEmpty(allowCredentials)) {
            //是否允许请求带有验证信息，若要获取客户端域下的cookie时，需要将其设置为true；
            response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
        }
        if (StringUtil.isNotEmpty(allowHeaders)) {
            //允许服务端访问的客户端请求头，多个请求头用逗号分割，例如：Content-Type,Access-Token,timestamp
            response.setHeader("Access-Control-Allow-Headers", allowHeaders);
        }
        if (StringUtil.isNotEmpty(exposeHeaders)) {
            //允许客户端访问的服务端响应头，多个响应头用逗号分割。
            response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }

}
