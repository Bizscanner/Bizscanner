package store.bizscanner.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "REFRESH_TOKEN", timeToLive = 1209600)
public class RefreshToken {

    @Id
    private String email;

    @Indexed
    private String refreshToken;

}
