package store.bizscanner.dto.response.jcategoryrecommend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import store.bizscanner.entity.Carea;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DongInfoResponse {
    private String careaCode;
    private String careaName;
    private Double latitude;
    private Double longitude;

    public DongInfoResponse(Carea carea){
        this.careaCode = carea.getCareaCode();
        this.careaName = carea.getCareaName();
        this.latitude = carea.getLatitude();
        this.longitude = carea.getLongitude();
    }
}
