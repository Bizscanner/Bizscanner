package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.dto.response.BestPopulationResponse;
import store.bizscanner.entity.Population;
import store.bizscanner.entity.enums.Age;
import store.bizscanner.entity.enums.Day;
import store.bizscanner.entity.enums.Gender;
import store.bizscanner.entity.enums.Time;
import store.bizscanner.repository.PopulationRepository;

import java.util.PriorityQueue;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PopulationService {

    private final PopulationRepository populationRepository;

    public BestPopulationResponse bestPopulation(String careaCode) {
        Population population = populationRepository.findTopByCareaCodeOrderByYearCodeDescQuarterCodeDesc(careaCode);

        return new BestPopulationResponse(
                (Gender) getBestGender(population),
                (Age) getBestAge(population),
                (Day) getBestDay(population),
                (Time) getBestTime(population)
        );
    }

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

    public Object getBestGender(Population population) {
        PriorityQueue<Best> maxPopulation = new PriorityQueue<>();

        maxPopulation.add(new Best(population.getMalePopulation(), Gender.MALE));
        maxPopulation.add(new Best(population.getFemalePopulation(), Gender.FEMALE));

        return maxPopulation.poll().object;
    }

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
}
