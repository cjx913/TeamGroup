package com.cjx913.teamgroup.web.filter;

import com.cjx913.teamgroup.jdbc.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "TransactionFilter", urlPatterns = "/do/*")
public class TransactionFilter implements Filter {
    @Override
    public void destroy() {
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try {
            TransactionManager.begin();
            chain.doFilter(req, resp);
            TransactionManager.commit();
        } catch (Exception e) {
            TransactionManager.rollback();
        }
    }
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
