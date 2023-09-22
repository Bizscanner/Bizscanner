package store.bizscanner.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import store.bizscanner.dto.response.cchange.CchangeResponse;
import store.bizscanner.dto.response.earningexpenditure.ConsumptionTrendResponse;
import store.bizscanner.dto.response.population.BestPopulationResponse;
import store.bizscanner.dto.response.population.PopulationResponse;
import store.bizscanner.dto.response.rent.RentResponse;
import store.bizscanner.dto.response.sales.BestSalesResponse;
import store.bizscanner.dto.response.sales.QuarterSalesCountListResponse;
import store.bizscanner.dto.response.sales.SalesAmountResponse;
import store.bizscanner.dto.response.store.BestJcategoryResponse;
import store.bizscanner.dto.response.store.QuarterlyCloseStoreResponse;
import store.bizscanner.dto.response.store.QuarterlyOpenStoreResponse;
import store.bizscanner.dto.response.store.QuarterlyStoreResponse;
import store.bizscanner.entity.Cchange;
import store.bizscanner.service.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ReportControllerTest {

    @InjectMocks
    private ReportController reportController;

    @Mock
    private CchangeService cchangeService;
    @Mock
    private EarningExpenditureService earningExpenditureService;
    @Mock
    private PopulationService populationService;
    @Mock
    private RentService rentService;
    @Mock
    private SalesService salesService;
    @Mock
    private StoreService storeService;

    private MockMvc mockMvc;

    String careaCode = "2110008";
    String jcategoryCode = "CS300002";

    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(reportController).build();
    }

    @DisplayName("Best 인구 호출")
    @Test
    void BestPopulationTest() throws Exception {

        // given
        BestPopulationResponse bestPopulationResponse = new BestPopulationResponse();
        Mockito.when(populationService.bestPopulation(any()))
                .thenReturn(bestPopulationResponse);

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/report/best-population/{careaCode}", careaCode));

        // then
        resultActions.andExpect(status().isOk());
    }

    @DisplayName("Best 업종 호출")
    @Test
    void BestJcategoryTest() throws Exception {

        // given
        BestJcategoryResponse bestJcategoryResponse = new BestJcategoryResponse();
        Mockito.when(storeService.bestJcategory(any()))
                .thenReturn(bestJcategoryResponse);

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/report/best-jcategory/{careaCode}", careaCode));

        // then
        resultActions.andExpect(status().isOk());
    }

    @DisplayName("유동인수 수 호출")
    @Test
    void PopulationTest() throws Exception {

        // given
        PopulationResponse populationResponse = new PopulationResponse();
        Mockito.when(populationService.getPopulation(any()))
                .thenReturn(populationResponse);

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/report/population/{careaCode}", careaCode));

        // then
        resultActions.andExpect(status().isOk());
    }

    @DisplayName("점포 수 호출")
    @Test
    void getQuarterlyStoreTest() throws Exception {

        // given

        QuarterlyStoreResponse quarterlyStoreResponse = new QuarterlyStoreResponse();
        Mockito.when(storeService.getQuarterlyStore(any(), any()))
                .thenReturn(quarterlyStoreResponse);

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/report/stores/{careaCode}/{jcategoryCode}", careaCode, jcategoryCode));

        // then
        resultActions.andExpect(status().isOk());
    }

    @DisplayName("Best 매출 호출")
    @Test
    void getBestSalesTest() throws Exception {

        // given
        BestSalesResponse bestSalesResponse = new BestSalesResponse();
        Mockito.when(salesService.getBestSales(any()))
                .thenReturn(bestSalesResponse);

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/report/best-sales-amount/{careaCode}", careaCode));

        // then
        resultActions.andExpect(status().isOk());
    }

    @DisplayName("매출 건수 호출")
    @Test
    void getQuarterSalesCountTest() throws Exception {

        // given
        QuarterSalesCountListResponse quarterSalesCountListResponse = new QuarterSalesCountListResponse();
        Mockito.when(salesService.getQuarterSalesCount(any(), any()))
                .thenReturn(quarterSalesCountListResponse);

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/report/sales/count/{careaCode}/{jcategoryCode}", careaCode, jcategoryCode));

        // then
        resultActions.andExpect(status().isOk());
    }

    @DisplayName("임대료 호출")
    @Test
    void getRentTest() throws Exception {

        // given
        RentResponse rentResponse = new RentResponse();
        Mockito.when(rentService.getRent(any()))
                .thenReturn(rentResponse);

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/report/rent/{careaCode}", careaCode));

        // then
        resultActions.andExpect(status().isOk());
    }

    @DisplayName("개업 현황 호출")
    @Test
    void getQuarterlyOpenStoreTest() throws Exception {

        // given
        QuarterlyOpenStoreResponse quarterlyOpenStoreResponse = new QuarterlyOpenStoreResponse();
        Mockito.when(storeService.getQuarterlyOpenStore(any(), any()))
                .thenReturn(quarterlyOpenStoreResponse);

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/report/stores/open-status/{careaCode}/{jcategoryCode}", careaCode, jcategoryCode));

        // then
        resultActions.andExpect(status().isOk());
    }

    @DisplayName("폐업 현황 호출")
    @Test
    void getQuarterlyCloseStoreTest() throws Exception {

        // given
        QuarterlyCloseStoreResponse quarterlyCloseStoreResponse = new QuarterlyCloseStoreResponse();
        Mockito.when(storeService.getQuarterlyCloseStore(any(), any()))
                .thenReturn(quarterlyCloseStoreResponse);

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/report/stores/close-status/{careaCode}/{jcategoryCode}", careaCode, jcategoryCode));

        // then
        resultActions.andExpect(status().isOk());
    }

    @DisplayName("상권 변화 지표 호출")
    @Test
    void CareaChangeTest() throws Exception {

        // given
        CchangeResponse cchangeResponse = new CchangeResponse(mock(Cchange.class));
        Mockito.when(cchangeService.findBycareaCode(any()))
                .thenReturn(cchangeResponse);

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/report/cchange/{careaCode}", careaCode));

        // then
        resultActions.andExpect(status().isOk());
    }

    @DisplayName("매출액 호출")
    @Test
    void getSalesAmountTest() throws Exception {

        // given
        SalesAmountResponse salesAmountResponse = new SalesAmountResponse();
        Mockito.when(salesService.getSalesAmount(any(), any()))
                .thenReturn(salesAmountResponse);

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/report/sales/amount/{careaCode}/{jcategoryCode}", careaCode, jcategoryCode));

        // then
        resultActions.andExpect(status().isOk());
    }

    @DisplayName("소비 트렌드 호출")
    @Test
    void getConsumptionTrendTest() throws Exception {

        // given
        ConsumptionTrendResponse consumptionTrendResponse = new ConsumptionTrendResponse();
        Mockito.when(earningExpenditureService.getConsumptionTrend(any()))
                .thenReturn(consumptionTrendResponse);

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .get("/report/expenditure/{careaCode}", careaCode));

        // then
        resultActions.andExpect(status().isOk());
    }
}
