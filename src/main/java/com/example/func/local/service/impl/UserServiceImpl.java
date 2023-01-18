package com.example.func.local.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.func.local.entity.UserEntity;
import com.example.func.local.mapper.UserEntityMapper;
import com.example.func.local.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lichuang
 * @date 2022/03/14
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserEntityMapper, UserEntity> implements UserService {


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void operate(UserEntity entity) {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<UserEntity>()
                .eq(UserEntity::getName, entity.getName());
        UserEntity db = getOne(queryWrapper);

        log.info("db:{}", JSONObject.toJSONString(db));

        LambdaUpdateWrapper<UserEntity> updateWrapper = new LambdaUpdateWrapper<UserEntity>()
                .eq(UserEntity::getId, db.getId())
                .set(UserEntity::getAge, 100);
        System.out.println(update(updateWrapper));

        UserEntity dbUpdate = getOne(queryWrapper);
        log.info("dbUpdate:{}", JSONObject.toJSONString(dbUpdate));
    }
}
