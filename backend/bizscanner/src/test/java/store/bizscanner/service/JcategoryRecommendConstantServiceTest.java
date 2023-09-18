package store.bizscanner.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.global.exception.CustomException;

@SpringBootTest
@Transactional
class JcategoryRecommendConstantServiceTest {

    @Autowired
    private JcategoryRecommendConstantService jcategoryRecommendConstantService;

    @Test
    void recommendConstant_EmptyCheck(){
        Assertions.assertThatThrownBy(() -> jcategoryRecommendConstantService.getJcategoryRecommend("123"))
                .isInstanceOf(CustomException.class)
                .hasMessage("Report Resource not exists");
    }
}
