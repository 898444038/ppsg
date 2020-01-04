package com.ming.system.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ming.system.utils.ResultMsg;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description:没有访问权限
 */
public class JWTAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        String reason = "没有访问权限：" + e.getMessage();
        Gson gson = new Gson();
        String result = gson.toJson(ResultMsg.auth(reason));
        PrintWriter out = null;
        try {
            out = httpServletResponse.getWriter();
            out.append(result);
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}