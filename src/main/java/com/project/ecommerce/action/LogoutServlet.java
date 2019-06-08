package com.project.ecommerce.action;

import com.project.ecommerce.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by qyh on 2019/6/8.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    private HttpServletRequest request;
    private HttpServletResponse response;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.request = req;
        this.response = resp;
        request.removeAttribute(Constants.USER_SESSION);
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
    }

}
