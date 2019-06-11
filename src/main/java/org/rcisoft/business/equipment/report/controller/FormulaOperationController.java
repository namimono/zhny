package org.rcisoft.business.equipment.report.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.equipment.report.entity.FormulaParams;
import org.rcisoft.business.equipment.report.service.FormulaOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @ApiOperation(value="编辑公式和变量",notes="需要的参数（字段名称与数据库一致） 公式：busFormula，参数集合：variableList，删除的参数ids：deleteVariableIds")
    @PostMapping("/editFormulaAndVariable")
    public Result editFormulaAndVariable(@RequestBody FormulaParams formulaParams) {
        return Result.result(formulaOperationServiceImpl.editFormulaAndVariable(formulaParams), "保存公式成功", "保存公式失败");
    }

    @ApiOperation(value="删除公式",notes="需要的参数（字段名称与数据库一致） 公式id")
    @GetMapping("/deleteFormula/{formulaId}")
    public Result deleteFormula(@PathVariable String formulaId) {
        return Result.result(formulaOperationServiceImpl.deleteFormula(formulaId), "删除公式成功", "删除公式失败");
    }

    @ApiOperation(value="查询所有公式和变量",notes="需要的参数（字段名称与数据库一致） 项目id")
    @GetMapping("/queryFormulaAndVariale/{projectId}")
    public Result queryFormulaAndVariale(@PathVariable String projectId) {
        return Result.result(formulaOperationServiceImpl.queryFormulaAndVariale(projectId));
    }

    @ApiOperation(value="查询echart图的数据",notes="需要的参数（字段名称与数据库一致） 项目id，年月日")
    @GetMapping("/queryData/{projectId}/{date}")
    public Result queryData(@PathVariable String projectId, @PathVariable String date) {
        return Result.result(formulaOperationServiceImpl.queryData(projectId, date));
    }

    @ApiOperation(value="下载echart图数据",notes="需要的参数（字段名称与数据库一致） 项目id，年月日")
    @GetMapping("/downloadData/{projectId}/{date}")
    public void downloadData(HttpServletRequest request, HttpServletResponse response, @PathVariable String projectId, @PathVariable String date) {
        formulaOperationServiceImpl.downloadData(request, response, projectId, date);
    }
}
