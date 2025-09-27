package top.gwj.boot.config.controller;

import cn.hutool.http.HttpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gwj.boot.config.tools.Result;

@RestController
public class Hutools5 {

    @GetMapping("/http/call")
    public Result<String> callExternalApi() {
        String url = "https://tianqi.moji.com/today/china/beijing/beijing-olympic-forest-park";
        // 实际要去申请天气 API 的 appid 等，这里只是示例结构
        String result = HttpUtil.get(url);
        return Result.success(result);
    }
}