package org.rcisoft.business.equipment.report.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.equipment.report.service.FormulaOperationService;
import org.rcisoft.entity.BusFormula;
import org.rcisoft.entity.BusVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 土豆儿
 * @date 2019/3/12 16:40
 **/
@Api(tags = "电子报表-公式运算")
@RestController
@RequestMapping("formulaOperation")
public class FormulaOperationController {

    @Autowired
    private FormulaOperationService formulaOperationServiceImpl;

    @ApiOperation(value="增加公式信息", notes="增加公式信息")
    @PostMapping("/addFormula")
    public Result addFormula(@RequestBody BusFormula busFormula){
        return Result.result(formulaOperationServiceImpl.addFormula(busFormula),"增加公式信息成功","增加公式信息失败");
    }

    @ApiOperation(value="删除公式信息", notes="删除公式信息")
    @DeleteMapping("/deleteFormula")
    public Result deleteFormula(@RequestBody BusFormula busFormula){
        return Result.result(formulaOperationServiceImpl.deleteFormula(busFormula),"删除公式信息成功","删除公式信息失败");
    }

    @ApiOperation(value="修改公式信息", notes="修改公式信息")
    @PutMapping("/updateFormula")
    public Result updateFormula(@RequestBody BusFormula busFormula){
        return Result.result(formulaOperationServiceImpl.updateFormula(busFormula),"修改公式信息成功","修改公式信息失败");
    }

    @ApiOperation(value="根据项目ID查询公式信息", notes="根据项目ID查询公式信息")
    @GetMapping("/queryFormula/{projectId}")
    public Result queryFormula(@PathVariable String projectId){
        return Result.result(formulaOperationServiceImpl.queryFormula(projectId));
    }

    @ApiOperation(value="查询参数来源", notes="查询参数来源")
    @GetMapping("/querySource")
    public Result querySource(){
        return Result.result(formulaOperationServiceImpl.querySource());
    }

    @ApiOperation(value="根据公式ID和项目ID查询变量", notes="根据公式ID和项目ID查询变量")
    @GetMapping("/queryVariable/{projectId}/{formulaId}")
    public Result queryVariable(@PathVariable String projectId,@PathVariable String formulaId){
        return Result.result(formulaOperationServiceImpl.queryVariable(projectId,formulaId));
    }

    @ApiOperation(value="增加变量信息", notes="增加变量信息")
    @PostMapping("/addVariable")
    public Result addVariable(@RequestBody BusVariable busVariable){
        return Result.result(formulaOperationServiceImpl.addVariable(busVariable),"增加变量信息成功","增加变量信息失败");
    }

    @ApiOperation(value="删除变量信息", notes="删除变量信息")
    @DeleteMapping("/deleteVariable")
    public Result deleteVariable(@RequestBody BusVariable busVariable){
        return Result.result(formulaOperationServiceImpl.deleteVariable(busVariable),"删除变量信息成功","删除变量信息失败");
    }

    @ApiOperation(value="修改变量信息", notes="修改变量信息")
    @PutMapping("/updateVariable")
    public Result updateVariable(@RequestBody BusVariable busVariable){
        return Result.result(formulaOperationServiceImpl.updateVariable(busVariable),"修改变量信息成功","修改变量信息失败");
    }

    @ApiOperation(value="根据项目ID和参数来源查询二级参数信息", notes="根据项目ID和参数来源查询二级参数信息")
    @GetMapping("/queryVariable/{projectId}/{sourceId}")
    public Result queryParamSecondByProId(@PathVariable String projectId,@PathVariable String sourceId){
        return Result.result(formulaOperationServiceImpl.queryParamSecondByProId(projectId,sourceId));
    }

    @ApiOperation(value="导出模板（项目维护-其他配置-参数库）", notes="导出模板（项目维护-其他配置-参数库）")
    @GetMapping("/downloadFormulaData")
    public void downloadFormulaData(HttpServletResponse response, String projectId, String beginTime, String endTime, List<BusFormula> formulaList){
        formulaOperationServiceImpl.downloadFormulaData(response,projectId,beginTime,endTime,formulaList);
    }
}
