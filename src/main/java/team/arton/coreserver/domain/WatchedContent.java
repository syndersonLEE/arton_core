package team.arton.coreserver.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity(name = "watched_content")
public class WatchedContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    public WatchedContent(User user, Content content) {
        this.user = user;
        this.content = content;
    }

    public WatchedContent() {

    }
}
