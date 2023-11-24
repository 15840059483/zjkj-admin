package com.jeebase.system.webchat.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeebase.common.base.PageResult;
import com.jeebase.common.base.Result;
import com.jeebase.system.common.dto.LogInfo;
import com.jeebase.system.common.dto.QueryLog;
import com.jeebase.system.webchat.entity.Pmis;
import com.jeebase.system.webchat.service.PmisService;
import com.jeebase.system.webchat.service.imp.PmisServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jline.internal.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "项目管理")
@RequestMapping("/pmis")
public class PmisController {

    @Autowired
    PmisService pmisService;

    @PostMapping("/savePmis")
    @ApiOperation("增加")
    public Result<Pmis> savePmis(Pmis pmis){
        String name = pmis.getName();
        LambdaQueryWrapper<Pmis> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Pmis::getName,name);
        List<Pmis> lists = pmisService.list(lqw);
        if (lists.size() > 0){
            return new Result().error("不允许添加重复项目");
        }

        int save = pmisService.savePmis(pmis);
        if (save>0){
            return new Result().success("添加成功");
        }else {
            return new Result().error("添加失败");
        }
    }

    @GetMapping("/deletePmis")
    @ApiOperation("删除")
    public Result<Pmis> deletePmis(String id){
        Log.info(id);
        Long Id = new Long(id);
        int del = pmisService.deletePmis(Id);
        if (del>0){
            return new Result().success("添加成功");
        }else {
            return new Result().error("添加失败");
        }
    }

    @PostMapping("/updatePmis")
    @ApiOperation("修改")
    public Result<Pmis> updatePmis(Pmis pmis){
        String name = pmis.getName();
        LambdaQueryWrapper<Pmis> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Pmis::getName,name);
        List<Pmis> lists = pmisService.list(lqw);
        if (lists.size() > 0){
            return new Result().error("不允许添加重复项目");
        }
        int edit = pmisService.updatePmis(pmis);
        if (edit>0){
            return new Result().success("添加成功");
        }else {
            return new Result().error("添加失败");
        }
    }

    @GetMapping("/listPmis")
    @ApiOperation("查询")
    public PageResult<Pmis> list(Pmis pmis, Page<Pmis> page) {
        Page<Pmis> pagePmis = pmisService.selectPmisList(page, pmis);
        PageResult<Pmis> pageResult = new PageResult<Pmis>(pagePmis.getTotal(), pagePmis.getRecords());
        return pageResult;
    }

    @GetMapping("/list")
    @ApiOperation("查询id,name")
    public List listPmis() {
        return pmisService.selectPmis();
    }
//    @GetMapping("/listPmist")
//    @ApiOperation("分页查询测试")
//    public Result<Page> lists(int page , int pagesize) {
//        Page<Pmis> pageinfo = new Page<>(page, pagesize);
//        pmisService.page(pageinfo);
//        return new Result<Page>().put(pageinfo);
//    }

}
