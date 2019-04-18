package org.rcisoft.business.system.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.rcisoft.base.result.Result;
import org.rcisoft.business.system.project.service.TopologicalService;
import org.rcisoft.entity.BusTopology;
import org.rcisoft.entity.BusTopologyNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        return Result.result(topologicalServiceImpl.addTopology(busTopology),"新增拓扑图信息成功","新增拓扑图信息失败");
    }

    @ApiOperation(value="删除拓扑图信息", notes="删除拓扑图信息")
    @DeleteMapping("/deleteTopology")
    public Result deleteTopology(@RequestBody BusTopology busTopology){
        return Result.result(topologicalServiceImpl.deleteTopology(busTopology),"删除拓扑图信息成功","删除拓扑图信息失败");
    }

    @ApiOperation(value="修改拓扑图信息", notes="修改拓扑图信息")
    @PutMapping("/updateTopology")
    public Result updateTopology(@RequestBody BusTopology busTopology){
        return Result.result(topologicalServiceImpl.updateTopology(busTopology),"修改拓扑图信息成功","修改拓扑图信息失败");
    }

    @ApiOperation(value="查询拓扑图信息", notes="查询拓扑图信息")
    @GetMapping("/queryTopology/{projectId}/{systemId}")
    public Result queryTopology(@PathVariable String projectId,@PathVariable String systemId){
        BusTopology busTopology = new BusTopology();
        busTopology.setProjectId(projectId);
        busTopology.setSystemId(systemId);
        return Result.result(topologicalServiceImpl.queryTopology(busTopology));
    }

    @ApiOperation(value="新增拓扑图节点图片信息", notes="新增拓扑图节点图片信息")
    @PostMapping("/addTopologyNode")
    public Result addTopologyNode(@RequestParam MultipartFile file, @RequestParam(value = "proId") String proId){
        return Result.result(topologicalServiceImpl.addTopologyNode(file,proId),"新增拓扑图节点图片信息成功","新增拓扑图节点图片信息成功");
    }

    @ApiOperation(value="删除拓扑图节点图片信息", notes="删除拓扑图节点图片信息")
    @DeleteMapping("/deleteTopologyNode/{nodeId}")
    public Result deleteTopologyNode(@PathVariable String nodeId){
        return Result.result(topologicalServiceImpl.deleteTopologyNode(nodeId),"删除拓扑图节点图片信息成功","删除拓扑图节点图片信息失败");
    }

    @ApiOperation(value="查询拓扑图节点图片信息", notes="查询拓扑图节点图片信息")
    @GetMapping("/queryTopologyNode/{proId}")
    public Result queryTopologyNode(@PathVariable String proId){
        return Result.result(topologicalServiceImpl.queryTopologyNode(proId));
    }

    @ApiOperation(value="根据图片ID查询设备信息", notes="根据图片ID查询设备信息")
    @GetMapping("/queryDeviceByPicId/{picId}")
    public Result queryDeviceByPicId(@PathVariable String picId){
        return Result.result(topologicalServiceImpl.queryDeviceByPicId(picId));
    }
}
