package org.rcisoft.business.monitor.intercept.controller;

import io.swagger.annotations.Api;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.monitor.intercept.service.BusTopologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 11:01 2019/3/13
 */
@Api(tags = "拓扑图")
@RestController
@RequestMapping("BusTopology")
public class BusTopologyController {
    @Autowired
    private BusTopologyService busTopologyService;

    @RequestMapping(value = "topoJson",method = RequestMethod.GET)
    public Result queryTopoJson(){
        return Result.result(1,busTopologyService.queryTopoJson());
    }




}
