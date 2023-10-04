package store.bizscanner.dto.response.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentListResponse {
    List<CommentResponse> commentResponseList;

}
