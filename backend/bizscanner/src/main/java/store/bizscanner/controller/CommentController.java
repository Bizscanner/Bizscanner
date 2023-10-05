package store.bizscanner.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import store.bizscanner.dto.request.CommentRequest;
import store.bizscanner.dto.response.comment.CommentListResponse;
import store.bizscanner.service.CommentService;

import org.springframework.security.core.Authentication;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@Valid @RequestBody CommentRequest commentRequest, Authentication authentication) {
        commentService.createComment(commentRequest, authentication.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{careaCode}")
    public ResponseEntity<CommentListResponse> getComment(@PathVariable String careaCode) {
        return new ResponseEntity<>(commentService.getComment(careaCode), HttpStatus.OK);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(@Valid @RequestBody CommentRequest commentRequest, @PathVariable Long commentId, Authentication authentication) {
        commentService.updateComment(commentRequest, commentId, authentication.getName());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, Authentication authentication) {
        commentService.deleteComment(commentId, authentication.getName());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/my")
    public ResponseEntity<CommentListResponse> getComment(Authentication authentication) {
        return new ResponseEntity<>(commentService.getMyComment(authentication.getName()), HttpStatus.OK);
    }
}
