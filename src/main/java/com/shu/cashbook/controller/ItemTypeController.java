package com.shu.cashbook.controller;

import com.shu.cashbook.common.BaseResult;
import com.shu.cashbook.common.utils.MainUtils;
import com.shu.cashbook.domain.ItemType;
import com.shu.cashbook.service.ItemTypeService;
import com.shu.cashbook.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @version 1.0
 * @author: yang
 * @date: 2019/2/22 20:59
 */
@Api(tags = "支出类型相关Controller")
@RestController
@RequestMapping("home")
public class ItemTypeController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ItemTypeService itemTypeService;
    @Resource
    private UserService userService;

    @GetMapping("itemType")
    @ApiOperation("获取支出类型选项")
    public BaseResult getItemType() {
        ItemType itemType = new ItemType();
        //查询
        itemType.setViewable(true);
        itemType.setUserId("public");
        List<ItemType> list = itemTypeService.select(itemType);
        itemType.setUserId(userService.getUsername());
        //合并查询结果
        list.addAll(itemTypeService.select(itemType));
        Map<String, String> map = new LinkedHashMap<>();
        for (ItemType type : list) {
            map.put(type.getId(), type.getItemName());
        }
        return BaseResult.success(map);
    }

    @PostMapping("itemType")
    @ApiOperation("新增支出类型选项")
    @ApiImplicitParam(name = "itemName", value = "支出类型名称")
    public BaseResult addItemType(ItemType itemType) {
        if (StringUtils.isBlank(itemType.getItemName())) {
            return BaseResult.failed(403, "请输入支出类型名称");
        }
        ItemType temp = itemTypeService.selectByName(itemType.getItemName());
        if (temp != null) {
            if (temp.getViewable()) {
                return BaseResult.failed(403, "该支出类型名称已经存在");
            } else {
                temp.setViewable(true);
                logger.info("新增支出类型{}成功", itemType.getItemName());
                return BaseResult.success("新增支出类型成功");
            }
        }
        itemType.setId(MainUtils.getUuid());
        itemType.setUserId(userService.getUser().getId());
        itemType.setViewable(true);
        itemTypeService.insert(itemType);
        logger.info("新增支出类型{}成功", itemType.getItemName());
        return BaseResult.success("新增支出类型{}成功", itemType.getItemName());
    }

    @PutMapping("itemType")
    @ApiOperation("修改支出类型选项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemName", value = "支出类型名称"),
            @ApiImplicitParam(name = "id", value = "ID")
    })
    public BaseResult updateItemType(ItemType itemType) {
        if (StringUtils.isBlank(itemType.getItemName())) {
            return BaseResult.failed(403, "请输入支出类型名称");
        }
        ItemType temp = itemTypeService.selectByName(itemType.getItemName());
        if (temp != null) {
            return BaseResult.failed(403, "该支出类型名称已经存在");
        }
        itemType.setUserId(userService.getUser().getId());
        itemType.setViewable(true);
        itemTypeService.update(itemType);
        logger.info("修改支出类型{}成功", itemType.getItemName());
        return BaseResult.success("修改支出类型{}成功", itemType.getItemName());
    }

    @DeleteMapping("itemType")
    @ApiOperation("删除支出类型选项")
    @ApiImplicitParam(name = "itemName", value = "支出类型名称")
    public BaseResult deleteItemType(ItemType itemType) {
        if (StringUtils.isBlank(itemType.getItemName())) {
            return BaseResult.failed(403, "请输入支出类型名称");
        }
        itemType.setViewable(false);
        logger.info("删除支出类型{}成功", itemType.getItemName());
        itemTypeService.update(itemType);
        return BaseResult.success("删除支出类型{}成功", itemType.getItemName());
    }
}
