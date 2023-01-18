package com.example.func.local.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.func.local.entity.UserEntity;

/**
 * @author lichuang
 * @date 2022/03/14
 */
public interface UserService extends IService<UserEntity> {

    void operate(UserEntity entity);

}
