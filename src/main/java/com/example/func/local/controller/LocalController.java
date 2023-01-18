package com.example.func.local.controller;

import com.example.func.local.common.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lichuang
 * @date 2022/03/14
 */
@Slf4j
@RestController
public class LocalController {

    @GetMapping("/v1/get")
    public ApiResult get() {
        return ApiResult.ok("ok");
    }

}
