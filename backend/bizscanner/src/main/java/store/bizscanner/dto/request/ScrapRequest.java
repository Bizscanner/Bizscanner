package store.bizscanner.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class ScrapRequest {
    @NotNull
    private String careaCode;

    @NotNull
    private String jcategoryCode;
}
