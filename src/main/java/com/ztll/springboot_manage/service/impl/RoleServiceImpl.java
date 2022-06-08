package com.ztll.springboot_manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ztll.springboot_manage.entity.Role;
import com.ztll.springboot_manage.entity.RoleMenu;
import com.ztll.springboot_manage.mapper.RoleMapper;
import com.ztll.springboot_manage.mapper.RoleMenuMapper;
import com.ztll.springboot_manage.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ztll
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<Integer> getRoleMenu(Integer roleId) {
        return roleMenuMapper.selectByRoleId(roleId);
    }

    @Transactional
    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {
//        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("role_id", roleId);
//        roleMenuMapper.delete(queryWrapper);

        //先删除当前角色id所有的绑定关系
        roleMenuMapper.deleteByRoleId(roleId);

        //再把前端传过来的菜单id数组绑定到当前的这个角色id上
        for (Integer menuId : menuIds) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        }
    }

}
