package org.rcisoft.business.system.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.system.project.service.TopologicalService;
import org.rcisoft.entity.BusTopology;
import org.rcisoft.entity.BusTopologyNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 土豆儿
 * @date 2019/3/13 10:46
 **/
@Api(tags = "项目编辑-拓扑图绘制")
@RestController
@RequestMapping("topological")
public class TopologicalController {

    @Autowired
    private TopologicalService topologicalServiceImpl;

    @ApiOperation(value="新增拓扑图信息", notes="新增拓扑图信息")
    @PostMapping("/addTopology")
    public Result addTopology(@RequestBody BusTopology busTopology){
        return Result.result(1, topologicalServiceImpl.addTopology(busTopology));
    }

    @ApiOperation(value="删除拓扑图信息", notes="删除拓扑图信息")
    @DeleteMapping("/deleteTopology")
    public Result deleteTopology(@RequestBody BusTopology busTopology){
        return Result.result(1, topologicalServiceImpl.deleteTopology(busTopology));
    }

    @ApiOperation(value="修改拓扑图信息", notes="修改拓扑图信息")
    @PutMapping("/updateTopology")
    public Result updateTopology(@RequestBody BusTopology busTopology){
        return Result.result(1, topologicalServiceImpl.updateTopology(busTopology));
    }

    @ApiOperation(value="查询拓扑图信息", notes="查询拓扑图信息")
    @GetMapping("/queryTopology")
    public Result queryTopology(String projectId,String systemId){
        BusTopology busTopology = new BusTopology();
        busTopology.setProjectId(projectId);
        busTopology.setSystemId(systemId);
        return Result.result(topologicalServiceImpl.queryTopology(busTopology));
    }

    @ApiOperation(value="新增拓扑图节点图片信息", notes="新增拓扑图节点图片信息")
    @PostMapping("/addTopologyNode")
    public Result addTopologyNode(@RequestBody BusTopologyNode busTopologyNode){
        return Result.result(1, topologicalServiceImpl.addTopologyNode(busTopologyNode));
    }

    @ApiOperation(value="删除拓扑图节点图片信息", notes="删除拓扑图节点图片信息")
    @DeleteMapping("/deleteTopologyNode")
    public Result deleteTopologyNode(@RequestBody BusTopologyNode busTopologyNode){
        return Result.result(1, topologicalServiceImpl.deleteTopologyNode(busTopologyNode));
    }

    @ApiOperation(value="修改拓扑图节点图片信息", notes="修改拓扑图节点图片信息")
    @PutMapping("/upadteTopologyNode")
    public Result upadteTopologyNode(@RequestBody BusTopologyNode busTopologyNode){
        return Result.result(1, topologicalServiceImpl.upadteTopologyNode(busTopologyNode));
    }

    @ApiOperation(value="查询拓扑图节点图片信息", notes="查询拓扑图节点图片信息")
    @GetMapping("/queryTopologyNode")
    public Result queryTopologyNode(@RequestParam String systemId){
        BusTopologyNode busTopologyNode = new BusTopologyNode();
        busTopologyNode.setSystemId(systemId);
        return Result.result(topologicalServiceImpl.queryTopologyNode(busTopologyNode));
    }
}
