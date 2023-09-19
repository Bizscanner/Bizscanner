package store.bizscanner.dto.response.jcategoryrecommend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DongResponse {
    private List<DongInfoResponse> dongInfoResponseList;

}
