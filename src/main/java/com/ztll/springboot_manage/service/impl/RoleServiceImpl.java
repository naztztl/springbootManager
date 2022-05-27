package com.ztll.springboot_manage.service.impl;

import com.ztll.springboot_manage.entity.Role;
import com.ztll.springboot_manage.mapper.RoleMapper;
import com.ztll.springboot_manage.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ztll
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
