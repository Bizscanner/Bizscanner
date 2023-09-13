package store.bizscanner.dto.response.population;

import lombok.*;
import store.bizscanner.entity.enums.Age;
import store.bizscanner.entity.enums.Day;
import store.bizscanner.entity.enums.Gender;
import store.bizscanner.entity.enums.Time;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BestPopulationResponse {
    private Gender bestPopulationGender;
    private Age bestPopulationAge;
    private Day bestPopulationDay;
    private Time bestPopulationTime;
}
