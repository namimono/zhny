package org.rcisoft.business.system.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.system.auth.service.Impl.AuthUserServiceImpl;
import org.rcisoft.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 16:36 2019/3/6
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("authUser")
public class AuthUserController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthUserServiceImpl authService;


    @ApiOperation(value = "用户登录",notes = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result createAuthenticationToken(String username,String password)throws AuthenticationException{
        return Result.result(authService.login(username, password));
    }

    @ApiOperation(value = "用户登录刷新",notes = "用户登录刷新")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Result refreshAuthenticationToken(HttpServletRequest request)throws AuthenticationException{
        String token = request.getHeader(tokenHeader);
        String refreshedToken = authService.refresh(token);
        return Result.result(1,refreshedToken);
    }

    @ApiOperation(value = "添加用户",notes = "添加用户")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(SysUser addedUser)throws AuthenticationException{
        Integer result = authService.register(addedUser);
        return Result.result(result,result);
    }


}
