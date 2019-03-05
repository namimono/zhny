package org.rcisoft.controller.projMaintenance;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.service.projMaintenance.Impl.ProjConfigServiveImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Create by 土豆儿
 * Time：2019/3/4 14:54
 **/

@Api(tags = "项目配置")
@RestController
@RequestMapping("projConfig")
public class ProjConfigController {

    @Autowired
    private ProjConfigServiveImpl projConfigServive;

    @ApiOperation(value="获取全部项目表信息", notes="获取全部项目表信息")
    @GetMapping("/queryAllInfo")
    public Result queryAllInfo(){
        return Result.result(1, projConfigServive.queryAllInfo());
    }

    @ApiOperation(value="设置项目配置信息", notes="设置项目配置信息")
    @PostMapping("/setProjConfig")
    public Result setProjConfig(@RequestBody Map<String,Object> map){
        return Result.result(1, projConfigServive.setProjConfig(map));
    }

    @ApiOperation(value="获取省份信息及其ID", notes="获取省份信息及其ID")
    @GetMapping("/queryProvinceInfo")
    public Result queryProvinceInfo(){
        return Result.result(1, projConfigServive.queryProvinceInfo());
    }

    @ApiOperation(value="根据省份ID获取城市信息及其code", notes="根据省份ID获取城市信息及其code")
    @GetMapping("/queryCityInfo")
    public Result queryCityInfo(@RequestParam("provinceId") String provinceId){
        return Result.result(1, projConfigServive.queryCityInfo(provinceId));
    }

    @ApiOperation(value="获取线下团队信息", notes="获取线下团队信息")
    @GetMapping("/queryOutTeamInfo")
    public Result queryOutTeamInfo(){
        return Result.result(1, projConfigServive.queryOutTeamInfo());
    }

    @ApiOperation(value="获取线上团队信息", notes="获取线上团队信息")
    @GetMapping("/queryOnTeamInfo")
    public Result queryOnTeamInfo(){
        return Result.result(1, projConfigServive.queryOnTeamInfo());
    }
}
