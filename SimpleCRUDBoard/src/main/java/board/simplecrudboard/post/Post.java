package board.simplecrudboard.post;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Post {

    private Long id;
    private String title;
    private String text;
    private LocalDateTime localDateTime;
    private int view;

    public Post(String title, String text, LocalDateTime localDateTime, int view) {

        this.title = title;
        this.text = text;
        this.localDateTime = localDateTime;
        this.view = view;
    }
}
