package store.bizscanner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.bizscanner.dto.response.sales.QuarterSalesCountListResponse;
import store.bizscanner.dto.response.earningexpenditure.ConsumptionTrendResponse;
import store.bizscanner.dto.response.cchange.CchangeResponse;
import store.bizscanner.dto.response.population.BestPopulationResponse;
import store.bizscanner.dto.response.rent.RentResponse;
import store.bizscanner.dto.response.sales.SalesAmountResponse;
import store.bizscanner.dto.response.store.BestJcategoryResponse;
import store.bizscanner.dto.response.store.QuarterlyCloseStoreResponse;
import store.bizscanner.dto.response.store.QuarterlyOpenStoreResponse;
import store.bizscanner.dto.response.store.QuarterlyStoreResponse;
import store.bizscanner.dto.response.population.PopulationResponse;
import store.bizscanner.dto.response.sales.BestSalesResponse;
import store.bizscanner.service.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {
    private final PopulationService populationService;
    private final SalesService salesService;
    private final StoreService storeService;
    private final RentService rentService;
    private final CchangeService cchangeService;
    private final EarningExpenditureService earningExpenditureService;

    @GetMapping("/best-population/{careaCode}")
    public ResponseEntity<BestPopulationResponse> bestPopulation(@PathVariable String careaCode) {
        return new ResponseEntity<>(populationService.bestPopulation(careaCode), HttpStatus.OK);
    }

    @GetMapping("/best-jcategory/{careaCode}")
    public ResponseEntity<BestJcategoryResponse> bestJcategory(@PathVariable String careaCode) {
        return new ResponseEntity<>(storeService.bestJcategory(careaCode), HttpStatus.OK);
    }

    @GetMapping("/population/{careaCode}")
    public ResponseEntity<PopulationResponse> getPopulation(@PathVariable String careaCode) {
        return new ResponseEntity<>(populationService.getPopulation(careaCode), HttpStatus.OK);
    }

    @GetMapping("/stores/{careaCode}/{jcategoryCode}")
    public ResponseEntity<QuarterlyStoreResponse> getQuarterlyStore(@PathVariable String careaCode, @PathVariable String jcategoryCode) {
        return new ResponseEntity<>(storeService.getQuarterlyStore(careaCode,jcategoryCode), HttpStatus.OK);
    }

    @GetMapping("best-sales-amount/{careaCode}")
    public ResponseEntity<BestSalesResponse> getBestSales(@PathVariable String careaCode) {
        return new ResponseEntity<>(salesService.getBestSales(careaCode), HttpStatus.OK);
    }

    @GetMapping("sales/count/{careaCode}/{jcategoryCode}")
    public ResponseEntity<QuarterSalesCountListResponse> getQuarterSalesCount(@PathVariable String careaCode, @PathVariable String jcategoryCode) {
        return new ResponseEntity<>(salesService.getQuarterSalesCount(careaCode, jcategoryCode), HttpStatus.OK);
    }

    @GetMapping("/rent/{careaCode}")
    public ResponseEntity<RentResponse> getRent(@PathVariable String careaCode) {
        return new ResponseEntity<>(rentService.getRent(careaCode), HttpStatus.OK);
    }

    @GetMapping("/stores/open-status/{careaCode}/{jcategoryCode}")
    public ResponseEntity<QuarterlyOpenStoreResponse> getQuarterlyOpenStore(@PathVariable String careaCode, @PathVariable String jcategoryCode) {
        return new ResponseEntity<>(storeService.getQuarterlyOpenStore(careaCode,jcategoryCode), HttpStatus.OK);
    }

    @GetMapping("/stores/close-status/{careaCode}/{jcategoryCode}")
    public ResponseEntity<QuarterlyCloseStoreResponse> getQuarterlyCloseStore(@PathVariable String careaCode, @PathVariable String jcategoryCode) {
        return new ResponseEntity<>(storeService.getQuarterlyCloseStore(careaCode,jcategoryCode), HttpStatus.OK);
    }

    @GetMapping("/cchange/{careaCode}")
    public ResponseEntity<CchangeResponse> getCareaChange(@PathVariable String careaCode){
        return new ResponseEntity<>(cchangeService.findBycareaCode(careaCode), HttpStatus.OK);
    }

    @GetMapping("/sales/amount/{careaCode}/{jcategoryCode}")
    public ResponseEntity<SalesAmountResponse> getSalesAmount(@PathVariable String careaCode, @PathVariable String jcategoryCode) {
        return new ResponseEntity<>(salesService.getSalesAmount(careaCode, jcategoryCode), HttpStatus.OK);
    }

    @GetMapping("/expenditure/{careaCode}")
    public ResponseEntity<ConsumptionTrendResponse> getConsumptionTrend(@PathVariable String careaCode) {
        return new ResponseEntity<>(earningExpenditureService.getConsumptionTrend(careaCode), HttpStatus.OK);
    }
}
