package board.simplecrudboard.controller;


import board.simplecrudboard.post.Post;
import board.simplecrudboard.post.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
@Controller
@RequestMapping("/board/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @GetMapping
    public String posts(Model model) {
        log.info("posts method");
        ArrayList<Post> posts = postRepository.findAll();

        model.addAttribute("posts", posts);

        return "board/posts";
    }


    // post id를 바탕으로 해당 게시글을 찾고 모델에 넣어 board/post 뷰로 반환해준다.
    @GetMapping("/{postId}")
    public String post(@PathVariable long postId, Model model) {
        log.info("post method");

        Post findPost = postRepository.findById(postId);
        model.addAttribute("post", findPost);

        postRepository.updateView(postId, findPost);
        return "board/post";
    }



    @GetMapping("/add")
    public String add(Model model) {
        return "board/addPostForm";
    }

    @PostMapping("/add")
    public String addPost(RedirectAttributes redirectAttributes,
                          @RequestParam String postTitle,
                          @RequestParam String postBody,
                          Model model) {
        Post newPost = new Post(postTitle, postBody, LocalDateTime.now(), 0);
        postRepository.save(newPost);

        log.info("newPost's id: {}", newPost.getId());

        redirectAttributes.addAttribute("postId", newPost.getId());
        redirectAttributes.addAttribute("addStatus", true);
        model.addAttribute("post", newPost);

        return "redirect:/board/posts/{postId}";
    }

    @GetMapping("/{postId}/edit")
    public String edit(@PathVariable long postId,
                       Model model) {
        Post findPost = postRepository.findById(postId);
        model.addAttribute("post", findPost);

        return "/board/editPostForm";
    }

    @PostMapping("/{postId}/edit")
    public String editPost(@PathVariable long postId,
                           @RequestParam String postBody,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        // 포스트를 수정할때 바꿔야 하는 내용: 제목, 텍스트

        Post editedPost = postRepository.findById(postId);

        editedPost.setText(postBody);
        postRepository.update(postId, editedPost);
        model.addAttribute("post", editedPost);

        redirectAttributes.addAttribute("postId", postId);
        redirectAttributes.addAttribute("editStatus", true);


        // postId 는 뷰 주소로 넘겨서 다시 GET 요청하고, editStatus 는 쿼리 파라미터로 날린다.
        return "redirect:/board/posts/{postId}";

    }

    @GetMapping("/{postId}/delete")
    public String delete(@PathVariable long postId) {
        log.info("delete method");
        postRepository.delete(postId);

        return "redirect:/board/posts";
    }


}
