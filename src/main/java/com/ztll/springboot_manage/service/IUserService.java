package com.ztll.springboot_manage.service;

import com.ztll.springboot_manage.controller.dto.UserDTO;
import com.ztll.springboot_manage.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ztll
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);
}
