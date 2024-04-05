package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<PostDTO> index() {

        val posts = postRepository.findAll();

        List<PostDTO> postDTOList = new ArrayList<>();

        posts.forEach(post -> {
            val comments = commentRepository.findByPostId(post.getId());
            List<CommentDTO> commentDTOList = comments.stream().map(this::toCommentDTO).toList();;
            postDTOList.add(toPostDTO(post, commentDTOList));
        });

        return postDTOList;
    }

    @GetMapping(path = "/{id}")
    public PostDTO show(@PathVariable long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        val comments = commentRepository.findByPostId(post.getId());
        List<CommentDTO> commentDTOList = comments.stream().map(this::toCommentDTO).toList();

        return toPostDTO(post, commentDTOList);
    }

    private CommentDTO toCommentDTO (Comment comment) {
        val commentDTO = new CommentDTO();

        commentDTO.setId(comment.getId());
        commentDTO.setBody(comment.getBody());

        return commentDTO;
    }

    private PostDTO toPostDTO(Post post, List<CommentDTO> comments) {
        val postDTO = new PostDTO();

        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setBody(post.getBody());
        postDTO.setComments(comments);

        return postDTO;
    }

}
// END
