package com.admin.template.controller;

import com.admin.template.request.AddMenuReqVo;
import com.admin.template.request.MenuReqVo;
import com.admin.template.service.SystemMenuServiceImpl;
import com.admin.template.utils.CommonResult;
import com.admin.template.vo.SystemMenuSvcVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @className: SystemMenuController
 * @description:
 * @author: YangQian
 * @date: 2023/9/22 14:41
 */
@Api(tags = "管理后台 - 菜单")
@RequestMapping("/system/menu")
@RestController
public class SystemMenuController {

    @Resource
    private SystemMenuServiceImpl systemMenuService;

    @ApiOperation("获取导航栏菜单列表")
    @PostMapping("get_menu_list")
    public CommonResult<List<SystemMenuSvcVo>> getMenuList() {
        return CommonResult.success(systemMenuService.getMenuList());
    }

    @ApiOperation("获取菜单列表")
    @PostMapping("get_all_menu_list")
    public CommonResult<List<SystemMenuSvcVo>> getAllMenuList() {
        return CommonResult.success(systemMenuService.getAllMenuList());
    }

    @ApiOperation("新建菜单")
    @PostMapping("add_menu")
    public CommonResult<Integer> addMenu(@RequestBody @Valid AddMenuReqVo reqVo) {
        return CommonResult.success(systemMenuService.addMenu(reqVo));
    }

    @ApiOperation("编辑菜单")
    @PostMapping("update_menu")
    public CommonResult<Integer> updateMenu(@RequestBody @Valid AddMenuReqVo reqVo) {
        return CommonResult.success(systemMenuService.updateMenu(reqVo));
    }

    @ApiOperation("菜单排序")
    @PostMapping("menu_sort")
    public CommonResult<Integer> menuSort(@RequestBody @Valid MenuReqVo reqVo) {
        return CommonResult.success(systemMenuService.menuSort(reqVo));
    }
}
