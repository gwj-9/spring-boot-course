package top.gwj.boot.config.controller;

import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.gwj.boot.config.tools.Result1;
@RestController
public class Hutool2 {
    @PostMapping("/encrypt")
    public Result1<String> md5Encrypt(@RequestBody String content) {
        String md5 = DigestUtil.md5Hex(content);
        return Result1.success(md5);
    }
}
