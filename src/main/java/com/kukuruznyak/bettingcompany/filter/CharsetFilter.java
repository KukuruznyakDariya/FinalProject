package com.kukuruznyak.bettingcompany.filter;

import com.kukuruznyak.bettingcompany.dao.impl.AbstractDaoImpl;
import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filter for encoding of request
 */
public class CharsetFilter implements Filter {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDaoImpl.class);
    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null) encoding = "UTF-8";
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException, ServletException {
        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(encoding);
        }
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        next.doFilter(request, response);
    }

    public void destroy() {
    }
}