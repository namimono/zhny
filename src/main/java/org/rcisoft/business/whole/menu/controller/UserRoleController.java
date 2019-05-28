package org.rcisoft.business.whole.menu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.whole.menu.entity.FirstMenu;
import org.rcisoft.business.whole.menu.service.UserRoleService;
import org.rcisoft.entity.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Api(tags = "用户角色管理")
@RestController
@RequestMapping("userRoles")
public class UserRoleController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private UserRoleService userRoleService;
    @ApiOperation(value = "用户角色可访问菜单查询", notes = "用户角色可访问菜单查询")
    @PostMapping(value = "/roleMenu")
    public Result userMenu(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        List<FirstMenu> firstMenus = userRoleService.userRoleMenu(token);
        return Result.result(1,firstMenus);
    }
}
