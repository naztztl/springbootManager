package com.ztll.springboot_manage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.ztll.springboot_manage.service.IRoleService;
import com.ztll.springboot_manage.entity.Role;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ztll
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService roleService;

    // 新增和修改
    @PostMapping
    public boolean save(@RequestBody Role role) {
        //新增或者更新
        return roleService.saveOrUpdate(role);
    }

    //根据id删除
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return roleService.removeById(id);
    }

    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return roleService.removeByIds(ids);
    }

    // 查询所有数据
    @GetMapping
    public List<Role> findAll() {
        return roleService.list();
    }

    // 根据id查询
    @GetMapping("/{id}")
    public Role findOne(@PathVariable Integer id) {
        return roleService.getById(id);
    }

    //分页查询
    @GetMapping("/page")
    public Page<Role> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {

        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return roleService.page(new Page<>(pageNum, pageSize), queryWrapper);
    }


}

