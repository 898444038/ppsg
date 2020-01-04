package com.ming.system.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ming.system.utils.ResultMsg;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 统一结果处理：没有携带token或者token无效
 */
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        String reason = "token失效，请重新登录："+authException.getMessage();
        response.getWriter().write(new ObjectMapper().writeValueAsString(reason));

//        String reason = "没有访问权限：" + e.getMessage();
//        Gson gson = new Gson();
//        String result = gson.toJson(ResultMsg.auth(reason));
//        PrintWriter out = null;
//        try {
//            out = httpServletResponse.getWriter();
//            out.append(result);
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        } finally {
//            if (out != null) {
//                out.close();
//            }
//        }
    }
}
