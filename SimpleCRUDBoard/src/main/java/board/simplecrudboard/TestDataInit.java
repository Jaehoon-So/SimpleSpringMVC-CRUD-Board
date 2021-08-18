package board.simplecrudboard;

import board.simplecrudboard.post.Post;
import board.simplecrudboard.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final PostRepository postRepository;

    @PostConstruct
    public void init() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        postRepository.save(new Post("첫 번째 글 ", "첫 번째 글입니다.", currentDateTime, 0));
        postRepository.save(new Post("두 번째 글 ", "두 번째 글입니다.", currentDateTime, 0));
    }
}
