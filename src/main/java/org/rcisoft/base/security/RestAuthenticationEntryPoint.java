package org.rcisoft.base.security;

import org.rcisoft.base.result.Result;
import org.rcisoft.base.result.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lcy on 17/11/23.
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * 实现AuthenticationEntryPoint的commence方法自定义校验不通过的方法
     *
     * @param request
     * @param response
     * @param e
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        // 捕获AuthenticationException中的message，并封装成自定义异常抛出
        response.setCharacterEncoding("utf-8");
        //header 起作用
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.getWriter().write(new Result().setCode(ResultCode.UNAUTHORIZED.code).setMsg("无权限").toString());
    }
}