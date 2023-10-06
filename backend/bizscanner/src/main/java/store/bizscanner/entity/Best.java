package store.bizscanner.entity;

import lombok.Getter;

/**
 * Best 항목을 찾기 위한 Class
 * 여러 Enum Type에 대응하기 위해 Object로 선언
 * value를 기준으로 정렬하는 규칙 생성
 */
@Getter
public class Best implements Comparable<Best> {
    private final Long value;
    private final Object object;

    public Best (Long value, Object object) {
        this.value = value;
        this.object = object;
    }

    @Override
    public int compareTo(Best o) {
        return Long.compare(o.value, this.value);
    }
}