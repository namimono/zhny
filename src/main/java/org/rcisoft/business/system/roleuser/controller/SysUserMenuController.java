package org.rcisoft.business.system.roleuser.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.system.auth.service.Impl.AuthUserServiceImpl;
import org.rcisoft.business.system.roleuser.entity.IdAndPassword;
import org.rcisoft.business.system.roleuser.service.SysUserMenuService;
import org.rcisoft.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @author GaoLiwei
 * @date 2019/4/23
 */
@Api(tags = "角色用户管理")
@RestController
@RequestMapping("userMenu")
public class SysUserMenuController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthUserServiceImpl authService;
    @Autowired
    private SysUserMenuService sysUserMenuService;

    @ApiOperation(value = "查询角色权限,既可以看到的菜单栏", notes = "参数：角色Flag(超级管理员:1;管理员:2;业主:3)")
    @GetMapping(value = "/getRolePermission/{flag}")
    public Result getRolePermission(@PathVariable("flag") String flag) {
        return Result.result(sysUserMenuService.getRolePermission(flag));
    }

    @ApiOperation(value = "查询角色用户,可以查询这个角色下的用户信息", notes = "参数：角色Flag(超级管理员:1;管理员:2;业主:3;团队负责人:4;巡检员:5;认定员:6)")
    @GetMapping(value = "/listRoleUser/{flag}")
    public Result listRoleUser(@PathVariable("flag") String flag) {
        return Result.result(sysUserMenuService.listRoleUser(flag));
    }

    @ApiOperation(value = "删除角色用户,可以删除这个角色下的某个用户信息", notes = "参数：角色Flag(超级管理员:1;管理员:2;业主:3;团队负责人:4;巡检员:5;认定员:6)，表ID（不是用户ID，是表id!!!）")
    @DeleteMapping(value = "/deleteRoleUser/{flag}/{id}")
    public Result deleteRoleUser(@PathVariable("flag") String flag, @PathVariable("id") String id ) {
        return Result.result(sysUserMenuService.deleteRoleUser(flag,id),"删除成功","删除失败");
    }


    @ApiOperation(value = "修改角色的权限", notes = "参数：角色id(超级管理员:1;管理员:2;业主:3),菜单栏id(包括一级菜单id与二级菜单id)")
    @PutMapping(value = "/setRolePermission")
    public Result setRolePermission(@RequestBody List<SysRoleMenuMid> sysRoleMenuMidList) {
        return Result.result(sysUserMenuService.setRolePermission(sysRoleMenuMidList), "修改成功", "修改失败");
    }

    @ApiOperation(value = "新增系统用户信息（这里只包含超级管理员，管理员，业主）", notes = "参数：用户名，密码，姓名，用户类型(只能是1，2，3中的一种)，手机号，邮箱")
    @PostMapping(value = "/saveSysUser")
    public Result saveSysUser(@RequestBody SysUser sysUser) {
        return Result.result(sysUserMenuService.saveSysUser(sysUser), "新增用户成功", "新增用户失败");
    }

    @ApiOperation(value = "编辑系统用户信息（这里只包含超级管理员，管理员，业主）", notes = "参数：id,姓名，用户类型(只能是1，2，3中的一种)，手机号，邮箱")
    @PutMapping(value = "/updateSysUser")
    public Result updateSysUser(@RequestBody SysUser sysUser) {
        return Result.result(sysUserMenuService.updateSysUser(sysUser), "编辑用户成功", "编辑用户失败");
    }

    @ApiOperation(value = "新增团队负责人", notes = "参数：负责人姓名，职务，职称，从业时间，荣誉")
    @PostMapping(value = "/savePrincipal")
    public Result savePrincipal(@RequestBody SysPrincipal sysPrincipal) {
        return Result.result(sysUserMenuService.savePrincipal(sysPrincipal), "新增团队负责人成功", "新增团队负责人失败");
    }

    @ApiOperation(value = "编辑团队负责人", notes = "参数：id,负责人姓名，职务，职称，从业时间，荣誉")
    @PutMapping(value = "/updatePrincipal")
    public Result updatePrincipal(@RequestBody SysPrincipal sysPrincipal) {
        return Result.result(sysUserMenuService.updatePrincipal(sysPrincipal), "编辑团队负责人成功", "编辑团队负责人失败");
    }

    @ApiOperation(value = "新增巡检员", notes = "参数：姓名，用户，密码")
    @PostMapping(value = "/saveInspector")
    public Result saveInspector(@RequestBody SysInspector sysInspector) {
        return Result.result(sysUserMenuService.saveInspector(sysInspector), "新增巡检员成功", "新增巡检员失败");
    }

    @ApiOperation(value = "编辑巡检员", notes = "参数：id,姓名")
    @PutMapping(value = "/updateInspector")
    public Result updateInspector(@RequestBody SysInspector sysInspector) {
        return Result.result(sysUserMenuService.updateInspector(sysInspector), "编辑巡检员成功", "编辑巡检员失败");
    }


    @ApiOperation(value = "新增认定员", notes = "参数：姓名，职业资质，从业时间，项目业绩")
    @PostMapping(value = "/saveAuthenticator")
    public Result saveAuthenticator(@RequestBody SysAuthenticator sysAuthenticator) {
        return Result.result(sysUserMenuService.saveAuthenticator(sysAuthenticator), "新增认定员成功", "新增认定员失败");
    }

    @ApiOperation(value = "编辑认定员", notes = "参数：姓名，职业资质，从业时间，项目业绩")
    @PutMapping(value = "/updateAuthenticator")
    public Result updateAuthenticator(@RequestBody SysAuthenticator sysAuthenticator) {
        return Result.result(sysUserMenuService.updateAuthenticator(sysAuthenticator), "编辑认定员成功", "编辑认定员失败");
    }



    @ApiOperation(value = "重置密码（只有超级管理员，管理员，业主，巡检员有）", notes = "参数：id,用户类型(只能是1，2，3，5中的一种)")
    @PutMapping(value = "/resetPassWord/{flag}/{id}")
    public Result resetPassWord(@PathVariable("flag") String flag , @PathVariable("id") String id ) {
        return Result.result(sysUserMenuService.resetPassWord(flag,id), "重置密码成功", "重置密码失败");
    }

    @ApiOperation(value = "分配项目时，查询所有项目名称", notes = "参数：用户id")
    @GetMapping(value = "/listProject/{id}")
    public Result listProject(@PathVariable("id") String id ) {
        return Result.result(sysUserMenuService.listProjectName(id));
    }

    @ApiOperation(value = "分配项目", notes = "参数：项目id,用户id")
    @PostMapping(value = "/saveUserProject")
    public Result saveUserProject(@RequestBody List<SysUserProjectMid> sysUserProjectMidList) {
        return Result.result(sysUserMenuService.saveUserProject(sysUserProjectMidList),"分配项目成功","分配项目失败");
    }

    @ApiOperation(value = "修改密码", notes = "参数：表ID，旧密码，新密码，角色标识")
    @PostMapping(value = "/updatePassword")
    public Result updatePassword(@RequestBody IdAndPassword idAndPassword) {
        return Result.result(sysUserMenuService.updatePassword(idAndPassword),"修改密码成功","修改密码失败");
    }


    @ApiOperation(value = "菜单查询", notes = "菜单查询")
    @RequestMapping(value = "/menu", method = RequestMethod.POST)
    public Result userMenu(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        System.out.println(token);
        List<SysMenu> list = authService.userMenu(token);
        return Result.result(1, list);

    }

}
