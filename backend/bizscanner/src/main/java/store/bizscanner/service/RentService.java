package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.dto.response.rent.RentResponse;
import store.bizscanner.global.exception.CustomException;
import store.bizscanner.global.exception.ErrorCode;
import store.bizscanner.repository.RentRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RentService {
    private final RentRepository rentRepository;
    private final CareaService careaService;

    /**
     * 임대료
     * @param careaCode
     * @return 해당 상권의 임대료 정보 반환
     */
    public RentResponse getRent(String careaCode) {
        return new RentResponse(rentRepository.findById(careaService.findByCareaCode(careaCode).getRentId())
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND)));
    }
}
