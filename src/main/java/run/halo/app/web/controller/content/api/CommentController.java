package run.halo.app.web.controller.content.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.halo.app.model.dto.CommentDTO;
import run.halo.app.model.params.CommentParam;
import run.halo.app.service.CommentService;

/**
 * Portal comment controller.
 *
 * @author johnniang
 * @date 4/3/19
 */
@RestController("PortalCommentController")
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    @ApiOperation("Comments a post")
    public CommentDTO comment(@RequestBody CommentParam commentParam) {
        return new CommentDTO().convertFrom(commentService.createBy(commentParam));
    }
}