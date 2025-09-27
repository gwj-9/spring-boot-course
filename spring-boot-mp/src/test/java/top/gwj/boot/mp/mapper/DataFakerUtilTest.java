package top.gwj.boot.mp.mapper;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import top.gwj.boot.mp.utils.DataFakerUtil;

@SpringBootTest
class DataFakerUtilTest {

    @Resource
    private DataFakerUtil dataFakerUtil;

    @Test
    void generateData() {
        dataFakerUtil.generateBatch();
    }
}