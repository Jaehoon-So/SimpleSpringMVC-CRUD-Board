package board.simplecrudboard.post;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PostRepository {

    private Map<Long, Post> store = new HashMap<Long, Post>();
    private static long sequence = 0L;

    public Post findById(Long id) {
        return store.get(id);
    }

    public ArrayList<Post> findAll() {
        return new ArrayList<Post>(store.values());
    }

    public Post save(Post post) {
        post.setId(++sequence);
        store.put(post.getId(), post);
        return post;
    }

    public void update(Long postId, Post updateParam) {
        Post findPost = store.get(postId);

        findPost.setText(updateParam.getText());
        findPost.setTitle(updateParam.getTitle());

    }

    public void updateView(Long postId, Post updateParam) {
        Post findPost = store.get(postId);

        findPost.setView(findPost.getView() + 1);
    }

    public void delete(Long postId) {
        store.remove(postId);
    }



}
