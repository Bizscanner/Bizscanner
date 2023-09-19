package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.dto.response.population.BestPopulationResponse;
import store.bizscanner.dto.response.population.PopulationResponse;
import store.bizscanner.dto.response.population.QuarterlyPopulationResponse;
import store.bizscanner.entity.Best;
import store.bizscanner.entity.Population;
import store.bizscanner.entity.enums.Age;
import store.bizscanner.entity.enums.Day;
import store.bizscanner.entity.enums.Gender;
import store.bizscanner.entity.enums.Time;
import store.bizscanner.global.exception.CustomException;
import store.bizscanner.global.exception.ErrorCode;
import store.bizscanner.repository.PopulationRepository;
import store.bizscanner.repository.mapping.TotalPopulationMapping;

import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PopulationService {

    private final PopulationRepository populationRepository;

    private static final String QUARTER_YEAR = "2021";
    private static final int REQUIRED_LEAST_RESULT_COUNT = 2;

    /**
     * Best 유동인구 API
     * @param careaCode
     * @return Best 유동인구 반환
     */
    public BestPopulationResponse bestPopulation(String careaCode) {
        Population population = populationRepository.findTopByCareaCodeOrderByYearCodeDescQuarterCodeDesc(careaCode)
                .orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND));

        return new BestPopulationResponse(
                (Gender) getBestGender(population),
                (Age) getBestAge(population),
                (Day) getBestDay(population),
                (Time) getBestTime(population)
        );
    }

    /**
     * Best 유동인구 - 성별
     * @param population
     * @return Best 성별
     */
    public Object getBestGender(Population population) {
        PriorityQueue<Best> maxPopulation = new PriorityQueue<>();

        maxPopulation.add(new Best(population.getMalePopulation().longValue(), Gender.MALE));
        maxPopulation.add(new Best(population.getFemalePopulation().longValue(), Gender.FEMALE));

        return maxPopulation.poll().getObject();
    }

    /**
     * Best 유동인구 - 연령대
     * @param population
     * @return Best 연령대
     */
    public Object getBestAge(Population population) {
        PriorityQueue<Best> maxPopulation = new PriorityQueue<>();

        maxPopulation.add(new Best(population.getTeensPopulation().longValue(), Age.TEENS));
        maxPopulation.add(new Best(population.getTwentiesPopulation().longValue(), Age.TWENTIES));
        maxPopulation.add(new Best(population.getThirtiesPopulation().longValue(), Age.THIRTIES));
        maxPopulation.add(new Best(population.getFortiesPopulation().longValue(), Age.FORTIES));
        maxPopulation.add(new Best(population.getFiftiesPopulation().longValue(), Age.FIFTIES));
        maxPopulation.add(new Best(population.getSixtiesPopulation().longValue(), Age.SIXTIES));

        return maxPopulation.poll().getObject();
    }

    /**
     * Best 유동인구 - 요일
     * @param population
     * @return Best 요일
     */
    public Object getBestDay(Population population) {
        PriorityQueue<Best> maxPopulation = new PriorityQueue<>();

        maxPopulation.add(new Best(population.getMondayPopulation().longValue(), Day.MONDAY));
        maxPopulation.add(new Best(population.getTuesdayPopulation().longValue(), Day.TUESDAY));
        maxPopulation.add(new Best(population.getWednesdayPopulation().longValue(), Day.WEDNESDAY));
        maxPopulation.add(new Best(population.getThursdayPopulation().longValue(), Day.THURSDAY));
        maxPopulation.add(new Best(population.getFridayPopulation().longValue(), Day.FRIDAY));
        maxPopulation.add(new Best(population.getSaturdayPopulation().longValue(), Day.SATURDAY));
        maxPopulation.add(new Best(population.getSundayPopulation().longValue(), Day.SUNDAY));

        return maxPopulation.poll().getObject();
    }

    /**
     * Best 유동인구 - 시간대
     * @param population
     * @return Best 시간대
     */
    public Object getBestTime(Population population) {
        PriorityQueue<Best> maxPopulation = new PriorityQueue<>();

        maxPopulation.add(new Best(population.getTime1Population().longValue(), Time.TIME1));
        maxPopulation.add(new Best(population.getTime2Population().longValue(), Time.TIME2));
        maxPopulation.add(new Best(population.getTime3Population().longValue(), Time.TIME3));
        maxPopulation.add(new Best(population.getTime4Population().longValue(), Time.TIME4));
        maxPopulation.add(new Best(population.getTime5Population().longValue(), Time.TIME5));
        maxPopulation.add(new Best(population.getTime6Population().longValue(), Time.TIME6));

        return maxPopulation.poll().getObject();
    }

    /**
     * 유동인구 수 API
     * @param careaCode
     * @return 분기, 요일, 시간대, 성별, 연령대, 남성연령대, 여성연령대 별 유동인구 수를 반환
     */
    public PopulationResponse getPopulation(String careaCode) {
        List<TotalPopulationMapping> quarterlyPopulation = populationRepository.findByCareaCodeAndYearCodeGreaterThanOrderByYearCodeAscQuarterCodeAsc(careaCode, QUARTER_YEAR);
        if(quarterlyPopulation.size() < REQUIRED_LEAST_RESULT_COUNT){
            throw new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND);
        }
        return new PopulationResponse(
                quarterlyPopulation.stream()
                        .map(totalPopulationMapping -> new QuarterlyPopulationResponse(
                        totalPopulationMapping.getYearCode(),
                        totalPopulationMapping.getQuarterCode(),
                        totalPopulationMapping.getTotalPopulation()))
                        .collect(Collectors.toList()),
                populationRepository.findTopByCareaCodeOrderByYearCodeDescQuarterCodeDesc(careaCode)
                        .orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND)));
    }
}
