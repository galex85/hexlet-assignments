package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    private List<Post> posts = Data.getPosts();
    @GetMapping("users/{id}/posts") // Список страниц
    public List<Post> index( @PathVariable String id,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer limit) {

        return posts.stream()
                .filter(p -> p.getUserId() == Integer.valueOf(id))
                .skip((page - 1) * limit)
                .limit(limit).toList();
        //        return posts.stream().limit(limit).toList();
    }

    @PostMapping("/users/{id}/posts") // Создание страницы
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@PathVariable String id,
                       @RequestBody Post post) {
        post.setUserId(Integer.valueOf(id));
        posts.add(post);
        return post;
    }

}
// END
