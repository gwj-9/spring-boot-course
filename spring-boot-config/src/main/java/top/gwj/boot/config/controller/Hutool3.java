package top.gwj.boot.config.controller;

import cn.hutool.core.io.FileUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.gwj.boot.config.tools.Result1;

@RestController
public class Hutool3 {

    @GetMapping("/file/read")
    public Result1<String> readFile(@RequestParam String filePath) {
        String content = FileUtil.readUtf8String(filePath);
        return Result1.success(content);
    }
}