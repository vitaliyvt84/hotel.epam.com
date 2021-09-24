package com.hotel.filters;

import com.hotel.dto.UserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter", urlPatterns = "/pages/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");
        if (userDTO != null && userDTO.getRoleId().equals("1")) {
            chain.doFilter(req, resp);
        } else {
            req.getSession().setAttribute("url", req.getRequestURI());
            req.getSession().setAttribute("message", "You have to be admin");
            resp.sendRedirect(req.getContextPath() + "/login");
        }

    }
}
