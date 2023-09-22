package store.bizscanner.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import store.bizscanner.dto.response.cchange.CchangeResponse;
import store.bizscanner.entity.Cchange;
import store.bizscanner.global.exception.CustomException;
import store.bizscanner.global.exception.ErrorCode;
import store.bizscanner.repository.CchangeRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CchangeServiceTest {

    @InjectMocks
    private CchangeService cchangeService;

    @Mock
    private CchangeRepository cchangeRepository;

    String careaCode = "2110008";

    @DisplayName("상권변화지표_조회_성공")
    @Test
    void successFindByCareaCodeTest() {
        // given
        Optional<Cchange> cchange = Optional.of(new Cchange());

        // mocking
        when(cchangeRepository.findTopByCareaCodeOrderByYearCodeDescQuarterCodeDesc(any())).thenReturn(cchange);

        // when
        CchangeResponse cchangeResponse = cchangeService.findBycareaCode(careaCode);

        // then
        assertThat(cchangeResponse).isInstanceOf(CchangeResponse.class);
    }

    @DisplayName("상권변화지표_조회_실패")
    @Test
    void failFindByCareaCodeTest() {
        // given
        Optional<Cchange> cchange = Optional.empty();

        // mocking
        when(cchangeRepository.findTopByCareaCodeOrderByYearCodeDescQuarterCodeDesc(any())).thenReturn(cchange);

        // when
        // then
        assertThatThrownBy(() -> cchangeService.findBycareaCode(careaCode))
                .isInstanceOf(CustomException.class)
                .hasMessageContaining(ErrorCode.REPORT_RESOURCE_NOT_FOUND.getMessage());
    }
}
