package top.gwj.boot.config.controller;

import cn.hutool.core.date.DateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gwj.boot.config.tools.Result;

@RestController
public class HutoolController {

    @GetMapping("/date")
    public Result<String> formatCurrentDate() {
        String format = DateUtil.now();
        return Result.success(format);
    }


}