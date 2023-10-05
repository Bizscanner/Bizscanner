package store.bizscanner.dto.response.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private Long commentId;
    private String nickname;
    private String contents;
    private LocalDateTime modifiedDate;
}
