package org.rcisoft.business.system.roleuser.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.system.auth.service.Impl.AuthUserServiceImpl;
import org.rcisoft.entity.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 16:36 2019/3/6
 */
@Api(tags = "用户管理菜单")
@RestController
@RequestMapping("userMenu")
public class SysUserMenuController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthUserServiceImpl authService;




    @ApiOperation(value = "菜单查询",notes = "菜单查询")
    @RequestMapping(value = "/menu",method = RequestMethod.POST)
    public Result userMenu(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        System.out.println(token);
        List<SysMenu> list = authService.userMenu(token);
        return Result.result(1,list);

    }

}
