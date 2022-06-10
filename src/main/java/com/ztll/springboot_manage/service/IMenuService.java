package com.ztll.springboot_manage.service;

import com.ztll.springboot_manage.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ztll
 * @since 2022-05-28
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
