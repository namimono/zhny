package org.rcisoft.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with family.
 * User: cy
 * Date: 16/5/20
 * Time: 下午1:48
 * description: autowired servlet
 */
@Controller
public class HttpServletController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    public HttpServletController() {
    }

    /**
     * get token
     * @return
     */
    public String getToken(){
        // 取得header
        String authHeader = request.getHeader(this.tokenHeader);
        //判断header头
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            // The part after "Bearer "
            final String authToken = authHeader.substring(tokenHead.length());
            //从jwt中获取信息，如果要缓存很多信息可以用Claims
            //String username = JwtUtil.getUsernameFromToken(authToken);
            return authToken;
        }
        return null;
    }
}
