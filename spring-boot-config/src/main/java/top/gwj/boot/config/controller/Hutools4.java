package top.gwj.boot.config.controller;

import cn.hutool.core.util.DesensitizedUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.gwj.boot.config.tools.Result;

@RestController
public class Hutools4{

    @GetMapping("/string/desensitize/phone")
    public Result<String> desensitizePhone(@RequestParam String phone) {
        String desensitized = DesensitizedUtil.mobilePhone(phone);
        return Result.success(desensitized);
    }
}