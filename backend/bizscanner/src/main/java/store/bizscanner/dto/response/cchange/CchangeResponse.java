package store.bizscanner.dto.response.cchange;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import store.bizscanner.entity.Cchange;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CchangeResponse {
    private String careaChange;
    private String careaChangeName;

    public CchangeResponse(Cchange cchange){
        this.careaChange = cchange.getCareaChange();
        this.careaChangeName = cchange.getCareaChangeName();
    }
}
