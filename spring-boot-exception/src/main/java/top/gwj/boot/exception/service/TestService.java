package top.gwj.boot.exception.service;


import org.springframework.stereotype.Service;
import top.gwj.boot.exception.enums.ErrorCode;
import top.gwj.boot.exception.exception.ServerException;

@Service
public class TestService {

    public void method1() {
        throw new ServerException("余额不足");
    }

    public void method2() {
        throw new ServerException(ErrorCode.FORBIDDEN);
    }
}
