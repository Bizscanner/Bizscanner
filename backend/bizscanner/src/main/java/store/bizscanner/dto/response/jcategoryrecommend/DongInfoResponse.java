package store.bizscanner.dto.response.jcategoryrecommend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import store.bizscanner.entity.Carea;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DongInfoResponse implements Serializable {
    private String careaTypeCode;
    private String careaCode;
    private String careaName;
    private Double centerLatitude;
    private Double centerLongitude;
    private List<double[]> polygonCoordinates;

    public DongInfoResponse(Carea carea, CodeAreaPolygonAndCenterResponse polygonAndCenter){
        this.careaTypeCode = carea.getCareaTypeCode();
        this.careaCode = carea.getCareaCode();
        this.careaName = carea.getCareaName();
        this.centerLatitude = polygonAndCenter.getCenterLatitude();
        this.centerLongitude = polygonAndCenter.getCenterLongitude();
        this.polygonCoordinates = polygonAndCenter.getPolygonCoordinates();
    }
}
