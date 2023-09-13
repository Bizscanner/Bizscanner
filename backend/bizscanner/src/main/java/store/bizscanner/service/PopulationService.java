package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.dto.response.population.BestPopulationResponse;
import store.bizscanner.dto.response.population.PopulationResponse;
import store.bizscanner.dto.response.population.QuarterlyPopulationResponse;
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

    // Best 유동인구 반환
    // DB에서 전체 데이터를 호출 후 각각의 Best 항목을 구해 Response에 맵핑하여 반환
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

    // Best 항목을 찾기 위한 Inner Class
    // 여러 Enum Type에 대응하기 위해 Object로 선언
    // value를 기준으로 정렬하는 규칙 생성
    public static class Best implements Comparable<Best> {
        Integer value;
        Object object;

        public Best (Integer value, Object object) {
            this.value = value;
            this.object = object;
        }
        @Override
        public int compareTo(Best o) {
            return o.value - this.value;
        }
    }

    // PriorityQueue를 이용해 유동인구가 가장 많은 항목 추출
    // Best 성별
    public Object getBestGender(Population population) {
        PriorityQueue<Best> maxPopulation = new PriorityQueue<>();

        maxPopulation.add(new Best(population.getMalePopulation(), Gender.MALE));
        maxPopulation.add(new Best(population.getFemalePopulation(), Gender.FEMALE));

        return maxPopulation.poll().object;
    }

    // Best 연령대
    public Object getBestAge(Population population) {
        PriorityQueue<Best> maxPopulation = new PriorityQueue<>();

        maxPopulation.add(new Best(population.getTeensPopulation(), Age.TEENS));
        maxPopulation.add(new Best(population.getTwentiesPopulation(), Age.TWENTIES));
        maxPopulation.add(new Best(population.getThirtiesPopulation(), Age.THIRTIES));
        maxPopulation.add(new Best(population.getFortiesPopulation(), Age.FORTIES));
        maxPopulation.add(new Best(population.getFiftiesPopulation(), Age.FIFTIES));
        maxPopulation.add(new Best(population.getSixtiesPopulation(), Age.SIXTIES));

        return maxPopulation.poll().object;
    }

    // Best 요일
    public Object getBestDay(Population population) {
        PriorityQueue<Best> maxPopulation = new PriorityQueue<>();

        maxPopulation.add(new Best(population.getMondayPopulation(), Day.MONDAY));
        maxPopulation.add(new Best(population.getTuesdayPopulation(), Day.TUESDAY));
        maxPopulation.add(new Best(population.getWednesdayPopulation(), Day.WEDNESDAY));
        maxPopulation.add(new Best(population.getThursdayPopulation(), Day.THURSDAY));
        maxPopulation.add(new Best(population.getFridayPopulation(), Day.FRIDAY));
        maxPopulation.add(new Best(population.getSaturdayPopulation(), Day.SATURDAY));
        maxPopulation.add(new Best(population.getSundayPopulation(), Day.SUNDAY));

        return maxPopulation.poll().object;
    }

    // Best 시간대
    public Object getBestTime(Population population) {
        PriorityQueue<Best> maxPopulation = new PriorityQueue<>();

        maxPopulation.add(new Best(population.getTime1Population(), Time.TIME1));
        maxPopulation.add(new Best(population.getTime2Population(), Time.TIME2));
        maxPopulation.add(new Best(population.getTime3Population(), Time.TIME3));
        maxPopulation.add(new Best(population.getTime4Population(), Time.TIME4));
        maxPopulation.add(new Best(population.getTime5Population(), Time.TIME5));
        maxPopulation.add(new Best(population.getTime6Population(), Time.TIME6));

        return maxPopulation.poll().object;
    }

    // 해당하는 상권의 유동인구 정보를 반환
    // 분기, 요일, 시간대, 성별, 연령대, 남성연령대, 여성연령대 별 유동인구 수를 반환
    public PopulationResponse getPopulation(String careaCode) {
        List<TotalPopulationMapping> quarterlyPopulation = populationRepository.findByCareaCodeAndYearCodeGreaterThanOrderByYearCodeAscQuarterCodeAsc(careaCode, "2021");

        return new PopulationResponse(
                quarterlyPopulation.stream().map(totalPopulationMapping -> new QuarterlyPopulationResponse(
                        totalPopulationMapping.getYearCode(),
                        totalPopulationMapping.getQuarterCode(),
                        totalPopulationMapping.getTotalPopulation()))
                        .collect(Collectors.toList()),
                populationRepository.findTopByCareaCodeOrderByYearCodeDescQuarterCodeDesc(careaCode)
                        .orElseThrow(() -> new CustomException(ErrorCode.REPORT_RESOURCE_NOT_FOUND)));
    }
}
