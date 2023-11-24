package com.jeebase.system.common.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jeebase.common.annotation.auth.NoAuthentication;
import com.jeebase.common.annotation.log.AroundLog;
import com.jeebase.common.base.Result;
import com.jeebase.system.common.dto.CreateDict;
import com.jeebase.system.common.dto.DictInfo;
import com.jeebase.system.common.dto.UpdateDict;
import com.jeebase.system.common.entity.Dict;
import com.jeebase.system.common.service.IDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 数据字典表 前端控制器
 * </p>
 *
 * @author fyy
 * @since
 */
@RestController
@RequestMapping("/common/dict")
@Api(tags = "字典相关功能")
public class DictController {

    @Autowired
    private IDictService dictService;

    /**
     * 查询字典里列表
     * 
     * @param parentId
     * @return
     */
    @GetMapping(value = "/tree")
    @RequiresRoles("SYSADMIN")
    @ApiOperation(value = "查询字典树列表", notes = "查询字典树列表")
    @ApiImplicitParam(paramType = "query", name = "parentId", value = "父级ID", required = false, dataType = "Integer")
    public Result<List<Dict>> queryDictTree(Integer parentId) {
        List<Dict> treeList = dictService.queryDictTreeByPanentId(parentId);
        return new Result<List<Dict>>().success().put(treeList);
    }

    /**
     * 添加字典
     */
    @PostMapping("/create")
    @RequiresRoles("SYSADMIN")
    @ApiOperation(value = "添加字典")
    @AroundLog(name = "添加字典")
    public Result<Dict> create(@RequestBody CreateDict org) {
        String dictName = org.getDictName();
        Long parentId = org.getParentId(); //字典类型
        LambdaQueryWrapper<Dict> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Dict::getDictName,dictName);
        lqw.eq(Dict::getParentId,parentId);
        List<Dict> list = dictService.list(lqw);
        if (list.size() > 0){
            return new Result<Dict>().error("字典名重复，请勿重复添加");
        }
        Dict orgEntity = new Dict();
        BeanCopier.create(CreateDict.class, Dict.class, false).copy(org, orgEntity, null);
        boolean result = dictService.save(orgEntity);
        if (result) {
            return new Result<Dict>().success("添加成功").put(orgEntity);
        } else {
            return new Result<Dict>().error("添加失败，请重试");
        }
    }

    /**
     * 修改字典
     */
    @PostMapping("/update")
    @RequiresRoles("SYSADMIN")
    @ApiOperation(value = "更新字典")
    @AroundLog(name = "更新字典")
    public Result<Dict> update(@RequestBody UpdateDict org) {
        String dictName = org.getDictName();
        Long parentId = org.getParentId(); //字典类型
        LambdaQueryWrapper<Dict> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Dict::getDictName,dictName);
        lqw.eq(Dict::getParentId,parentId);
        List<Dict> list = dictService.list(lqw);
        if (list.size() > 0){
            return new Result<Dict>().error("字典名重复，请勿重复添加");
        }
        Dict orgEntity = new Dict();
        BeanCopier.create(UpdateDict.class, Dict.class, false).copy(org, orgEntity, null);
        boolean result = dictService.updateById(orgEntity);
        if (result) {
            return new Result<Dict>().success("修改成功").put(orgEntity);
        } else {
            return new Result<Dict>().error("修改失败");
        }
    }

    /**
     * 删除字典
     */
    @PostMapping("/delete/{orgId}")
    @RequiresRoles("SYSADMIN")
    @ApiOperation(value = "删除字典")
    @AroundLog(name = "删除字典")
    @ApiImplicitParam(paramType = "path", name = "orgId", value = "字典ID", required = true, dataType = "Integer")
    public Result<?> delete(@PathVariable("orgId") Integer orgId) {
        QueryWrapper<Dict> wpd = new QueryWrapper<Dict>();
        wpd.and(e -> e.eq("id", orgId).or().eq("parent_id", orgId));
        boolean result = dictService.remove(wpd);
        if (result) {
            return new Result<>().success("删除成功");
        } else {
            return new Result<>().error("删除失败");
        }
    }
    
    /**
     * 查询字典里列表
     *
     * @param dictCode
     * @return
     */
    @PostMapping(value = "/list/{dictCode}")
    @NoAuthentication
    @ApiOperation(value = "查询字典列表", notes = "查询字典列表")
    @ApiImplicitParam(paramType = "query", name = "dictCode", value = "字典值", required = true, dataType = "String")
    public Result<List<Dict>> queryDictList(@PathVariable("dictCode") String dictCode) {
        List<Dict> dictList = dictService.queryDictListByPanentCode(dictCode);
        return new Result<List<Dict>>().success().put(dictList);
    }
}
