package com.hotel.filters;

import com.hotel.dto.UserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "UserFilter", urlPatterns = "/pages/user/*")
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        UserDTO userDTO = (UserDTO) req.getSession().getAttribute("user");
        System.out.println("user" + userDTO);
        if (userDTO != null && userDTO.getRoleId().equals("3")) {
            chain.doFilter(req, resp);
        } else {
            req.getSession().setAttribute("url", req.getRequestURI());
            req.getSession().setAttribute("message", "You need to be register");
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
