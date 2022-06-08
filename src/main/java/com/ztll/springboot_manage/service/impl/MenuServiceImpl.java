package com.ztll.springboot_manage.service.impl;

import com.ztll.springboot_manage.entity.Menu;
import com.ztll.springboot_manage.mapper.MenuMapper;
import com.ztll.springboot_manage.service.IMenuService;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
