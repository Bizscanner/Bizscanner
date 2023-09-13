package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.entity.Carea;
import store.bizscanner.global.exception.CustomException;
import store.bizscanner.global.exception.ErrorCode;
import store.bizscanner.repository.CareaRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CareaService {
    private final CareaRepository careaRepository;

    public Carea findByCareaCode(String careaCode){
        return careaRepository.findByCareaCode(careaCode).orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND));
    }
}
