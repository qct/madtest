package madtest.servlet3;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Created by alex on 2016/8/23.
 */
@WebFilter(filterName = "filter-one", urlPatterns = "/*")
public class FilterOne implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Inside filter one.");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
