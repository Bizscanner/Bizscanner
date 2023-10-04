package store.bizscanner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import store.bizscanner.entity.Comment;
import store.bizscanner.service.CommentService;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CommentControlletTest {

    @InjectMocks
    private CommentController commentController;

    @Mock
    private CommentService commentService;

    private MockMvc mockMvc;

    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
    }

    @DisplayName("comment post")
    @Test
    void CreateCommentTest() throws Exception {

        // given
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("careaCode", "2110008");
        requestMap.put("jcategoryCode", "CS300002");
        requestMap.put("contents", "test");

        Comment comment = new Comment();
        Mockito.when(commentService.createComment(any(), any()))
                .thenReturn(comment);

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestMap)))
                .andDo(print());

        // then
        resultActions.andExpect(status().isOk());
    }
}
