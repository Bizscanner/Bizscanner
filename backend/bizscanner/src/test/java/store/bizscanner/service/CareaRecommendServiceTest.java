package store.bizscanner.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.repository.SalesRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CareaRecommendServiceTest {

    @Autowired
    private CareaRecommendService careaRecommendService;
    @Autowired
    private SalesRepository salesRepository;

    @Test
    public void checkFind() {
        List<String> distinctJcategory = salesRepository.findDistinctJcategory();
        List<String> collect = distinctJcategory.stream().map(x -> careaRecommendService.findCareaRecommend(x, 100000000000L, 60L).getCareaCode())
                .collect(Collectors.toList());
    }
}