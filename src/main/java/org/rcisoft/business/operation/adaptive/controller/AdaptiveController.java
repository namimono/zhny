package org.rcisoft.business.operation.adaptive.controller;

import io.swagger.annotations.Api;
import org.rcisoft.business.operation.adaptive.service.AdaptiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 土豆儿
 * @date 2019/3/18 10:51
 **/
@Api(tags = "节能运维-自适应模块")
@RestController
@RequestMapping("adaptive")
public class AdaptiveController {

    @Autowired
    private AdaptiveService adaptiveServiceImpl;
}
