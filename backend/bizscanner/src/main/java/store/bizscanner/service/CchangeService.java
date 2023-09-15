package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.dto.response.cchange.CchangeResponse;
import store.bizscanner.global.exception.CustomException;
import store.bizscanner.global.exception.ErrorCode;
import store.bizscanner.repository.CchangeRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CchangeService {
    private final CchangeRepository cchangeRepository;

    /**
     * 상권 변화지표 API
     * @param careaCode
     * @return 상권의 가장 최신 상권변화지표
     */
    public CchangeResponse findBycareaCode(String careaCode){
        return new CchangeResponse(cchangeRepository.findTopByCareaCodeOrderByYearCodeDescQuarterCodeDesc(careaCode)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND)));
    }
}
