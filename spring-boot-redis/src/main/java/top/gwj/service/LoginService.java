package top.gwj.service;


import top.gwj.dto.LoginRequest;
import top.gwj.vo.LoginResponse;

public interface LoginService {
    /**
     * 验证码登录
     *
     * @param loginRequest 登录请求参数
     * @return 登陆响应
     */
    LoginResponse login(LoginRequest loginRequest);
}
