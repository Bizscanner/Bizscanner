package store.bizscanner.dto.response.jcategoryrecommend;

import lombok.Getter;
import store.bizscanner.entity.CodeCenterCoordinate;
import store.bizscanner.entity.CodeCoordinate;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CodeAreaPolygonAndCenterResponse {

    private final Double centerLatitude;
    private final Double centerLongitude;
    private final List<double[]> polygonCoordinates;


    public CodeAreaPolygonAndCenterResponse(CodeCenterCoordinate center, List<CodeCoordinate> polygon) {
        this.centerLatitude = center.getLatitude();
        this.centerLongitude = center.getLongitude();
        polygonCoordinates = polygon.stream().map(x -> new double[]{x.getLongitude(), x.getLatitude()})
                .collect(Collectors.toList());
    }
}
