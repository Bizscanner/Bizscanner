package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.repository.CareaRecommendConstantRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CareaRecommendConstantService {
    private final CareaRecommendConstantRepository careaRecommendConstantRepository;
}
