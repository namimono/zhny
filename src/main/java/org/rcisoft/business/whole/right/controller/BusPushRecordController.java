package org.rcisoft.business.whole.right.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.whole.right.service.BusPushRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Minghui Xu
 * @Description:
 * @Date: Created in 10:27 2019/3/13
 */
@Api(tags = "消息推送")
@RestController
@RequestMapping("BusPushRecord")
public class BusPushRecordController {
    @Autowired
    private BusPushRecordService busPushRecordService;

    @ApiOperation(value = "获取消息时间和内容",notes = "获取消息时间和内容")
    @RequestMapping(value = "queryContent",method = RequestMethod.GET)
    public Result queryContent(){
        return Result.result(1,busPushRecordService.queryContent());
    }

}
