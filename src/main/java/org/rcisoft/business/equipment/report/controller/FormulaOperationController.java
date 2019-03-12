package org.rcisoft.business.equipment.report.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.equipment.report.service.FormulaOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value="根据项目ID查询公式信息", notes="根据项目ID查询公式信息")
    @GetMapping("/queryFormula")
    public Result queryFormula(@RequestParam("projectId") String projectId){
        return Result.result(formulaOperationServiceImpl.queryFormula(projectId));
    }
}
