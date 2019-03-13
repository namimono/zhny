package org.rcisoft.business.system.other.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.base.result.ServiceResult;
import org.rcisoft.business.system.other.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by JiChao on 2019/3/13.
 * 登录页背景图
 */
@Api(tags = "系统管理--其他管理")
@RestController
@RequestMapping("other")
public class OtherController {

    @Autowired
    OtherService otherServiceImpl;

    @ApiOperation(value="查询所有背景图片的路径", notes="查询所有背景图片的路径")
    @GetMapping("/queryTeamByType")
    public Result queryImageUrlList() {
        return Result.result(otherServiceImpl.queryImageUrlList());
    }

    @ApiOperation(value="上传图片", notes="上传图片")
    @PostMapping("/uploadImage")
    public Result uploadImage(@RequestParam MultipartFile file) {
        ServiceResult serviceResult = otherServiceImpl.uploadImage(file);
        return Result.result(serviceResult.getResult(), serviceResult.getObject());
    }

    @ApiOperation(value="根据id删除文件及数据库记录", notes="根据id删除文件及数据库记录")
    @DeleteMapping("/deleteImage/{id}")
    public Result deleteImage(@PathVariable Integer id) {
        return Result.result(otherServiceImpl.deleteImage(id), null);
    }


}
