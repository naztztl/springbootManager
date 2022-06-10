package com.ztll.springboot_manage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ztll.springboot_manage.common.Constants;
import com.ztll.springboot_manage.controller.dto.UserDTO;
import com.ztll.springboot_manage.entity.Menu;
import com.ztll.springboot_manage.entity.User;
import com.ztll.springboot_manage.exception.ServiceException;
import com.ztll.springboot_manage.mapper.RoleMapper;
import com.ztll.springboot_manage.mapper.RoleMenuMapper;
import com.ztll.springboot_manage.mapper.UserMapper;
import com.ztll.springboot_manage.service.IMenuService;
import com.ztll.springboot_manage.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ztll.springboot_manage.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ztll
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Log LOG = Log.get();

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;

    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one, userDTO, true);
            // 设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);
            String role = one.getRole();
            //设置用户的菜单列表
            List<Menu> roleMenus = getRoleMenus(role);
            userDTO.setMenus(roleMenus);
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    @Override
    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDTO, one, true);
            save(one);  // 把copy完之后的用户对象存储到数据库
        } else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return one;
    }

    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one;
        try {
            one = getOne(queryWrapper);    //从数据库查询用户信息
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }

    /**
     * 获取当前角色的菜单列表
     * @param roleFlag
     * @return
     */
    private List<Menu> getRoleMenus(String roleFlag) {
        Integer roleId = roleMapper.selectByFlag(roleFlag);
        //当前角色的所有菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);

        //查出系统所有的菜单
        List<Menu> menus = menuService.findMenus("");
        //new一个最后筛选完成之后的list
        List<Menu> roleMenus = new ArrayList<>();


        //筛选当前用户角色的菜单
        for (Menu menu : menus) {
//            roleMenus.add(menu);
//            if (menuIds.contains(menu.getId())) {
//                roleMenus.add(menu);
//            }
//            List<Menu> children = menu.getChildren();
//            for (Menu child : children) {
//                if (menuIds.contains(child.getId())) {
////                    roleMenus.add(menu);
//                    roleMenus.add(child);
//                }
//            }

//            if (!menuIds.contains(menu.getId())) {
//                List<Menu> children = menu.getChildren();
//                for (Menu child : children) {
//                    if (menuIds.contains(child.getId())) {
////                    roleMenus.add(menu);
//                        roleMenus.add(child);
//                    }
//                }
//            } else {
//                roleMenus.add(menu);
//            }
            roleMenus.add(menu);
            List<Menu> children = menu.getChildren();
            //remove 移除 children 里面不在 menuIds 集合中的元素
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
        return roleMenus;
    }
}
