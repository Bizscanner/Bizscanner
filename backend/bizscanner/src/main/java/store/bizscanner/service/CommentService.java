package store.bizscanner.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.bizscanner.dto.request.CommentRequest;
import store.bizscanner.dto.response.comment.CommentListResponse;
import store.bizscanner.dto.response.comment.CommentResponse;
import store.bizscanner.entity.Comment;
import store.bizscanner.global.exception.CustomException;
import store.bizscanner.global.exception.ErrorCode;
import store.bizscanner.repository.CommentRepository;

import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberService memberService;

    /**
     * 코멘트 생성
     * 로그인 필수
     * @param commentRequest
     * @param email
     * @return comment
     */
    @Transactional
    public Comment createComment(CommentRequest commentRequest, String email) {
        return commentRepository.save(Comment.builder()
                        .careaCode(commentRequest.getCareaCode())
                        .jcategoryCode(commentRequest.getJcategoryCode())
                        .contents(commentRequest.getContents())
                        .member(memberService.findByEmail(email))
                .build());
    }

    /**
     * 코멘트 조회
     * @param careaCode
     * @return commentList
     */
    public CommentListResponse getComment(String careaCode) {
        return new CommentListResponse(
                commentRepository.findByCareaCode(careaCode).stream()
                        .map(comment -> new CommentResponse(
                                comment.getId(),
                                comment.getMember().getNickname(),
                                comment.getContents(),
                                comment.getModifiedDate()))
                        .collect(Collectors.toList()));
    }

    /**
     * 코멘트 수정
     * 로그인 필수 & 로그인 유저와 코멘트 작성자가 다를 경우 Exception
     * @param commentRequest
     * @param commentId
     * @param email
     * @return
     */
    @Transactional
    public Comment updateComment(CommentRequest commentRequest, Long commentId, String email) {
        return commentWriterValid(commentId, email).update(commentRequest.getContents());
    }

    /**
     * 코멘트 삭제
     * 로그인 필수 & 로그인 유저와 코멘트 작성자가 다를 경우 Exception
     * @param commentId
     * @param email
     */
    @Transactional
    public void deleteComment(Long commentId, String email) {
        commentWriterValid(commentId, email);

        commentRepository.deleteById(commentId);
    }

    /**
     * 자신이 작성한 코멘트 조회
     * @param email
     * @return
     */
    public CommentListResponse getMyComment(String email) {
        return new CommentListResponse(
                commentRepository.findByMember(memberService.findByEmail(email)).stream()
                        .map(comment -> new CommentResponse(
                                comment.getId(),
                                comment.getMember().getNickname(),
                                comment.getContents(),
                                comment.getModifiedDate()))
                        .collect(Collectors.toList())
        );
    }

    /**
     * 코멘트 ID 와 이메일을 이용해 작성자가 맞는지 검증하는 메소드
     * @param commentId
     * @param email
     * @return comment
     */
    public Comment commentWriterValid (Long commentId, String email) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        if(!comment.getMember().getEmail().equals(email)) {
            throw new CustomException(ErrorCode.MEMBER_NOT_WRITER);
        }

        return comment;
    }
}
